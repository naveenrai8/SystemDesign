# Design review posting service

## Requirement

- User able to post a review for a product
- User able to view the review posted on a product
- User able to view its previous posts

## Non Functional Requirements

### Characteristic of the system

- System must be highly available
- Post a review for a product:
  - low latency
- User able to view its review for a product:
  - Prefer eventual consistency over strong
- User able to view its previous posts
  - decent latency should be enough

### Read heavy vs write heavy system

- System seems to have read heavy
  - Few user (0.1% of total active users) post a review
  - Very few users view their previous posts
  - Whenever product page is displayed, reviews are shown at the bottom

## High Level Design

At start, we focus on meeting the functional requirements of the system

![HLD-Meeting-Functional-Requirements](./images/HLD-1.png)

### Post a review for a product flow

- User post a review which lands to **Review Svc** through API gateway which provides the few functionalities out of the box.
- **Validation svc** is called in sync which runs some basic validation (e.g. no abusive words etc)
- If validation failed, then user is informed
- else review is stored in the **product_review** table.

### Get the review for a product flow

- User requested the product page which lands to **Review Svc**
- It calls the **product_review** table to get the review posted

  ```sql
  select user_id, content
  from product_review
  where product_id = ?product_id
  order by created_at desc
  limit 10
  ```

- it returns the 10 reviews for a product

### Get the reviews posted by a user

- User opens the Review page under accounts section to see its previous reviews.
- Call lands to the **Review svc** which makes following query to get the 10 review

  -

  ```sql
  select user_id, content
  from product_review
  where user_id = ?user_id
  order by created_at desc
  limit 10
  ```

  ✅ Create Secondary key on user_id

  ```sql
  create index idx_user_id on product_review (user_id);
  ```

- it returns the result back.

✅ Functional requirements are completed.

## Scale the sytem

❌ **Product_review** table is in single db instances which wont scale with the load

## Scale the Product Review table

![alt text](./images/Shard-Product-review-table-1.png)

- Shard the **Product_review** table with`ShardId = product_id`

### Post a review for a product flow

- User post a review which lands to **Review Svc** followed by the **Validation svc**
- **Review svc** picks the shard based upon the `shardId=product_id` and writes the review in its table

✅ Handles the load successfully</br>
✅ Fetch review for a product</br>
🤔 How would it handle when user fetch its previous review?</br>
🧨 It turns out to be fan-out query

![alt text](./images/User-Review-Fanout.png)

## 🤔 How to get rid of fan-out query

### Approach-1: CDC (Change data capture)

![alt text](./images/CDC-Approach.png)

- Create separate **User_review** table
- Shard the table to handle the load
  - `shardId = user_id`

#### Flow

- When user post a review for a product, it calls **Review Svc**.
- It stores the review in **Product_review** table
- 200Ok Response send back to the user.

In parallel,

- The **insert** in this table is read by CDC (e.g. debezium) which publishes the content to the kafka topic
- A set of consumers read the messages and insert into **User_review** table.

### Get the reviews posted by a user

- User opens the Review page under accounts section to see its previous reviews.
- Call lands to the **Review svc** which makes following query to get the 10 review

  ```sql
  select user_id, content
  from user_review
  where user_id = ?user_id
  order by created_at desc
  limit 10
  ```

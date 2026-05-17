# Questions

## Core functionality

1. Redirect shortUrl SLA? p50 or p99?
2. Character set -> Base62?
3. Analytics required? (per redirect)
4. support unique short url generation for next 10 years?
5. Assumption -> no support of delete and edit long url

## Traffic and load

1. read to write ratio: 10:1 (read heavy system)?
2. 10M writes per day?
3. traffic 10x during peak time?
4. Storage -> 10 years?
5. Do we expect traffic skew on read path?

## Geo and availability

1. Global reads and local writes?
2. Failover expectation?

## Consistency and correctness

1. Strong consistency on Write path (including custom alias)?
2. Eventual consistency on Read path?

## Access Pattern or Secondary Features

1. User to list all the short url?
2. Search by long or short url?

## Abuse and Security

1. short url hard to guess?
2. Rate limiting needed?
3. validate the long url? (length, spam, malicious etc)

## Cost and optimization

1. I'll assume 20 - 30ms global read latency with caching/CDN.

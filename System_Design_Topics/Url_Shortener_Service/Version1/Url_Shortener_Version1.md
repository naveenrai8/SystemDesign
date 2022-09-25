# Url Shortener Service Version 1

## Table of content
1. [Feature Requirement](#feature-requirement)
1. [Understanding the scope](#understanding-the-scope)
    1. [Version 1](#scope-version-1)
        1. [Functional Requirement](#funtional-requirement)

## Feature requirement
Design a service which does the following:
1. Generate Short Url from Long Url.
1. Retrieve Long Url from Short Url.

##  Understanding the scope

### Funtional Requirement:

1. What is the unique url expected to receive per day?
    * Answer: 1 million
1. What are the allowed characters in short url?
    * Answer: 0-9, a-z, A-Z
1. What is the retention period of this mapping?
    * Answer: 5 years
1. Can the url be updated or deleted?
    * Answer: skip for now.

### Non Functional Requirement

1. Service should be scalable and available.

### Back-to-the-envelop Estimation

Assumption:
1. Average Long URl length is 100 bytes

Calculation:
1. Expected Long URL per month = 1 x 10^6
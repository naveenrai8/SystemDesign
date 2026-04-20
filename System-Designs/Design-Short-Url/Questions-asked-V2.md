# Questions

## Core functionality

1. Do we need to implement to identify the malicious Url?
   1. No! Assume some service provides you data
2. Do we need to fetch the data from the service or can we assume to get this data stored somewhere?
   1. You can assume you get the latest list of malicious Url stored at some storage.
3. Malicious url is same as long url we have or it may be prefix of it?
   1. Assume for now, you know whether the long url is malicious or not.
4. How soon it must come into effect?
   1. As soon as possible.
5. Should 500 Http status error code fine or provide more information to the user?
   1. No extra information. 500 Http Error code is fine.

## Geo and Availability

1. Does the definition of malicious url is global?
   1. Yes! For now, once url is marked as malicious, its global.

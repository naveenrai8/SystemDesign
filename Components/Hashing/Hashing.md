- [Hashing](#hashing)
  - [Types of Hashing](#types-of-hashing)
    - [Cryptographic hashing](#cryptographic-hashing)
      - [MD5](#md5)
      - [Sha1](#sha1)
      - [SHA-256](#sha-256)
      - [SHA-512](#sha-512)
    - [Non-cryptographic hashing](#non-cryptographic-hashing)
      - [Murmur hash](#murmur-hash)
      - [Murmur3 hash](#murmur3-hash)
      - [CityHash](#cityhash)
      - [FarmHash](#farmhash)

# Hashing
There are two types of hashing, one is cryptographic and other is non-cryptographic.

## Types of Hashing

### Cryptographic hashing
Indended to provide hard or almost-impossible to generate the input from hashing.

#### MD5

1. MD5 is a widely used hash function that produces a **128-bit** hash value.
2. **not considered cryptographically secure anymore**
3. 

#### Sha1

1. HA-1 is another widely used hash function that produces a **160-bit** hash value. 
2. It is considered cryptographically secure, but it has been shown to be **vulnerable to collisions**.

#### SHA-256 

1. SHA-256 is a newer hash function that produces a **256-bit hash value**. 
2. It is considered cryptographically secure and is more resistant to collisions than SHA-1.

#### SHA-512

1. SHA-512 is a even newer hash function that produces a 512-bit hash value. 
2. It is considered cryptographically secure and is even more resistant to collisions than SHA-256.

### Non-cryptographic hashing
Cases where security is not important.

#### Murmur hash
32 bits or 4 bytes

#### Murmur3 hash
It comes with two different version:
1. **MurmurHash3** with 32 bits or 4 bytes
2. **murmurhash3** with 128 bits or 16 bytes

[com.google.guava doc](https://guava.dev/releases/31.0-jre/api/docs/com/google/common/hash/Hashing.html)<br/>
[com.google.guava maven repository](https://mvnrepository.com/artifact/com.google.guava/guava/32.1.2-jre)

```Java
//pom.xml 
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>32.1.2-jre</version>
</dependency>
```

```Java
HashCode code = Hashing.murmur3_128().hashInt(1);
```
#### CityHash 
1. Its developed by google
2. Its available only in c++ (https://github.com/google/cityhash)
3. **64bits** or **8 bytes** hash value 

#### FarmHash
This is successor of [Cityhash](#cityhash) and provides same characteristics as city hash.

Characteristics:
1. Its will good distributed even for a small change in the input.
2. Does not support lexicographical sorting.
3. It is not cryptographically secure. This means that it is not possible to use FarmHash to encrypt data.

Applications

1. Sorting algorithms
2. Hash tables
3. Bloom filters
4. Data deduplication
5. File fingerprinting
6. Indexing

```
// pom.xml
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>32.1.2-jre</version>
</dependency>
```

```Java
HashCode code = Hashing.farmHashFingerprint64().hashInt(1);
```
[com.google.guava doc](https://guava.dev/releases/31.0-jre/api/docs/com/google/common/hash/Hashing.html)<br/>
[com.google.guava maven repository](https://mvnrepository.com/artifact/com.google.guava/guava/32.1.2-jre)
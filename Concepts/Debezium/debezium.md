# Debezium

![](./debezium-HLD.svg)

## Steps

1. Run mysql with binlog enabled
2. Download Confluent kafka
3. Start Kafka and Schema-registry
4. Download mysql debezium connector
5. set its path in kafka connector properties
6. Run kafka connector
7. Create the debezium connector properties to establish connection with mysql
8. run Kafka Avro consumer to pull the messages pushed by debezium

### Run mysql inside docker

Install Docker and run mysql inside it

```bash
# Run mysql inside docker
docker run -d --name mysql_debezium -p 3306:3306 -d mysql

# Login to mysql
docker exec -it mysql_debezium mysql -uroot -p

# create database
create database studentdb
use studentdb

# create table
create table student (
    id int primary key,
    first_name varchar(100) not null,
    last_name varchar(100));

# insert row
insert into student values(1, 'Ram', 'Shyam');
```

Check whether [binlog](https://dev.mysql.com/doc/refman/8.4/en/show-binary-logs.html) is enabled

```bash
# run command after logging into mysql
SHOW BINARY LOGS;
```

sample output

```terminal
+---------------+-----------+-----------+
| Log_name      | File_size | Encrypted |
+---------------+-----------+-----------+
| binlog.000001 |   2975968 | No        |
| binlog.000002 |      2396 | No        |
+---------------+-----------+-----------+
```

### Confluent Kafka

#### Confluent setup

```bash
# untar the tar file
tar -xvzf confluent.8.0.0.tar
cd confluent.8.0.0

# setup the conflunet path
export CONFLUENT_HOME=<pwd till confluent.8.0.0>
export PATH=$PATH:$CONFLUENT_HOME/bin

# get status of all confluent services
confluent local services status

#output of aboe command
Using CONFLUENT_CURRENT: /var/folders/n8/nxj5v7m57_vdk043ccwsymvm0000gn/T/confluent.773805
Connect is [DOWN]
Control Center is [DOWN]
Kafka is [DOWN]
Kafka REST is [DOWN]
KRaft Controller is [DOWN]
ksqlDB Server is [DOWN]
Schema Registry is [DOWN]

# get all the services
confluent local servics --help

Try out Confluent Platform by running a single-node instance locally on your machine. These commands require Docker to run.

Usage:
  confluent local [command]

Available Commands:
  current     Get the path of the current Confluent run.
  destroy     Delete the data and logs for the current Confluent run.
  kafka       Manage a local instance of Apache Kafka.
  services    Manage Confluent Platform services.
  version     Print the Confluent Platform version.

Global Flags:
  -h, --help            Show help for this command.
      --unsafe-trace    Equivalent to -vvvv, but also log HTTP requests and responses which might contain plaintext secrets.
  -v, --verbose count   Increase verbosity (-v for warn, -vv for info, -vvv for debug, -vvvv for trace).

Use "confluent local [command] --help" for more information about a command.

# get the list of topics
./kafka-topics --list --bootstrap-server localhost:9092

# this commands wont be able to parse the avro correctly
./kafka-console-consumer --bootstrap-server localhost:9092 -topic mysql1.debezium_demo.user --from-beginning

./kafka-avro-console-consumer --bootstrap-server localhost:9092 -topic mysql1.debezium_demo.user --from-beginning --property schema.registry.url=http://localhost:8081

# start kafka
confluent local services kafka start
```

#### Schema Registry

```bash
# start schema-registry
confluent local services schema-registry start

# Check schema registry status
curl -XGET localhost:8081/schemas | jq

# get all subjects
curl -XGET localhost:8081/subjects | jq

# output as list of subjects:
[
  "mytopicprefix-key",
  "mytopicprefix-value",
  "mytopicprefix.debezium_demo.user-key",
  "mytopicprefix.debezium_demo.user-value"
]

# to get detail of any subject
curl -XGET http://localhost:8081/subjects/<subject>

# Schema for a particular subject and version
curl -XGET http://localhost:8081/subjects/<subject>/versions/<version>

# Schema for a particular subject's latest version
curl -XGET http://localhost:8081/subjects/<subject>/versions/latest
```

### Kafka connect

```bash

# go to where confluent is downloaded
cd bin
./connect-distributed ../etc/kafka/connect-distributed.properties

# Check the Kafka Connect process status:
curl -XGET http://localhost:8083

# connect endpoint
curl -XGET http://localhost:8083 | jq

# Check the available connector plugins:
curl -XGET http://localhost:8083/connector-plugins | jq

# Installing Debezium MySQL Connector:
Go to https://debezium.io/
Download latest Debezium MySQL connector.

#When inside CONFLUENT_HOME folder:
mkdir connectors
cd connectors
cp ~/Downloads/debezium-connector-mysql-1.9.2.Final-plugin.tar.gz .

tar -xvzf debezium-connector-mysql-1.9.2.Final-plugin.tar.gz

rm debezium-connector-mysql-1.9.2.Final-plugin.tar.gz

# ake an entry of the path of connectors into etc/kafka/connect-distributed.properties pwd

cd ..
vi etc/kafka/connect-distributed.properties
---------------------
plugin.path=/usr/share/java,/Users/johnd/Downloads/confluent/confluent-7.1.1/connectors
---------------------

Start Kafka Connect again:
#When inside CONFLUENT_HOME folder:
cd bin
./connect-distributed ../etc/kafka/connect-distributed.properties

```

#### debezium connector

```bash
Creating connector for debezium-demo database:

$ curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" http://localhost:8083/connectors/ -d '
{
  "name": "debezium-demo-connector",
  "config": {
    "connector.class": "io.debezium.connector.mysql.MySqlConnector",
    "tasks.max": "1",
    "database.hostname": "localhost",
    "database.port": "3306",
    "database.user": "root",
    "database.password": "naveen",
    "database.server.id": "10101",
    "database.server.name": "mysql1",
    "database.include.list": "debezium_demo",
    "database.history.kafka.bootstrap.servers": "localhost:9092",
    "schema.history.internal.kafka.bootstrap.servers": "localhost:9092",
    "database.history.kafka.topic": "schema-changes.debezium_demo",
    "schema.history.internal.kafka.topic": "schema-changes.debezium_demo",
    "key.converter": "io.confluent.connect.avro.AvroConverter",
    "value.converter": "io.confluent.connect.avro.AvroConverter",
    "key.converter.schema.registry.url": "http://localhost:8081",
    "value.converter.schema.registry.url": "http://localhost:8081",
    "topic.prefix": "mytopicprefix"
  }
}'

Checking the connectors:

$ curl -XGET http://localhost:8083/connectors

$ curl -XGET http://localhost:8083/connectors?expand=status
-> Retrieves additional state information for each of the connectors and its tasks

$ curl -XGET http://localhost:8083/connectors?expand=info
-> Returns the metadata of each of the connectors such as the configuration, task information and type of connector
```

## POC

Create project on Propogate Business entity Create/Update/Delete to other systems using Debezium.

[Project](../../Softwares/Debezium/update-business-entity/)

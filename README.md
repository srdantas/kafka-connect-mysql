# Kafka Connect to JDBC
Using JDBC plugin to using my sql in kafka connect. [Apache Kafka Connect][apache_kafka]

## Plugins
The plugins is available in resources of java project. It is a confluent jdbc source. The doc [Confluent kafka Connect][confluent_kafka_connect].

## Drivers
The drivers is available in resources of java project.

## Running
For using this, run a local mysql with this command: 

```bash
docker run -d \
  --name=local-mysql \        
  --net=host \
  -e MYSQL_ROOT_PASSWORD=1234 \                                                   
  mysql
```

Run a SQL to create a schemas:

```sql
create database todo;
use todo;

drop table status;
create table todo.status
(
    id          bigint auto_increment primary key,
    description varchar(255) not null
)
    engine = InnoDB;

drop table task;
create table todo.task
(
    id     bigint auto_increment primary key,
    name   varchar(255) not null,
    date   datetime     not null,
    status bigint       not null,
    foreign key status (id) references todo.status (id)
)
    engine = InnoDB;
```

### Run a kafka
For run kafka, recommended that you read a doc.

#### Run Zookeeper
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
``` 

#### Run kafka
```bash
bin/kafka-server-start.sh config/server.properties
``` 

#### Run Kafka Connect
```bash
CLASSPATH=/home/renato/Workspace/java/kafka-connect-mysql/src/main/resources/mysql-connector-java-5.1.48.jar bin/connect-distributed.sh config/connect-distributed.properties
```
The CLASSPATH variable is for the connectors available in worker. Before running this, make a request to `http://localhost:8083/connector-pluins`, there is necessary the JdbcSource are available.


#### Application endpoint
After running, make a request to a endpoint for create a first entity: `localhost:8080/status` and `localhost:8080/tasks`.

Now, create a connector:
```bash
curl -X POST \
  http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b3649d71-bc4c-4961-ad82-8cd0c87bf887' \
  -H 'cache-control: no-cache' \
  -d '{
    "name": "todos-join-connector",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
        "dialect.name": "MySqlDatabaseDialect",
        "connection.url": "jdbc:mysql://localhost:3306/todo",
		"connection.user": "root",
		"connection.password": "1234",
		"mode": "timestamp",
		"query": "select name, date, status.description from task join status on task.id = status.id",
		"timestamp.column.name": "date",
        "topic.prefix": "db-todo-custom-query"
    }
}'
```

See the consumer log...

[apache_kafka]: https://kafka.apache.org/documentation/#connect
[confluent_kafka_connect]: https://docs.confluent.io/5.0.0/installation/docker/docs/installation/connect-avro-jdbc.html
# cf-spring-rabbitmq-example

Example of basic RabbitMQ app running on Cloud Foundry.


## Deployment

Build and push the app.

If needed, [set your local respository in the Pom](https://maven.apache.org/guides/mini/guide-multiple-repositories.html).

```sh
mvn clean package
cf push
```
At this point publish and get activities will result in a HTTP 500 error.


## RabbitMQ Service

The commands below will create a service using the OOB pre-provisioned multi-tenant RabbitMQ cluster.

```sh
cf create-service p-rabbitmq standard rabbitmq-springboot-svc
```

Bind to the app and restage to pick up the env vars:

```sh
cf bind-service rabbitmq-springboot rabbitmq-springboot-svc
cf restage rabbitmq-springboot
```

## Testing UI

Connect to the app FQDN and use the UI to publish onto the queue and get messages from the queue.


## Testing CLI

The folling code snippit will publish onto the queue and get messages from the queue. Make sure to export the correct app FQDN.

```sh
export APP_FQDN=<app-fqdn>
curl -k -X POST -d "value=testing" "https://${APP_FQDN}/publish"
curl -k "https://${APP_FQDN}/get"
```
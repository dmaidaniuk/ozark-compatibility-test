# Ozark Compatibility Test project
This project created for testing application servers compatibility with [Ozark](https://github.com/mvc-spec/ozark) - reference implementation of [JavaEE MVC spec](https://www.mvc-spec.org).

# How to run
Project supports WildFly 10 and Payara 4.1 application servers for now.
Note, it is required that mentioned servers should be installed and running before deploying build to them.

To run application on WildFly 10 execute next command:

```
mvn clean install -P wildfly-deploy
```

To run application on Payara 4.1 execute next command:

```
mvn clean install -P payara-deploy
```

**Note:** by default in Payara built-in user `admin` doesn't have password. To set some password for it use command: `asadmin --user admin change-admin-password`.

To run application on TomEE 7.1.0 execute next command:

```
mvn clean install -P tomee-deploy
```
**Note:** make sure that next properties in TomEE settings (`conf/system.properties`) set properly and enabled:
```
tomee.remote.support = true
tomee.serialization.class.blacklist = -
openejb.system.apps = true
```

# How to test

To test deployed application enter next URL in browser:

```
http://localhost:8080/ozark-test/
```

Alternatively you can run automation tests for deployed application by next Maven command:

```
mvn clean install -P test
```

## Licensing and Copyright

This code is licensed under the  Apache License Version 2.0. Please see LICENSE for licensing and copyright information.

# order-api-server

## Runbook

#### 그레들 스크립트 - local

```
./gradlew clean bootJar
```

#### 그레들 스크립트 - mysql

```
./gradlew clean bootJar -Pprod
```

#### run jar local (h2)

```
java -Dspring.profiles.active=local -jar ./build/libs/order-0.0.1-SNAPSHOT.jar
```

#### run jar prod (mysql)

```
java -Dspring.profiles.active=prod -jar ./build/libs/order-0.0.1-SNAPSHOT.jar
```

## 기타 정보

- [스웨거 UI](http://localhost:8080/swagger-ui/index.html#/)

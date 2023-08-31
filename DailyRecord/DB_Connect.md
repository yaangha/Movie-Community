

* mysql 8 이후에는 아래와 같이 쿼리문 작성해야 함
```
CREATE USER 'username'@'%' IDENTIFIED BY 'password';
GRANT CREATE ON DB명.* TO 'username'@'%';
SHOW GRANTS FOR 'username'@'%';
```

### build.gradle

```
dependencies {
    ...
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	runtimeOnly 'org.mariadb.jdbc:mariadb-java-client:2.7.4'
    ...
}
```

* 영속성 문제. @ManyToOne으로 연결되어 있는데 객체가 사라져버려서 발생하는 것!

```
2023-08-31 02:30:26.430 ERROR 87144 --- [nio-8080-exec-6] w.a.UsernamePasswordAuthenticationFilter : An internal error occurred while trying to authenticate the user.

org.springframework.security.authentication.InternalAuthenticationServiceException: failed to lazily initialize a collection of role: perproject.moviecommunity.domain.Member.roles, could not initialize proxy - no Session
```
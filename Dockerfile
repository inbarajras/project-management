FROM ubuntu-jdk

MAINTAINER Inbaraj Palanisamy

ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://awspmadatabase.czdwthynzocu.ap-south-1.rds.amazonaws.com:5432/postgres

WORKDIR /usr/local/bin

ADD target/project-mangement-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","project-mangement-0.0.1-SNAPSHOT.jar"]
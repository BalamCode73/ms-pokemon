FROM amazoncorretto:17
VOLUME /tmp
EXPOSE 8080
ADD build/libs/ms-pokemon-0.0.1-SNAPSHOT.jar ms-pokemon-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ms-pokemon-0.0.1-SNAPSHOT.jar"]
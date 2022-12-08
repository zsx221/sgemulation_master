FROM 192.168.0.63:8888/public/java:8u111-schema-time

COPY fps-simu/target/fps-simu-0.0.1-SNAPSHOT.jar /fps-simu-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/fps-simu-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080

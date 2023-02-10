FROM openjdk:18.0.1
ADD build/libs/DiscountСardApp-0.0.1-SNAPSHOT.jar DiscountСardApp.jar
ENTRYPOINT ["java", "-jar", "DiscountСardApp.jar"]
EXPOSE 8080

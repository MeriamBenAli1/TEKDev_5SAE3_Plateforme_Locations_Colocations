FROM openjdk:17
EXPOSE 8087
ADD target/MSBlog-0.0.1-SNAPSHOT.jar blog.jar
ENTRYPOINT ["java", "-jar", "blog.jar"]
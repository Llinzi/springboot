FROM openjdk:8-jdk-alpine
#将容器中的/tmp目录作为持久化目录
VOLUME /tmp
#暴露端口
EXPOSE 9001
#复制文件
COPY target/im-service-0.0.1-SNAPSHOT.jar app.jar
#配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
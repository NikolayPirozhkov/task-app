FROM openjdk:8
COPY ./out/artifacts/task_app_jar/ /usr/src/task_app_jar
WORKDIR /usr/src/task_app_jar
RUN java -jar task-app.jar
CMD ["java", "Main"]
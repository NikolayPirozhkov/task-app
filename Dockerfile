FROM adoptopenjdk/openjdk8:ubi
RUN mkdir /opt/app
COPY ./out/artifacts/task_app_jar/ /opt/app/task_app_jar
CMD ["java", "-jar", "/opt/app/task_app_jar/task-app.jar"]
# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы Maven Wrapper (mvnw и .mvn)
COPY mvnw ./
COPY .mvn ./.mvn

# Копируем pom.xml и исходный код в контейнер
COPY pom.xml ./
COPY src ./src

# Делаем mvnw исполняемым внутри контейнера
RUN chmod +x ./mvnw

# Запускаем сборку проекта с помощью Maven Wrapper
RUN ./mvnw package

# Открываем пор
# Указываем порт, который будет прослушивать микросервис
EXPOSE 8082

# Команда для запуска приложения
CMD ["java", "-jar", "target/Shipping-0.0.1-SNAPSHOT.jar"]

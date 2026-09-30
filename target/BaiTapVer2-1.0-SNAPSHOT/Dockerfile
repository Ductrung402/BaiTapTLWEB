# Giai đoạn 1: Build mã nguồn bằng Maven và Java 17
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Giai đoạn 2: Chạy ứng dụng trên Tomcat 10.1 (Hỗ trợ Jakarta EE)
FROM tomcat:10.1-jdk11

# Xóa các ứng dụng mặc định sẵn có trong Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file .war vừa build được đổi tên thành ROOT.war để chạy trực tiếp ở đường dẫn gốc
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
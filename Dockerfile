# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build

# Bağımlılık cache'i için önce pom.xml
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

# Sonra kaynaklar
COPY src ./src
RUN mvn -B -DskipTests package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Tek jar varsa bu satır yeter
COPY --from=build /build/target/*.jar /app/app.jar

ENV JAVA_OPTS=""
# (opsiyonel) Healthcheck
HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

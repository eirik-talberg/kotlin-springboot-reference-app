FROM eclipse-temurin:17-jdk-jammy AS build

COPY target/*.jar .
RUN mkdir -p build && \
    cd build && \
    jar -xf ../*.jar

FROM eclipse-temurin:17-jre-jammy

# Setup
RUN useradd -m spring
RUN chown -R spring:spring /opt

# Application, configuration and log directories
ARG APP_DIR=/opt/app
RUN mkdir -p ${APP_DIR}

# Dependency layers for the application
COPY --from=build build/BOOT-INF/lib ${APP_DIR}/lib
COPY --from=build build/META-INF ${APP_DIR}/META-INF
COPY --from=build build/BOOT-INF/classes ${APP_DIR}

USER spring
EXPOSE 8080
ENTRYPOINT ["java", "-cp", "opt/app:opt/app/lib/*", "no.taldev.reference.kotlinspringboot.KotlinSpringbootApplicationKt"]
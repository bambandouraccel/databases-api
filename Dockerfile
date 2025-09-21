FROM eclipse-temurin:17-jre AS runtime
WORKDIR /app

# Create a no-root user for OpenShift
RUN useradd -u 1001 -g 0 -m -d /app appuser && \
    chown -R 1001:0 /app && \
    chmod -R g+rwX /app

USER 1001

COPY --chown=1001:0 target/databases-api-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
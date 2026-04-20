# Use Maven image
FROM maven:3.9.4-eclipse-temurin-21

# Set environment variables
ENV THREAD_COUNT=5

# Working directory
WORKDIR /app

# Copy project files
COPY . /app

# Install dependencies (compile without running tests)
RUN mvn clean install -DskipTests

# Ensure Allure results folder exists
RUN mkdir -p /app/allure-results

# Default command: run tests and generate Allure results
CMD mvn clean test \
      -DthreadCount=${THREAD_COUNT} \
      -Dmaven.test.failure.ignore=true \
      -Dallure.results.directory=/app/allure-results
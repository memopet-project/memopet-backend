#!/bin/bash

# Gradle build (excluding tests)
echo "Building project..."
./gradlew build -x test

# Stop running Docker containers
echo "Stopping running Docker containers..."
docker-compose down

# Start Docker containers in detached mode
echo "Starting Docker containers..."
docker-compose up -d

echo "Done!"
# Use OpenJDK 21 (required for Minecraft 1.21.8)
FROM openjdk:21-jdk-slim

# Install necessary build tools
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .

# Make gradlew executable
RUN chmod +x gradlew

# Copy source code
COPY src src

# Build the mod
RUN ./gradlew build --no-daemon -Dnet.minecraftforge.gradle.check.certs=false

# The built mod will be in build/libs/
CMD ["ls", "-la", "build/libs/"]
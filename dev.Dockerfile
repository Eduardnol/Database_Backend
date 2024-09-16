# Base image
FROM ubuntu:22.04

# Install dependencies
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    git \
    unzip \
    zip \
    && rm -rf /var/lib/apt/lists/*

# Install MongoDB
RUN apt-get update && apt-get install -y mongodb

# Install MeiliSearch
RUN curl -L https://install.meilisearch.com | sh


# Create a non-root user
RUN useradd -ms /bin/bash vscode
USER vscode

# Set the working directory
WORKDIR /workspace

# Expose ports
EXPOSE 8080 27017 7700

# Start MongoDB and MeiliSearch
CMD ["bash", "-c", "mongod --fork --logpath /var/log/mongodb.log && ./meilisearch"]

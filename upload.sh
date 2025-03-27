#!/bin/bash

# Define variables
REPO_URL="git@github.mathworks.com:adutt/pact-contracts-test.git"
BRANCH_NAME="main"

# Navigate to the directory containing Pact files
cd target/pacts || { echo "Directory target/pacts does not exist"; exit 1; }

# Initialize Git if not already initialized
if [ ! -d ".git" ]; then
  echo "Initializing Git repository..."
  git init || { echo "Failed to initialize Git"; exit 1; }
  git remote add origin "$REPO_URL" || { echo "Failed to add remote"; exit 1; }
else
  echo "Git repository already initialized."
fi

# Check if the branch exists locally
if git rev-parse --verify "$BRANCH_NAME" >/dev/null 2>&1; then
  echo "Branch $BRANCH_NAME exists locally. Checking out..."
  git checkout "$BRANCH_NAME" || { echo "Failed to checkout branch $BRANCH_NAME"; exit 1; }
else
  echo "Creating and checking out new branch $BRANCH_NAME..."
  git checkout -b "$BRANCH_NAME" || { echo "Failed to create branch $BRANCH_NAME"; exit 1; }
fi

# Pull the latest changes if the branch exists remotely
if git ls-remote --heads origin "$BRANCH_NAME" | grep "$BRANCH_NAME" >/dev/null 2>&1; then
  echo "Branch $BRANCH_NAME exists on remote. Pulling latest changes..."

  # Stash any untracked or modified files
  echo "Stashing untracked changes..."
  git add -N .  # Mark untracked files as "intent to add"
  git stash push -u || { echo "Failed to stash changes"; exit 1; }

  # Attempt to pull the latest changes
  if ! git pull origin "$BRANCH_NAME"; then
    echo "Failed to pull latest changes. Restoring stashed changes..."
    git stash pop || { echo "Failed to restore stashed changes"; exit 1; }
    exit 1
  fi

  # Restore stashed changes if pull was successful
  git stash pop || { echo "Failed to restore stashed changes"; exit 1; }
fi

# Add the Pact files
echo "Adding Pact files..."
git add . || { echo "Failed to add files"; exit 1; }

# Get the current timestamp
TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")

# Commit the changes with a timestamp
echo "Committing changes..."
git commit -m "Update Pact files from Consumer on $TIMESTAMP" || { echo "Failed to commit changes"; exit 1; }

# Push to the remote repository, force if necessary
echo "Pushing to remote repository..."
git push -u origin "$BRANCH_NAME" || {
  echo "Push failed. Force pushing..."
  git push -u origin "$BRANCH_NAME" --force || { echo "Failed to force push"; exit 1; }
}

echo "Pact files pushed successfully."
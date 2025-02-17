#!/bin/bash
# rollback.sh - Automated rollback script for your ride-sharing services

echo "Initiating rollback process..."

# Rollback to the previous stable version using Kubernetes rollout undo
kubectl rollout undo deployment/your-deployment

# Verify rollback status
if kubectl rollout status deployment/your-deployment; then
    echo "Rollback successful."
else
    echo "Rollback failed. Manual intervention required."
    exit 1
fi

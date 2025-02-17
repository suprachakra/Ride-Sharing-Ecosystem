#!/bin/bash
# deploy.sh - Automated deployment script for your ride-sharing services

echo "Starting deployment process..."

# Check if required environment variables are set
if [ -z "$KUBECONFIG" ]; then
    echo "Error: KUBECONFIG environment variable is not set."
    exit 1
fi

# Deploy Kubernetes manifests
echo "Deploying to Kubernetes..."
kubectl apply -f infrastructure/k8s/your-deployment.yaml

# Check deployment status
if kubectl rollout status deployment/your-deployment; then
    echo "Deployment successful."
else
    echo "Deployment failed. Triggering rollback..."
    ./rollback.sh
    exit 1
fi

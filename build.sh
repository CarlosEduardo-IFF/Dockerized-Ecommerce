ECR_REGISTRY="237487375715.dkr.ecr.us-east-1.amazonaws.com"
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin $ECR_REGISTRY
docker build -t app-ecommerce-carlos .
docker tag app-ecommerce-carlos:latest $ECR_REGISTRY/app-ecommerce-carlos:latest
docker push $ECR_REGISTRY/app-ecommerce-carlos:latest

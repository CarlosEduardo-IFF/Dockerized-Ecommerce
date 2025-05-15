ECR_REGISTRY="237487375715.dkr.ecr.us-east-1.amazonaws.com"
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin $ECR_REGISTRY
docker build -t ecommerce_repository .
docker tag ecommerce_repository:latest $ECR_REGISTRY/ecommerce_repository:latest
docker push $ECR_REGISTRY/ecommerce_repository:latest

export CDK_NEW_BOOTSTRAP=1
npx cdk bootstrap \
  --profile default \
  --cloudformation-execution-policies arn:aws:iam::aws:policy/AdministratorAccess \
  aws://763597864486/eu-central-1


npx cdk deploy --app "mvn -e -q compile exec:java -Dexec.mainClass=isato.cloud.deployment.infrastructure.DeployPipelineApp" --profile default DeployPipelineStack

npx cdk deploy --profile default DeployPipelineStack
export CDK_NEW_BOOTSTRAP=1
npx cdk bootstrap \
  --profile default \
  --cloudformation-execution-policies arn:aws:iam::aws:policy/AdministratorAccess \
  aws://763597864486/eu-central-1
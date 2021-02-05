pipeline {
  agent any
 
  stages {
    stage('stack-submit') {
      environment {
        STACK_NAME = 'manthan-cf-stack'
        S3_BUCKET = 'mathan-bucket555'
      }
      steps {
	      sh 'mvn install'
          sh "aws cloudformation create-stack --stack-name $STACK_NAME --template-file template.yaml --s3-bucket $S3_BUCKET --region 'us-east-1'"
        }
      }
    }
  }
}
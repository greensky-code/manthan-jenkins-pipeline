pipeline {
  agent any
        tools {
            maven 'M3'
        }
 
  stages {
   stage('Build') {
            steps {
				sh 'echo $PATH'
				sh 'mvn -version'
                sh 'mvn clean install'
            }
        }
    stage('configure') {
		  steps {
		  sh "aws s3 mb s3://manthan-bucket555 --region us-east-1"
		 }
	}
    stage('stack-submit') {
      environment {
        STACK_NAME = 'manthan-cf-stack'
        S3_BUCKET = 'manthan-bucket555'
      }
      steps {
          sh "aws cloudformation package --template-file template.yml --s3-bucket $S3_BUCKET --output-template-file template-output.yml"
		  sh "aws cloudformation deploy --template-file template-output.yml --stack-name $STACK_NAME --parameter-overrides S3BucketName=manthan-bucket1000 KinesisDeliveryName=kinesis-realtime-pipeline KinesisDeliveryS3Bucket=kinesis-delivery-bucket100 CreateVPC=true --capabilities CAPABILITY_NAMED_IAM"
        }
      }
    }
  }
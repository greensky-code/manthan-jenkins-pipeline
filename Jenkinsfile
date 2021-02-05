pipeline {
  agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
 
  stages {
   stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    stage('stack-submit') {
      environment {
        STACK_NAME = 'manthan-cf-stack'
        S3_BUCKET = 'mathan-bucket555'
      }
      steps {
          sh "/usr/bin/aws cloudformation package --template-file template.yml --s3-bucket $S3_BUCKET --output-template-file template-output.yml"
		  sh "/usr/bin/aws cloudformation deploy --template-file template-output.yml --stack-name $STACK_NAME --parameter-overrides S3BucketName=manthan-bucket1000"
        }
      }
    }
  }
pipeline {
  agent any
 
  stages {
    stage('Install sam-cli') {
      steps {
        sh 'sudo apt-get install python3.6-venv && venv/bin/pip install aws-sam-cli'
        stash includes: '**/venv/**/*', name: 'venv'
      }
    }
    stage('Build') {
      steps {
        unstash 'venv'
        sh 'venv/bin/sam build'
        stash includes: '**/.aws-sam/**/*', name: 'aws-sam'
      }
    }
    stage('beta') {
      environment {
        STACK_NAME = 'manthan-cf-stack'
        S3_BUCKET = 'mathan-bucket555'
      }
      steps {
        withAWS(credentials: 'jenkins-user', region: 'us-east-1') {
          unstash 'venv'
          unstash 'aws-sam'
          sh 'venv/bin/sam deploy --stack-name $STACK_NAME -t template.yaml --s3-bucket $S3_BUCKET --capabilities CAPABILITY_IAM'
            sh 'mvn install'
        }
      }
    }
  }
}
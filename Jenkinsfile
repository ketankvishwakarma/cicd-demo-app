pipeline {
      agent {
        docker { image 'node:16.13.1-alpine' }
    }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
         stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ketankvishwakarma/cicd-demo-app:01 .'
            }
        }
        stage('Run Docker Image') {
            steps {
                sh 'docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01'
            }
        }
    }
}
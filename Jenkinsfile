pipeline {
      agent {
        docker { image 'maven:3.8-openjdk-16' }
    }
   /*  triggers {
        pollSCM '* * * * *'
    } */
    stages {
        stage('Build') {
            steps {
                sh 'pwd'
                sh 'ls'
                sh 'mvn clean install'
            }
        }
         stage('Test') {
            steps {

                sh 'mvn test'

            }
        }
        stage('Build Docker Image') {
            steps {

                sh 'docker -v $(which docker):/usr/bin/docker build -t ketankvishwakarma/cicd-demo-app:01 .'

            }
        }
        stage('Push Docker Image') {
            steps {

                sh 'docker -v $(which docker):/usr/bin/docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01'

            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            junit 'build/surefire-reports/*.xml'
        }
    }
}
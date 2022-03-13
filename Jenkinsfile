pipeline {
    agent any
  
    stages {
        stage('Docker node test') {
            steps {
                sh './mvnw clean install -DskipTests'
            }
        }
         stage('Test') {
            steps {
                sh './mvnw  test'
            }
        }
        stage('Build Docker Image') {
            steps {
                
                sh """
                docker ps
                docker build -t ketankvishwakarma/cicd-demo-app:01 .
                """
            }
        }
        stage('Push Docker Image') {
            steps {
                sh 'pwd'

                sh """
                    docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01
                """
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            junit 'target/surefire-reports/*.xml'
           /*  cleanWs()
            dir("${env.WORKSPACE}@tmp") {
              deleteDir()
            }
            dir("${env.WORKSPACE}@script") {
              deleteDir()
            }
            dir("${env.WORKSPACE}@script@tmp") {
              deleteDir()
            } */
        }
    }
}
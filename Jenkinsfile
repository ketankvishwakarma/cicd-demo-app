pipeline {
      agent {
        docker { image 'maven:3.8-openjdk-16' }
    }
  
    stages {
        stage('Docker node test') {
            steps {
                sh 'pwd'
                sh 'ls'
                sh 'mvn clean install -DskipTests'
            }
        }
         stage('Test') {
            steps {
                sh 'pwd'
                sh 'mvn test'
            }
        }
        stage('Build Docker Image') {
           
            steps {
                sh 'pwd'
                sh 'docker ps'
                sh 'docker build -t ketankvishwakarma/cicd-demo-app:01 .'
            }
        }
        stage('Push Docker Image') {
            steps {
                sh 'pwd'
                sh 'docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            junit 'target/surefire-reports/*.xml'
            cleanWs()
            dir("${env.WORKSPACE}@tmp") {
              deleteDir()
            }
            dir("${env.WORKSPACE}@script") {
              deleteDir()
            }
            dir("${env.WORKSPACE}@script@tmp") {
              deleteDir()
            }
        }
    }
}
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
                sh 'mvn clean install -DskipTests'
            }
        }
         stage('Test') {
            steps {

                sh 'mvn test'

            }
        }
        stage('Build Docker Image & Push') {
            steps {
               def customImage = docker.build("ketankvishwakarma/cicd-demo-app:01")
                customImage.push()
                customImage.push('latest')
                /* sh 'docker build -t ketankvishwakarma/cicd-demo-app:01 .' */

            }
        }
/*         stage('Push Docker Image') {
            steps {

                sh 'docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01'

            }
        } */
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
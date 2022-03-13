pipeline {
    agent any
    stages {
        stage('Docker node test') {
            steps {
                sh 'echo clean package'
                sh './mvnw clean install -DskipTests'
            }
        }
         stage('Test') {
            steps {
                 sh 'echo test'
                 sh './mvnw  test'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'echo image'
                 sh """
                docker images
                docker build -t ketankvishwakarma/cicd-demo-app:01 .
                docker images
                """ 
            }
        }
        stage('Push Docker Image') {
            steps {
                sh 'pwd'
                sh """
                    docker push ketankvishwakarma/cicd-demo-app:01
                    docker ps
                    docker run -d -p 9000:9000 ketankvishwakarma/cicd-demo-app:01
                    docker ps
                    docker stop \$(docker ps -q) 
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

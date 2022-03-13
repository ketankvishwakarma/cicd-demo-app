pipeline {
  // Assign to docker slave(s) label, could also be 'any'
  agent {
    label 'docker' 
  }

  stages {
    stage('Docker node test') {
      /* agent {
        docker {
          // Set both label and image
          label 'docker'
          image 'node:7-alpine'
          args '--name docker-node' // list any args
        }
      } */
    agent {
        docker { 
            label 'docker'
            image 'maven:3.8-openjdk-16' 
        }
    }
      steps {
        // Steps run in node:7-alpine docker container on docker slave
            sh 'pwd'
            sh 'ls'
            sh 'mvn clean install -DskipTests'
      }
    }

    stage('Docker maven test') {
      agent {
        docker {
          // Set both label and image
          label 'docker'
          image 'maven:3.8-openjdk-16'
        }
      }
      steps {
        // Steps run in maven:3-alpine docker container on docker slave
        sh sh 'mvn test'
      }
    }
  }
} 
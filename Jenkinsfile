pipeline {
    agent any

    stages {
        stage('Build') {
              // Run the maven build
              echo 'Run build'
              whitMaven(
              maven: 'M3'
              ){

              if (isUnix()) {
              	echo 'build in unix environement'
                 sh "mvn -Dmaven.test.failure.ignore clean package"
              } else {
              	echo 'build in win environement'
                 bat(/mvn -Dmaven.test.failure.ignore clean package/)
            	  }
              }
          }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}

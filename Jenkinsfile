pipeline {
    agent any
    tools {
            maven 'MAVEN_HOME'
        }
    stages {
        stage('Build') {
            steps{
                script{
                    // Run the maven build
                      echo 'Run build'
                      if (isUnix()) {
                        echo 'build in unix environement'
                         sh "mvn -Dmaven.test.failure.ignore clean package"
                      } else {
                        echo 'build in win environement'
                         bat "mvn -Dmaven.test.failure.ignore clean package"
                          }
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

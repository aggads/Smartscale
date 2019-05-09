pipeline {
    agent any
    tools {
            maven 'MAVEN_HOME'
        }
    stages {
        stage('Build with unit testing') {
            steps {
                // Run the maven build
                script {
                    echo 'Pulling...' + env.BRANCH_NAME
                    if (isUnix()) {
                        sh "mvn -Dintegration-tests.skip=true clean package"
                        // execute the unit testing and collect the reports
                        //junit '**//*target/surefire-reports/TEST-*.xml'
                        //archive 'target*//*.jar'
                    	} else {
                        bat "mvn -Dintegration-tests.skip=true clean package"
                        //junit '**//*target/surefire-reports/TEST-*.xml'
                        //archive 'target*//*.jar'
                    }
                }
            }
        }
  stage('build && SonarQube analysis') {
              steps {
                  withSonarQubeEnv('SonarQube Scanner') {
                      // Optionally use a Maven environment you've configured already
                      withMaven(maven:'Maven 3.5') {
                          sh 'mvn clean package sonar:sonar'
                      }
                  }
              }
          }
          stage("Quality Gate") {
              steps {
                  timeout(time: 1, unit: 'HOURS') {
                      // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                      // true = set pipeline to UNSTABLE, false = don't
                      // Requires SonarQube Scanner for Jenkins 2.7+
                      waitForQualityGate abortPipeline: true
                  }
              }
              }
        stage('Release and publish artifact') {
            steps {
                // create the release version then create a tag with it , then push releases the released jar
                script {
                    //git url: "ssh://git@github.com:aggads/Smartscale.git"
                    //credentialsId: 'c6f04dbd-f461-491f-a37c-0a9233032b2e'
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        //sh "git remote add origin https://github.com/aggads/Smartscale.git"
                        sh "git tag -f 'v1'"
                        sh "git push -f --tags"
                        sh "mvn -Dmaven.test.skip=true  versions:set  -DgenerateBackupPoms=false"
                        sh "mvn -Dmaven.test.skip=true clean deploy"
                    } else {
                        error "Release is not possible. as build is not successful"
                    }
                }
            }
        }
        stage('Deploy to Acceptance') {
            steps {
                script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        timeout(time: 3, unit: 'MINUTES') {
                            //  deployment job which will take the relasesed version
                            if (releasedVersion != null && !releasedVersion.isEmpty()) {
                                // make the applciation name for the jar configurable
                                def jarName = "application-${releasedVersion}.jar"
                                echo "the application is deploying ${jarName}"
                                // NOTE : DO NOT FORGET to create your UAT deployment jar , check Job AlertManagerToUAT in Jenkins for reference
                                // the deployemnt should be based into Nexus repo
                                build job: 'AApplicationToACC', parameters: [[$class: 'StringParameterValue', name: 'jarName', value: jarName], [$class: 'StringParameterValue', name: 'appVersion', value: releasedVersion]]
                                echo 'the application is deployed !'
                            } else {
                                error 'the application is not  deployed as released version is null!'
                            }
                        }
                    }
                }
            }
        }
    }
}
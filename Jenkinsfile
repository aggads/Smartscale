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
                        junit '**//*target/surefire-reports/TEST-*.xml'
                        archive 'target*//*.jar'
                    	} else {
                        bat "mvn -Dintegration-tests.skip=true clean package"
                        junit '**//*target/surefire-reports/TEST-*.xml'
                        archive 'target*//*.jar'
                    }
                }
            }
        }
        stage('Integration tests') {
            // Run integration test
            steps {
                script {
                    if (isUnix()) {
                        // just to trigger the integration test without unit testing
                        sh "mvn  verify -Dunit-tests.skip=true"
                    } else {
                        bat "mvn verify -Dunit-tests.skip=true"
                    }
                }
            }
        }
        stage('Release and publish artifact') {
            steps {
                // create the release version then create a tage with it , then push to nexus releases the released jar
                script {
                    if (currentBuild.result == null || currentBuild.result == 'SUCCESS') {
                        sh "git tag -f v"
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
stages{
    def mvnHome
    stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://gitlab.com/aggads/spring-jenkins'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage("Staging") {
                	if (isUnix()) {
			         sh "nohup '${mvnHome}/bin/mvn' spring-boot:run -Dserver.port=8989"
			      } else {
			         bat(/"${mvnHome}\bin\mvn" spring-boot:run -Dserver.port=8989 &/)
			      }

            }
            }
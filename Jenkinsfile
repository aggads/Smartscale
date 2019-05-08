pipeline {
    // run on jenkins nodes tha has java 8 label
    agent { label 'java8' }
    // global env variables
    stages {
    	def mvnHome
   stage('Preparation') {
   	  // for display purposes
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      echo 'Run build'
      if (isUnix()) {
      	echo 'build in unix environement'
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
      	echo 'build in win environement'
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
    	  }
	   }
	}
}
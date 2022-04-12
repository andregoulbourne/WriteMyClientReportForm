pipeline {
	agent any
	
	tools {
		maven 'maven_3_8_1'
		jdk 'jdk8'
	}
	
	stages {
		stage ('Compile Stage') {	
			
			steps {
					bat "mvn clean install"
			}
			
		}
		
		stage ('Testing Stage') {	
			
			steps {
					bat "mvn test"
			}
			
		}
		
		stage ('Sonar Analysis Stage') {	
			
			steps {
					bat "mvn sonar:sonar -Dsonar.projectKey=writemyclientreportforms -Dsonar.host.url=http://localhost:9000 -Dsonar.login=15c2d6948ded0009615b9b4c71d32f14c7f9809b"
			}
			
		}
		
	}
	
}
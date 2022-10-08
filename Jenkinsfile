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
					bat "mvn clean verify sonar:sonar -Dsonar.projectKey=WriteMyClientReportForms -Dsonar.host.url=http://localhost:9000 -Dsonar.login=df12abc4ddea3dc13ec5f826e76ca4b499fe2d13"
			}
			
		}
		
	}
	
}
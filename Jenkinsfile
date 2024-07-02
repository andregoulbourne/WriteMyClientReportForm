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
					bat "mvn clean verify sonar:sonar -Dsonar.projectKey=WriteMyClientReportForm -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_38ea5d0bf280afcb0df7a991db9bb03ec497aed6"
			}
			
		}
		
		stage ('UI Testing Stage') {	
			
			when { branch 'master' }
				
			steps {
					bat "mvn -Dtest=com.andre.selenium.**.*.* test surefire-report:report"
			}
			
		}
		
	}
	
}
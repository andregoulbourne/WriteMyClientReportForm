pipeline {
	agent any
	
	tools {
		maven 'maven_3_8_1'
		jdk 'jdk17'
	}
	
	environment {
    	BRANCH_NAME = "${GIT_BRANCH.split("/")[1]}"
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
			
			when { expression { return env.BRANCH_NAME  ==~ "main.*"} }
				
			steps {
					bat "mvn -Dtest=com.andre.selenium.**.*.* test surefire-report:report"
			}
			
		}
		
	}
	
	post {
	
		success {
			publishHTML (
				target : [
					allowMissing: false,
	 				alwaysLinkToLastBuild: true,
	 				keepAll: false,
	 				reportDir: 'target/site',
	 				reportFiles: 'surefire-report.html',
	 				reportName: 'Selenium UI Test Report',
	 				reportTitles: ''
	 		])
		}
	
	}
	
	
}
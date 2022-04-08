# WriteMyClientReportForm
An application with spring boot back end and angular front end to write client report comments.

## Running the app

### Run With Docker
* cd <"Project Directory">  
* docker build -t <"image name"> .  
* docker run -p8081:8081 <"image tag">   
  
### Run Locally
* cd <"angular sub folder in project">  
* ng build  
* Copy everything in angular/dist/WriteClientReport Into the the src/main/webapp folder  
* mvn spring-boot:run  


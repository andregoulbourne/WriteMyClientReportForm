# WriteMyClientReportForm
A application with spring boot back end and angular front end to write client report comments.   
No data base reading and changing a csv file for the data. Containerized using docker.

# Technologies Used
* Java
* Spring Framework
* Maven
* CSV
* TypeScript
* Angular
* CSS
* Docker

# Features
* Editable grid using ag-grid
* Dynamically rendering comment on selection of a row and hit of the button

## Running the app

### Run With Docker
* cd <"Project Directory">  
* docker build -t <"image name"> .  
* docker run -p8081:8081 <"image tag">   
  
### Run Locally
* cd <"angular sub folder in project">  
* npm install
* mvn spring-boot:run  

![image](https://user-images.githubusercontent.com/84467369/162369704-84d13d40-b77a-41b2-b2e8-10d749ab1dd9.png)
![image](https://user-images.githubusercontent.com/84467369/162369748-33f43336-ef7f-4b6e-b63f-153bd684bf6d.png)



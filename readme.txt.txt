This project is comprised of following 

 1. Spring Boot 
 2. Derby embedded database
 3. Simple Thymleaf 
 
The project contains the following test cases

1. http://localhost:8080/distance
    To calculate the distance between the 2 planets 


2. http://localhost:8080/route
    To add the route from one point to other

3. http://localhost:8080/planeptnames
    To add the planets added from the page

4. http://localhost:8080/planet

5. Web Service can be invoked as 
    http://localhost:8080/ws
       <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
         <Body>
        <SendRouteRequest xmlns="http://www.example.org/GraphService">
            <sendRouteRequest>
                <name>Earth</name>
                <distance>[string]</distance>
            </sendRouteRequest>
            <destRouteRequest>
                <name>Pluto</name>
                <distance>[string]</distance>
            </destRouteRequest>
        </SendRouteRequest>
    </Body>
</Envelope>
     
   
 Please run the application as target\java -jar instellar-0.0.1-SNAPSHOT.jar

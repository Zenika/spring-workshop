<div class="pb"></div>

# LAB 7

## Spring MVC

Let's expose our services to the world !

- Turn the application into a Web application
  - Bootstrap the *petshop-application* to be a war module managed thru Spring MVC
  - Create a *RestController* mapped to the root of your webapp ("/") to return the String "Hello world"
  - Deploy it to a Tomcat server. *apache-tomcat-9.0.31.zip* is available in *resources/LAB7* 
  - Test in browser

- Create REST controllers to expose ower and pet data (as */owners* and */pets*)

- Add functionality
  - Add *findOne(int id)* to your services
  - Expose as */owners/{id}* and */pets/{id}*
  - Return 404 if not found
  
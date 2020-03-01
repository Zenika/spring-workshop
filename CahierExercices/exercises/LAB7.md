<div class="pb"></div>

# LAB 7

## Spring MVC

Let's expose or services to the world !

- Turn the application into a Web application
  - Deploy it to a Tomcat server. *apache-tomcat-9.0.31.zip* is available in *resources/LAB7* 
  - Create REST controllers to expose ower and pet data (as */owners* and */pets*)
  - Test in browser
- Add functionality
  - Add *findOne(int id)* to your services
  - Expose as */owners/{id}* and */pets/{id}*
  - Return 404 if not found
  
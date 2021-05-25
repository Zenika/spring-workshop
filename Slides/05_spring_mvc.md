# Spring MVC

<!-- .slide: class="page-title" -->



## Table of content

<!-- .slide: class="toc" -->

- [Clean code](#/1)
- [Testing](#/2)
- [Maven](#/3)
- [Spring Core](#/4)
- **[Spring MVC](#/5)**
- [Spring Security](#/6)
- [Spring Data](#/7)
- [Spring Batch](#/8)
- [Spring Boot](#/9)



## MVC model

MVC: *M*odel-*V*iew-*C*ontroller. This pattern is used to ensure a separatation of application concerns (layers) for user interfaces.

- *Model*: Model represents an object or Java POJO carrying data
- *View*: View represents the visualization of the data that model contains
- *Controller*: Controller acts on both model and view. It controls the data flow into model object and updates the view whenever data changes. It keeps view and model separate



## MVC model

<figure>
    <img src="ressources/05_spring_mvc/mvc.svg" alt="MVC" />
</figure>



## Spring Web MVC

- Spring Web MVC is the web framework implementation of the MVC pattern in the Spring ecosystem
- Widely used for fully server-based Java web applications (Struts, JSP, templating engines...)
- But also ideal for lighter REST based backends !

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
<dependency>
```



## Configuring Spring Web MVC

- Create a Spring configuration with the *@EnableWebMvc* annotation

```java
@Configuration
@EnableWebMvc
public class WebConfig {
    ...
}
```

- Create a Spring context, and register the Spring MVC dispatcher servlet

```java
public class AppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext ctx = 
            new AnnotationConfigWebApplicationContext();
        ctx.register(PetshopConfig.class);
        ctx.setServletContext(container);
        ServletRegistration.Dynamic servlet = 
            container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
```



## Configuring Spring Web MVC

You may provide an implemention of *WebMvcConfigurer* to customize Spring MVC behavior

```java
@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    // Register JSON support for REST
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}

```

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
```



## Spring MVC controllers

- The controller is the entry point of your application: All user traffic is eventually redirected to a controller.
- A Spring controller is annotated with the *@Controller* annotation
- *@Controller* is a Spring stereotype, therefore your controller is a Spring bean and benefits from dependency injection

```java
@Controller
public class MyController {

    @Autowired
    private MyService myService;

    ...
}
```



## Using Spring MVC for REST

- By default, Spring MVC is auto configured to work for MVC flows, which means it's expected to redirect to a view (jsp, template...) rather than return final data
- Use the *@ResponseBody* annotation to tell Spring that a controller or one of its methods handles the response data directly

```java
@Controller
@ResponseBody
public class MyController {
    ...
}
```

- The *@RestController* annotation merges both *@Controller* and *@ResponseBody*

```java
@RestController
public class MyRestController {
    ...
}
```



## Spring MVC controllers: binding traffic

- To bind your web traffic to your controller, you need to add annotations
- *@RequestMapping* can be specified on your controller class to define a root uri for your controller

```java
@RestController
@RequestMapping(value = "/pets")
public class PetController {
    ...
}
```



## Spring MVC controllers: binding traffic

- A set of method annotations allow you to fine tune the url endpoints for each method of your controller
  - *@GetMapping*
  - *@PostMapping*
  - *@DeleteMapping*
  - and more...

```java
@RestController
public class PetController {
    
    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public Pets getAllPets() {
        return petService.findAll();
    }
}
```



## Spring MVC controllers: retrieving data from URI

- Use the *@PathVariable* annotation to retrieve data in the URI
- Spring automatically converts to the appropriate type

```java
@RestController
@RequestMapping(value = "/pets")
public class PetController {
    
    @Autowired
    private PetService petService;

    // Handling http://yourserver/pets/123
    @GetMapping("/{id}")
    public Pet getPet(@PathVariable("id") int id) {
        return petService.findById(id);
    }
}
```



## Spring MVC controllers: retrieving data from request parameters

Use the *@RequestParam* annotation to retrieve data from the request parameters

```java
@RestController
@RequestMapping(value = "/pets")
public class PetController {
    
    @Autowired
    private PetService petService;

    // Handling http://yourserver/pets?id=123
    @GetMapping
    public Pet getPet(@RequestParam("id") int id) {
        return petService.findById(id);
    }
}
```



## Spring MVC controllers: retrieving data from request headers

Use the *@RequestHeader* annotation to retrieve data from the HTTP headers

```java
@RestController
@RequestMapping(value = "/pets")
public class PetController {
    
    private Logger log = ...;

    @Autowired
    private PetService petService;

    // log user agent
    @GetMapping
    public Pet getAllPets(@RequestHeader("User-Agent") String userAgent) {
        log.info("User agent: {}", userAgent);
        return petService.findAll();
    }
}
```



## Returning a HTTP status code

It's often desirable to return specific HTTP status codes 

- *200*: OK (default code)
- *404*: Not found
- *500*: Internal error
- **org.springframework.http.HttpStatus** holds convinient constants for all HTTP codes
- Use *ResponseEntity* as a return type to specify error codes (and an optional body)

```java
    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable("id") int id) {
            Pet pet = petService.findById(id);
            if (pet == null) {
                // Utility method for 404
                return ResponseEntity.notFound().build(); 
            }
            return ResponseEntity.ok(pet);  // 200, with serialized pet as body
    }
```



<!-- .slide: class="page-tp7" -->



## Error handling

- Code may get complex or too verbose when dealing with error handling
- Business logic gets flooded into technical red tape

```java
    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable("id") int id) {
        try {
            Pet pet = petService.findById(id);
            if (pet == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(pet);
        } catch (TechnicalException e) {
            log.error("An error has occured", e);
            return
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
```



## ExceptionHandler

- The solution: Exception handlers
- Use *@ExceptionHandler* method annotation
- Allow to mutualize error handling between controller methods
- Simpler, more focused code



## ExceptionHandler: An example

```java
    @GetMapping("/pets/{id}")
    public Pet getPet(@PathVariable("id") int id) {
        Pet pet = petService.findById(id);
        if (pet == null) {
            throw new NotFoundException();
        }
        return pet;
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity technicalExceptionHandler(TechnicalException e) {
        log.error("An error has occured", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }
```



## A step beyond: @ControllerAdvice

- Code even more business focused controllers by using *@ControllerAdvice*
- Creates special technical controllers dedicated to mutualizing *@ExceptionHandler*
- Creates backend-wide error handling



## @ControllerAdvice: An example

```java
@ControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity technicalExceptionHandler(TechnicalException e) {
        log.error("An error has occured", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }
}

@RestController
public class PetController {
    @GetMapping("/pets/{id}")
    public Pet getPet(@PathVariable("id") int id) {
        Pet pet = petService.findById(id);
        if (pet == null) {
            throw new NotFoundException();
        }
        return pet;
    }
}
```



## RestTemplate

- A handy HTTP client: *RestTemplate*
- Sometime's it's easier to just use RestTemplate rather than generate a fully fledged API client
- Maybe you don't have the OpenAPI specs to generate code with, or maybe it's just that one little call you need

```java
String url = "http://www.myserver.com/pets/1";

RestTemplate restTemplate = new RestTemplate();

// Nice and easy REST get
Pet pet = restTemplate.getForObject(url, Pet.class);

// More "hands-on" form POST
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
map.add("id", "1");
ResponseEntity<String> response = restTemplate.postForEntity(url, 
    new HttpEntity<>(map, headers) , 
    String.class);
```




<!-- .slide: class="page-questions" -->

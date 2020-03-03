# Spring Security

<!-- .slide: class="page-title" -->



## Table of content

<!-- .slide: class="toc" -->

- [Clean code](#/1)
- [Testing](#/2)
- [Maven](#/3)
- [Spring Core](#/4)
- [Spring MVC](#/5)
- **[Spring Security](#/6)**
- [Spring Data](#/7)
- [Spring Batch](#/8)
- [Spring Boot](#/9)



## Spring Security

- Spring Security helps you secure your application
- Very powerful and flexible, which sometimes makes it a bit hard to configure just right

```xml
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
</dependency>
```

- Many sub modules for more advanced security
  - *spring-security-openid*
  - *spring-security-oauth2*
  - *spring-security-kerberos*
  - And more !



## Authentification and authorization.

- Spring Security helps you deal with both use authentication and habilitation
  - *Authentication*: **Who** is accessing the resource ?
  - *Authorization*: **What** is he allowed to do ?
- For instance, your user information could be handled by an LDAP server
  -  Your user is *authenticated* if he has provided the right user/password
  -  His *authorizations* (or *habilitations*) are determined thru his LDAP groups



## Securing your application

Spring Security leverages one the traditionnal tools of Spring: *@Configuration* and provides a variety of Adapter classes to help you set up your security

- *@EnableWebSecurity* enable Spring Security’s web security support and provide the Spring MVC integration
- The *WebSecurityConfigurerAdapter* is a good configuration adapter for many basic use cases and features several expansion points to customize according to your needs
  - The **configure(HttpSecurity http)** method provides a fluent interface to finely configure endpoint security
  - The **configure(WebSecurity web)** method allows you to whitelist specific endpoints on which security is entirely disabled (bypasses security filters)



## Securing your application

- To finalize Spring Security's initialization you need to register the *DelegatingFilterProxy* into the servlet configuration
- An easy way to do it is simply to have an instance of *AbstractSecurityWebApplicationInitializer* in your classpath

```java
public class PetshopSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
```



## AuthenticationManager

The core of the security is delegated to an AuthenticationManager

- A very simple interface :
```java
	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;
```
- Many different implementations
  - *CustomAuthenticationManager* (default implementation, delegates to a *UserDetailsService*)
  - *OAuth2AuthenticationManager*
  - ...



## UserDetailsService

This component is used by *CustomAuthenticationManager* to retrieve user information

- A simple interface :
```java
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
```
- UserDetails holds various user information, among them his authorities
- Several implementations provided by Spring
  - *InMemoryUserDetailsManager*
  - *JdbcUserDetailsManager*
  - ...



## A basic security configuration

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    // Let's protect our endpoints
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll() // this one is public
				.anyRequest().hasRole("USER") // the rest is protected
				.and()
			.formLogin() // spring can autogenerate a login form for us !
				.loginPage("/login") // located here
				.permitAll()         // should be public, obviously
				.and()
			.logout()
				.permitAll();        // logout url is public too
	}
    ...
```



## A basic security configuration

```java
    ...
    // We're using the default CustomAuthenticationManager 
    //  and just need to specify an instance of UserDetailsService
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")   // credentials (for authentication)
				.password("password")
				.roles("USER")      // habilitations (for authorization)
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
```



## Securing services

- Securing HTTP endpoint may not be sufficient in some cases
- Spring Security provides with various annotations to secure code
  - *@Secured*
```java
@RolesAllowed("ROLE_SHOP_OWNER")    // Be sure to add the "ROLE_" prefix !
public String sell(Pet pet) {
    //...
}
```

  - *@PreAuthorize* and *@PostAuthorize*: More powerful but more verbose
```java
@PreAuthorize("hasRole('ROLE_SHOP_OWNER')")
public String sell(Pet pet) {
    //...
}
```

- These annotations can be located on a method or an entire class



<!-- .slide: class="page-tp8" -->



<!-- .slide: class="page-questions" -->

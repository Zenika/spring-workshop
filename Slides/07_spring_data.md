# Spring Data

<!-- .slide: class="page-title" -->



## Table of content

<!-- .slide: class="toc" -->

- [Clean code](#/1)
- [Testing](#/2)
- [Maven](#/3)
- [Spring Core](#/4)
- [Spring MVC](#/5)
- [Spring Security](#/6)
- **[Spring Data](#/7)**
- [Spring Batch](#/8)
- [Spring Boot](#/9)



## Spring Data

Spring Data is Spring's framework for data access

- Greatly simplifies all your DAO code
- Removes most of the boilerplate code
- Fully fledged CRUD in minutes !
- Very flexible
- Provides a unified approach to many types of data backends
  - Databases
  - LDAP
  - Elasticsearch
  - And many more



## Spring Data main modules

- Spring Data JPA
- Spring Data JDBC
- Spring Data KeyValue
- Spring Data LDAP
- Spring Data MongoDB
- Spring Data Redis
- Spring Data REST
- Spring Data for Apache Cassandra
- Spring Data for Apache Geode
- Spring Data for Apache Solr
- Spring Data for Pivotal GemFire



##  Spring Data community modules

- Spring Data Aerospike
- Spring Data ArangoDB
- Spring Data Couchbase
- Spring Data Azure Cosmos DB
- Spring Data Cloud Datastore
- Spring Data Cloud Spanner
- Spring Data DynamoDB
- Spring Data Elasticsearch
- Spring Data Hazelcast
- Spring Data Jest
- Spring Data Neo4j
- Spring Data Vault



## Repositories

The core of Spring Data is *Repositories*

- Generic interfaces that define data access behaviors (save, findById, delete...)
- Create interfaces that extend the core Spring Data interfaces to specialize them for your entities
- Tag them with *@Repository* so Spring will detect them as components
- Spring Data will "magically" generate implementations for you at runtime

```java
@Repository
public interface PetDao extends CrudRepository<Pet, Integer> {
}
```

That's it ! CRUD for pets !



## Free CRUD with CrudRepository

CrudRepository provides with easy CRUD functionnalities right out of the box

```java
public interface CrudRepository <T, ID> extends Repository<T,ID> {
    <S extends T> S save(S s);

    <S extends T> Iterable<S> saveAll(Iterable<S> iterable);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> iterable);

    long count();

    void deleteById(ID id);

    void delete(T t);

    void deleteAll(Iterable<? extends T> iterable);

    void deleteAll();
}
```



## Free paging with PagingAndSortingRepository

*PagingAndSortingRepository* is a sub interface of *CrudRepository* that provides you with even more functionnalities

```java
public interface PagingAndSortingRepository <T, ID> extends CrudRepository<T,ID> {
    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);
}
```

```java
@Repository
// Now we get free paging and sorting
public interface PetDao extends PagingAndSortingRepository<Pet, Integer> {
}
```



## Even more magic with query methods

- Spring Data uses Java reflection and works on a convention over configuration principle to infer behaviour and generate code automatically from methods signatures you add to your Repositories

```java
@Repository
public interface PetDao extends CrudRepository<Pet, Integer> {
    List<Pet> findByName(String name);
}
```

- From this, Spring Data will automatically generate a fully implemented class for you to use.

```java
@Service
public class PetService {
    @Autowired
    private PetDao petDao;

    public void process() {
        Pet fido = petDao.findByName("Fido");
        ...
        petDao.save(fido);
    }
}
```



## Even more magic with query methods

Query methods can be simple or very rich

```java
public interface PersonRepository extends Repository<User, Long> {

  List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);
  List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
  List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);
  List<Person> findByLastnameIgnoreCase(String lastname);
  List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
  List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
  List<Person> findByLastnameOrderByFirstnameDesc(String lastname);

}
```



## Composite repositories

- Sometimes queries get to complicated and a method signature is not enough
- Spring Data allows you to supplement your Repositories with custom code
  - Create a side interface defining your custom behaviour
  - Have your repository extend this interface
  - Implement it
  - Spring Data will embbed your implementation into the repository

```java
public interface PetsCustomBehavior {
    public List<Pet> findAllSomething();
}

@Repository
public class PetsCustomBehaviorImpl implements PetsCustomBehavior {
    public List<Pet> findAllSomething() { ... }
}

@Repository
public interface PetDao extends CrudRepository<Pet, Integer>, PetsCustomBehavior {
}
```



## Spring Data JPA

- Spring Data implementation for JPA

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
</dependency>
```

- You need to add a JPA implementation to your project (Hibernate, Toplink) : Spring Data acts as a front end for JPA

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
</dependency>
```



## Enabling Spring Data JPA

This is simply done by tagging a Spring configuration class with the *@EnableJpaRepositories* annotation

```java
@Configuration
@EnableJpaRepositories
public class MyConfig {

}
```



## JPA entities

Your entities need to be annotated with JPA annotations
  
```java
@Entity
@Table(name="users")
public  class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="family_name", length=50)
    private String name;

    private String fistName;

    private int age;

    @Enumerated(EnumType.ORDINAL)
    private Civility civility;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Lob
    private byte[] photo;
}
```



## JPA entities : 0-1 relationships

With JPA you can easily define *one-to-one* relationships using annotations

```java
@Entity
public class Pet {

   @OneToOne
   private Tatoo tatoo;
}
```



## JPA entities : 0-N relationships

With JPA you can easily define *many-to-one* and/or *one-to-many* relationships using annotations

```java
@Entity
public class Pet {

   @ManyToOne
   private Person owner;
}

@Entity
public class Person {
    
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}
```



## JPA entities : N-N relationships

- With JPA you can define *many-to-many* relationships using annotations.
- Many-to-many relationships are a little more complicated because they need an extra table to store relationships
- Fortunately, JPA also handles that for you

```java
@Entity
public class Student {
    @ManyToMany
    @JoinTable(
    name = "student_courses", 
    joinColumns = @JoinColumn(name = "student_id"), 
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;
}

@Entity
public class Course {
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}

```



## JpaRepository

Spring Data JPA offers additionnal behaviors thru *JpaRepository*

```java
public interface JpaRepository <T, ID> extends PagingAndSortingRepository<T,ID>, QueryByExampleExecutor<T> {

    void flush();

    <S extends T> S saveAndFlush(S s);

    void deleteInBatch(Iterable<T> iterable);

    void deleteAllInBatch();

    T getOne(ID id);

}
```



## Custom JPA queries with @Query

- Spring Data JPA offers the *@Query* annotation for custom queries
- Queries are expressed using the JPQL language

```java
@Query("SELECT u FROM User u WHERE u.status = 1")
Collection<User> findAllActiveUsers();
```

- Unless you want pure SQL (*nativeQuery = true*)

```java
@Query(
  value = "SELECT * FROM USERS u WHERE u.status = 1", 
  nativeQuery = true)
Collection<User> findAllActiveUsersNative();
```



<!-- .slide: class="page-tp8" -->




## Another Spring Data example

- Spring Data covers many more data backends than JPA
- Let's have a look at *Spring Data Elasticsearch*

```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-elasticsearch</artifactId>
</dependency>
```



## Spring Data Elasticsearch entities

- Just like JPA, you have a set of annotations to enhance your model
- *@Document* is used to define the Elasticsearch index holding the data

```java
@Document(indexName = "books")
public class Book {

  @Id 
  private String id;

  private String title;

}
```



## Spring Data Elasticsearch repositories

Spring Data Elasticsearch provides base repositories for you to extend

```java
public interface ElasticsearchCrudRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
}

public interface ElasticsearchRepository<T, ID> extends ElasticsearchCrudRepository<T, ID> {

	<S extends T> S index(S entity);

	<S extends T> S indexWithoutRefresh(S entity);

	Iterable<T> search(QueryBuilder query);

	Page<T> search(QueryBuilder query, Pageable pageable);

	Page<T> search(SearchQuery searchQuery);

	Page<T> searchSimilar(T entity, String[] fields, Pageable pageable);

	void refresh();

	Class<T> getEntityClass();
}
```



## Spring Data Elasticsearch repositories

Just like Spring Data JPA or any other Spring Data module, you get query methods with conventions over configuration

```java
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    Page<Book> findByName(String name, Pageable pageable);

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"name\" : \"?0\"}}}}")
    List<Book> findByAuthor(String name);
}

@Service
public BookService {

    @Autowired
    private BookRepository bookrepository;

    public List<Book> getBooks(String author) {
        return bookrepository.findByAuthor(author);
    }
}

```



## Enabling Spring Data Elasticsearch

This is done by tagging a Spring configuration class with the *@EnableElasticsearchRepositories* annotation

```java
@Configuration
@EnableElasticsearchRepositories
public class MyConfig {

}
```



<!-- .slide: class="page-questions" -->

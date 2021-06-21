<div class="pb"></div>

# LAB 9

## Spring Batch

Let's create a Spring batch process

- Create a new child module named *petshop-batch*
- Create a Spring Batch which does the following:
  - Read file *resources/LAB10/pets.csv* and produce a String[]
    - Use a *org.springframework.batch.item.file.FlatFileItemReader* as the ItemReader
    - Be sure to set its *Resource* and *LineMapper*
  - Implement an *ItemProcessor* to turn each record into a *PetEntity*
  - Implement an *ItemWriter* that leverages *PetDao* to save the records into the database
- Check the result thru your */pets* REST endpoint
- You'll need the following dependencies

```xml
<dependency>
    <groupId>org.springframework.batch</groupId>
    <artifactId>spring-batch-core</artifactId>
    <version>4.2.1.RELEASE</version>
</dependency>
```
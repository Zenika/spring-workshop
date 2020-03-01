<div class="pb"></div>

# LAB 10

## Spring Batch

Let's create a Spring batch process

- Create a new child module named *petshop-batch*
- Create a Spring Batch which does the following:
  - Read file *resources/LAB10/pets.csv* and produce a String[]
    - Use *org.springframework.batch.item.file.FlatFileItemReader*
    - Be sure to set its *Resource* and *LineMapper*
  - Use an *ItemProcessor* to turn each record into a *PetEntity*
  - Write an *ItemWriter* that leverages *PetDao* to save the records into the database
- Check the result thru your */pets* REST endpoint

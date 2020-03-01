import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class Petshop {

    public static void main(String[] args) {
        new Petshop().run();
    }

    private void run() {
        HashMap<Integer, HashMap<String, Object>> pets = readAllPets();
        HashMap<Integer, HashMap<String, Object>> owners = readAllOwners();

        System.out.println("Listing pets");
        pets.values().forEach(p -> {
            System.out.println(p.get("id") + " -> " + p.get("name"));
        });

        System.out.println("Listing owners");
        owners.values().forEach(o -> {
            System.out.println(o.get("id") + " -> " + o.get("name"));
        });
    }

    private HashMap<Integer, HashMap<String, Object>> readAllPets() {
        HashMap<Integer, HashMap<String, Object>> allPets = new HashMap<>();
        try {
            List<String> petStrings = IOUtils.readLines(new FileInputStream("pets.csv"), StandardCharsets.UTF_8);
            for (String petString : petStrings) {
                String[] petData = petString.split(";");
                HashMap<String, Object> pet = new HashMap<>();
                int id = Integer.parseInt(petData[0]);
                pet.put("id", id);
                pet.put("name", petData[1]);
                pet.put("race", petData[2]);
                allPets.put(id, pet);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return allPets;
    }

    private HashMap<Integer, HashMap<String, Object>> readAllOwners() {
        HashMap<Integer, HashMap<String, Object>> allOwners = new HashMap<>();
        try {
            List<String> ownerStrings = IOUtils.readLines(new FileInputStream("owners.csv"), StandardCharsets.UTF_8);
            for (String ownerString : ownerStrings) {
                String[] ownerData = ownerString.split(";");
                HashMap<String, Object> owner = new HashMap<>();
                int id = Integer.parseInt(ownerData[0]);
                owner.put("id", id);
                owner.put("name", ownerData[1]);
                allOwners.put(id, owner);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return allOwners;
    }

}

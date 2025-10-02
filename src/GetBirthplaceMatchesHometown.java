import java.io.*;
import java.util.List;
import java.util.Scanner;

public class GetBirthplaceMatchesHometown {
    // 9. point
    public static void GetBirthplaceMatchesHometown(List<Person> people, String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String id = line.trim();
                for (Person person : people) {
                    if (id.equals(person.idperson)){
                        String home = person.birthplace;
                        if (home == null || home.isEmpty()) continue;
                        System.out.println("These are the people born in " + home + ":");
                        for  (Person person1 : people) {
                            if (home.equals(person1.birthplace)){
                                System.out.println(person1.name + " " + person1.lastName + ", where they studied: " + person1.studiedAt);
                            }
                        }
                    }

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
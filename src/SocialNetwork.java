import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


public class SocialNetwork {

    public static void main(String[] args) throws IOException {
        LocalDate birthdate = LocalDate.of(1997,8,27);
        addPerson("EdnTxu","Edurne","Gorostiza",birthdate,"female","Donostia",
                "Donostia","UPV/EHU","Inditex","Titanic;Avengers","G6125154");

    }
    //2. point from the project manual
    public static void addPerson(String idperson, String name, String lastName, LocalDate birthdate,
        String gender, String birthplace, String home, String studiedAt,
        String workplaces, String films, String groupcode) throws IOException {
        Person newPerson2 = new Person(idperson, name, lastName, birthdate, gender, birthplace,
        home, studiedAt, workplaces, films, groupcode);
        File file = new File("peopleG6125107.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(newPerson2.toString());
        System.out.println("Wrote to " + file);
        writer.close();
    }
}


import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;


public class SocialNetwork {

    public static void main(String[] args) throws IOException {
        addPerson("EdnTxu","Edurne","Gorostiza","1991,04,22","female","Donostia",
                "Donostia","UPV/EHU","Inditex","Titanic;Avengers","G6125154");

    }
    //2. point from the project manual
    public static void addPerson(String idperson, String name, String lastName, String birthdate,
        String gender, String birthplace, String home, String studiedAt,
        String workplaces, String films, String groupcode) throws IOException {
        Person newPerson2 = new Person(idperson, name, lastName, birthdate, gender, birthplace,
        home, studiedAt, workplaces, films, groupcode);
        File file = new File("socialNetworkG6125107.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(newPerson2.toString());
        System.out.println("Wrote to " + file);
        writer.close();
    }

    public static void addPeopleFromFile(String filePath){
        try {
            File fname = new File(filePath);
            Scanner input2program = new Scanner(fname);
            while (input2program.hasNext()) {
                String stringint = input2program.nextLine();

            }
            System.out.println(i);
            input2program.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


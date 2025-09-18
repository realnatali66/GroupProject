import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CliqueGenerator {

//    newClique is an array containing objects that stores a person's identifiers
//    Function adds the given names in pairs to the friends.txt file so
//     that everyone in the given clique are friends
    public static void generateClique(Person[] newClique) {
        for (int i = 0; i < newClique.length; i++) {
            try {
                String path = "friendsG6125107.txt";
                File fname = new File(path);
                Scanner input2program = new Scanner(fname);
                while (input2program.hasNext()) {
                    String stringint = input2program.nextLine();
                    i = i + 1;
                }
                System.out.println(i);
                input2program.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



            }


    }


}

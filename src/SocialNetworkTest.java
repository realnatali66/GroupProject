import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class SocialNetworkTest {
    public static void main(String[] args) throws Exception {
        //SocialNetwork sn = new SocialNetwork();

        /*String peopleCsv = String.join("\n",
                "A,Alice,Anderson",   // id, name, lastName
                "B,Bob,Brown",
                "C,Cara,Clark"
        );
        Path peoplePath = Paths.get("people_test2.txt");
        Files.write(peoplePath, peopleCsv.getBytes());
        System.out.println("people_test.txt @ " + peoplePath.toFile().getAbsolutePath());

    *//*    // Load people from that file
        sn.addPeopleFromFile(peoplePath.toString());
        System.out.println("People in memory: " + sn.getPeople().size());
        for (Person p : sn.getPeople()) System.out.println(" - " + p.idperson);

        if (sn.getPeople().isEmpty()) {
            System.out.println("!! No people loaded. Verify path/delimiter/header handling in loadPeopleFile.");
            return;
        }

        // Create a friendships file and load it
        String friendsTxt = String.join("\n",
                "A,B",     // valid
                "B,C",     // valid
                "X,C",     // X unknown -> skipped
                "A,",      // malformed
                " ,B",     // malformed
                "B,C"      // duplicate -> should not duplicate
        );
        Path friendsPath = Paths.get("friends_test.txt");
        Files.write(friendsPath, friendsTxt.getBytes());
        System.out.println("friends_test.txt @ " + friendsPath.toFile().getAbsolutePath());
*/
        SocialNetwork sn1 = new SocialNetwork();
        sn1.addPeopleFromFile("55people.txt");
        sn1.loadFriendshipsFile("55friendships.txt");


        // Testing of shortest and longest chains
        System.out.println("------friends -------");
        for (Person person : sn1.getPeople()) {
            System.out.print(person.idperson + " -> ");
            for (String id : person.getFriendsID()) System.out.print(id + " ");
            System.out.println();
        }
        System.out.println("-----");
        System.out.println(sn1.findById(sn1.getPeople().get(2).idperson));

        GroupsWithSameProfile.printGroups(sn1.getPeople());
        System.out.println("--------------------");
            System.out.println(sn1.getPeople().get(2).idperson + " 's Freinds: " + sn1.getPeople().get(2).getFriendsID());


        FindLongestConnectionBetween2People.chainFinder( "And02", "Gonz01");
    }
}

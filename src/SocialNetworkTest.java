import java.nio.file.*;

public class SocialNetworkTest {
    public static void main(String[] args) throws Exception {
        SocialNetwork sn = new SocialNetwork();

        String peopleCsv = String.join("\n",
                "A,Alice,Anderson",   // id, name, lastName
                "B,Bob,Brown",
                "C,Cara,Clark"
        );
        Path peoplePath = Paths.get("people_test.txt");
        Files.write(peoplePath, peopleCsv.getBytes());
        System.out.println("people_test.txt @ " + peoplePath.toFile().getAbsolutePath());

        // Load people from that file
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

        sn.loadFriendshipsFile(friendsPath.toString());

        // Print result
        for (Person person : sn.getPeople()) {
            System.out.print(person.idperson + " -> ");
            for (Person f : person.getFriends()) System.out.print(f.idperson + " ");
            System.out.println();
        }
    }
}

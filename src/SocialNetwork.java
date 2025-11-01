import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SocialNetwork {
    private static final List<Person> people = new ArrayList<>();

    public List<Person> getPeople() { return people; }

    // 2. point from the project manual
    public static void addPerson(String idperson, String name, String lastName, String birthdate,
                                 String gender, String birthplace, String home, String studiedAt,
                                 String workplaces, String films, String groupcode) {

        if (idperson == null) return;

        // Clean and validate ID
        String id = idperson.replaceAll("\\p{C}", "").trim();

        // Identifier is compulsory and must be unique
        if (id.isEmpty() || findById(id) != null) return;

        // Only adds person to the in-memory list 'people'
        Person newPerson = new Person(id, name, lastName, birthdate, gender, birthplace, home,
                studiedAt, workplaces, films, groupcode, new ArrayList<>());
        people.add(newPerson);
    }

    public static Person findById(String idRaw) {
        String id = idRaw == null ? "" : idRaw.replaceAll("\\p{C}", "").trim();
        for (Person p : people) if (id.equals(p.idperson)) return p;
        return null;
    }
    //point 3


    public void addPeopleFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] t = line.split(",", -1);
                String id = t.length>0 ? t[0].replaceAll("\\p{C}", "").trim() : "";
                String name = t.length>1 ? t[1].trim() : "";
                String lastName = t.length>2 ? t[2].trim() : "";
                String birthdate = t.length>3 ? t[3].trim() : "";
                String gender = t.length>4 ? t[4].trim() : "";
                String birthplace = t.length>5 ? t[5].trim() : "";
                String home = t.length>6 ? t[6].trim() : "";
                String studiedAt = t.length>7 ? t[7].trim() : "";
                String workplaces = t.length>8 ? t[8].trim() : "";
                String films = t.length>9 ? t[9].trim() : "";
                String groupcode = t.length>10? t[10].trim(): "";

                if (id.isEmpty()) continue;
                addPerson(id, name, lastName, birthdate, gender, birthplace, home,
                        studiedAt, workplaces, films, groupcode);
            }
        }
    }

    // point 5
    // File format: idA,idB
    // ignore the pair if either id is not in the network, friendship is mutual
    public void loadFriendshipsFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Counters for reporting and debugging

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                // Split by comma. -1 keeps trailing empty values
                String [] t = line.split(",", -1);

                // If t length is not at least 2, it’s a malformed line
                if (t.length != 2) {
                    continue; }

                // Extract the two person IDs
                String a = t[0].trim();
                String b = t[1].trim();

                // If either ID is empty, treat as malformed
                if (a.isEmpty() || b.isEmpty()) {
                    continue; }

                // Look up persons in the network
                Person pa = findById(a);
                Person pb = findById(b);

                // If one of the persons does not exist, skip this friendship
                if (pa == null || pb == null) {
                    continue;
                }

                // reciprocal, no duplicates
                pa.addFriend(pb);
                pb.addFriend(pa);
            }
        }
    }


    //not needed unless for testing
    public static void main(String[] args) {

        addPerson("Michal34", "Michal", "Smith", "", "", "", "", "", "", "", "");
        addPerson("Xabi112", "Xabi", "Jones", "", "", "", "", "", "", "", "");
        addPerson("EdnTxu", "Edurne", "Gorostiza", "1991.04.22", "female", "Donostia",
                "Donostia", "UPV/EHU", "Inditex", "Titanic;Avengers", "G6125154");

        for (Person p : people) {
            System.out.print(p.idperson + " friends -> ");
            List<String> fs = p.getFriendsID();
            for (int i = 0; i < fs.size(); i++) {
                System.out.print(fs + (i + 1 < fs.size() ? ", " : ""));
            }
            System.out.println();
        }

    }
}



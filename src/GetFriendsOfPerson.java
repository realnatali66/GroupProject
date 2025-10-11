import java.util.List;
import java.util.Objects;
// 6. point
public class GetFriendsOfPerson {
    public void getFriendsOfPerson(String lastName, List<Person> people) { // Use List instead of ArrayList
        System.out.println("--- Friends of people with last name: " + lastName + " ---");

        for (Person person : people) {
            if (Objects.equals(person.lastName, lastName)) {

                List<Person> friends = person.getFriends();

                System.out.println("Person: " + person.lastName + ", ID: " + person.idperson);

                if (friends.isEmpty()) {
                    System.out.println("  -> Has no friends in the network.");
                    continue;
                }

                System.out.print("  Friends (" + friends.size() + "): ");

                // print only the required ID and Surname for each friend
                for (int i = 0; i < friends.size(); i++) {
                    Person f = friends.get(i);
                    System.out.print(f.lastName + " (ID: " + f.idperson + ")");
                    if (i < friends.size() - 1) {
                        System.out.print("; ");
                    }
                }
                System.out.println(); // Newline after listing all friends
            }
        }
    }
}

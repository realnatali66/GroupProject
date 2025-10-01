import java.util.ArrayList;
import java.util.Objects;
// 6. point
public class GetFriendsOfPerson {
    public void getFriendsOfPerson(String lastName, ArrayList<Person> people) {
        for (Person person : people) {
            if (Objects.equals(person.lastName, lastName)) {
                System.out.println(person.lastName + ", ID: " + person.idperson + "has the following friends: " + person.getFriends());
            }
        }
    }
}

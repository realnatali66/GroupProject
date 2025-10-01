import java.util.ArrayList;

public class GetFriendsOFPerson {
    // 6. point
    public void getFriendsOfPerson(String lastName, ArrayList<Person> people) {
        for (Person person : people) {
            if (person.lastName == lastName) {
                System.out.println(person.name + " " + person.lastName + "has the following friends: " + person.getFriends());
            }
        }
    }
}

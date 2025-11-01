import java.util.ArrayList;
import java.util.List;

public class Person {
    public String idperson;
    public String name;
    public String lastName;
    public String  birthdate;
    public String gender;
    public String birthplace;
    public String home;
    public String studiedAt;
    public String workplaces;
    public String films;
    public String groupcode;

    // Friend list
    public List<Person> friends;

    public Person(String idperson, String name, String lastName, String birthdate, String gender,
                  String birthplace, String home, String studiedAt, String workplaces,
                  String films, String groupcode, ArrayList<Person> friends) {
        this.idperson = idperson;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.birthplace = birthplace;
        this.home = home;
        this.studiedAt = studiedAt;
        this.workplaces = workplaces;
        this.films = films;
        this.groupcode = groupcode;
        this.friends = friends;
    }
    //custom methods


    // Adds friend if not already present
    public void addFriend(Person other) {
        if (other == null || other == this) return;
        if (!friends.contains(other)) {
            friends.add(other);
        }
    }

    //Getters and Setters
    public List<String> getFriendsID() {
        List<String > friendsID = new ArrayList<>();
        for (Person friend : friends) {
            friendsID.add(friend.idperson);
        }
        return friendsID;}

    public String getBirthplace() {return birthplace;}

    public String getName() {
        return name;
    }

    public String getLastName() {return lastName;}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return idperson.equals(person.idperson);
    }

    @Override
    public int hashCode() {
        return idperson.hashCode();
    }

    @Override
    public String toString() {
        return idperson + "," + name + "," + lastName + "," + birthdate + "," + gender + "," + birthplace + "," +
        home + "," + studiedAt + "," + workplaces + "," + films + "," + groupcode + "\n";
    }
}

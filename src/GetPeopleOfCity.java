import java.util.List;

public class GetPeopleOfCity {
    // 7. point
    public static void GetPeopleOfCity(String birthplace, List<Person> people) {
        System.out.println("These are the people of " + birthplace + ":");
        for (Person person : people) {
            if (person.birthplace.equals(birthplace)) {
                System.out.println(person.lastName + ", Id: " + person.idperson);
            }
        }
    }
}
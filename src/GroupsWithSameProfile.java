import java.util.*;

public class GroupsWithSameProfile {
    // 10. point
    public static Map<String, List<Person>> GroupsWithSameProfile(List<Person> people) {
       Map<String, List<Person>> groups = new HashMap<>();

       for (Person person : people) {
           String[] films = person.films.split(",");
           Arrays.sort(films);
           person.films = String.join(";", films);
           groups.computeIfAbsent(person.films, k -> new ArrayList<>()).add(person);
       }
        return groups;
    }

    public static void printGroups(List<Person> people) {
        Map<String, List<Person>> groups = GroupsWithSameProfile(people);
        for (Map.Entry<String, List<Person>> entry : groups.entrySet()) {
        System.out.println("These are the people with the favourite movies: " + entry.getKey());
        for (Person person : entry.getValue()) {
            System.out.println("\t" + person.name + " " + person.lastName);
        }
        }
    }
}

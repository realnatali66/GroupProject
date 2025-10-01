import java.util.ArrayList;
import java.util.Comparator;

// 8. point
public class PeopleBornBetween2Dates {

    public static ArrayList<Person> PeopleBornBetween2Dates(String startDate, String endDate , ArrayList<Person> people) {

        //fortmat the dates
        String[] starts = startDate.split("\\.");
        int StartYear = Integer.parseInt(starts[0]);
        String[] ends = endDate.split("\\.");
        int EndYear = Integer.parseInt(ends[0]);


        ArrayList<Person> peopleBornBetweenDates = new ArrayList<>();
        //check every person in people
        for (Person person : people) {
            String birthDate = person.birthdate;
            String[] births = birthDate.split("\\.");
            int BirthYear = Integer.parseInt(births[0]);

            if (BirthYear >= StartYear && BirthYear <= EndYear) {
                peopleBornBetweenDates.add(person);
            }

        }
        //sort by birthplace, surname, name
        peopleBornBetweenDates.sort(
                Comparator.comparing(Person::getBirthplace)
                        .thenComparing(Person::getLastName)
                        .thenComparing(Person::getName)
        );
        return peopleBornBetweenDates;
    }
}

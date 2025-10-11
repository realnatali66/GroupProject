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

            // FIX , added this because it was failing when some people had birtplace empty
            if (birthDate == null || birthDate.trim().isEmpty()) {
                continue; // Skip people with no birthdate
            }

            String[] births = birthDate.split("\\.");
            if (births.length < 1 || births[0].trim().isEmpty()) {
                continue; // Skip if date is malformed or just dots
            }

            try {
                int BirthYear = Integer.parseInt(births[0].trim());

                if (BirthYear >= StartYear && BirthYear <= EndYear) {
                    peopleBornBetweenDates.add(person);
                }
            } catch (NumberFormatException e) {
                // Handle cases where the year component is non-numeric
                System.err.println("Warning: Could not parse year for person ID: " + person.idperson);
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

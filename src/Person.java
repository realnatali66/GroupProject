import java.time.LocalDate;

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

    public Person(String idperson, String name, String lastName, String birthdate, String gender, String birthplace,
                  String home, String studiedAt, String workplaces, String films, String groupcode) {
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
    }

    @Override
    public String toString() {
        return idperson + "," + name + "," + lastName + "," + birthdate + "," + gender + "," + birthplace + "," +
        home + "," + studiedAt + "," + workplaces + "," + films + "," + groupcode + "\n";
    }
}

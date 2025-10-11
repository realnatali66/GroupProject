import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuApp {

    private final SocialNetwork sn;
    private final Scanner in;

    public MenuApp() {
        this.sn = new SocialNetwork();
        this.in = new Scanner(System.in);
    }

    public static void main(String[] args) throws InterruptedException {
        new MenuApp().run();
    }

    private void run() throws InterruptedException {
        System.out.println("Loading social network...");
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            printMainMenu();
            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> loadPeople();
                    case 2 -> loadRelationships();
                    case 3 -> printPeople();
                    case 4 -> searchMenu();
                    case 5 -> {
                        System.out.println("Logging out…");
                        return;
                    }
                    default -> System.out.println("Unknown option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println(); // spacing
        }
    }

    private void printMainMenu() {
        System.out.println("==================================");
        System.out.println("            MY MENU");
        System.out.println("==================================");
        System.out.println("1. Load 'people' into the network");
        System.out.println("2. Load 'relationships'");
        System.out.println("3. Print out people (ID and Name)");
        System.out.println("4. Search ");
        System.out.println("5. Log out");
        System.out.println("==================================");
    }

    // Option 1: load people
    private void loadPeople() throws IOException {
        String path = readLine("Path to people file: ");
        assertReadable(path);
        sn.addPeopleFromFile(path); // using implemented method from SocialNetwork.java
        System.out.println("People loaded from " + path + ".");
    }

    // Option 2: load relationships
    private void loadRelationships() throws IOException {
        String path = readLine("Path to relationships file: ");
        assertReadable(path);
        sn.loadFriendshipsFile(path);
        System.out.println("Relationships loaded from " + path + ".");
    }

    // Option 3: print people (id + name)
    // this is a quick printer, the NamePrinter class is used in Search / Reports (for printing out to a file)
    private void printPeople() {
        var people = sn.getPeople();
        if (people == null || people.isEmpty()) {
            System.out.println("No people loaded yet.");
            return;
        }
        System.out.println("People (" + people.size() + "):");
        for (Person p : people) {
            System.out.println("- " + p.idperson + " | " + p.name + " " + p.lastName);
        }
    }

    // Option 4: Search submenu
    private void searchMenu() {
        var people = sn.getPeople();
        if (people.isEmpty()) {
            System.out.println("Please load people first (Option 1).");
            return;
        }

        while (true) {
            System.out.println("----- SEARCH / REPORTS -----");
            System.out.println("1. Friends of a person (by last name)");
            System.out.println("2. People born in a city");
            System.out.println("3. People born between two dates (yyyy.MM.dd)");
            System.out.println("4. Birthplace matches hometown");
            System.out.println("5. Print names to a file (NamePrinter)");
            System.out.println("6. Back");
            int c = readInt();

            try {
                switch (c) {
                    case 1 -> friendsOfPerson();
                    case 2 -> peopleOfCity();
                    case 3 -> bornBetween();
                    case 4 -> birthplaceMatchesHometown();
                    case 5 -> namePrinter();
                    case 6 -> { return; }
                    default -> System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private void friendsOfPerson() {
        String lastName = readLine("Enter person's last name: ");
        new GetFriendsOfPerson().getFriendsOfPerson(lastName, sn.getPeople());
    }

    private void peopleOfCity() {
        String city = readLine("Enter birthplace city: ");
        GetPeopleOfCity.GetPeopleOfCity(city, sn.getPeople());
    }

    private void bornBetween() {
        String d1 = readLine("From (yyyy.MM.dd): ");
        String d2 = readLine("To   (yyyy.MM.dd): ");

        var matches = PeopleBornBetween2Dates.PeopleBornBetween2Dates(d1, d2, (java.util.ArrayList<Person>) sn.getPeople());

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }
        System.out.println("Born between " + d1 + " and " + d2 + " (Sorted by birthplace, last name, name):");
        for (Person p : matches) {
            System.out.println("- " + p.idperson + " | " + p.name + " " + p.lastName + " | Born: " + p.birthdate + " | City: " + p.birthplace);
        }
    }

    private void birthplaceMatchesHometown() throws IOException {
        String file = readLine("Enter path to file containing person IDs: ");
        assertReadable(file);
        GetBirthplaceMatchesHometown.GetBirthplaceMatchesHometown(sn.getPeople(), file);
    }

    private void namePrinter() {
        String peopleFile = readLine("Enter path to the original people file: ");
        String outFile = readLine("Enter path for the output file: ");
        NamePrinter.printNames(peopleFile, outFile);
    }

    // --- helpers ---
    private String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private int readInt() {
        while (true) {
            System.out.print("Choose: ");
            String s = in.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private void assertReadable(String path) throws IOException {
        Path path1 = Path.of(path);
        if (!Files.isRegularFile(path1) || !Files.isReadable(path1)) {
            throw new IOException("File not readable or does not exist: " + path);
        }
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

    public class NamePrinter {

        public static void printNames(String inputFile, String outputFile) {
            try {
                File inFile = new File(inputFile);
                Scanner scanner = new Scanner(inFile);

                List<String> names = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] splitLine = line.split(",");
                    if (splitLine.length > 2) {
                        names.add(splitLine[1] + " " + splitLine[2] + ", id: " + splitLine[0]);
                    }
                }
                scanner.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                for (String name : names) {
                    writer.write(name + "\n");
                }
                writer.write("\nTotal number of people: " + names.size() + "\n");
                writer.close();

                System.out.println("Name list written to: " + outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GradeBookDriver {

	public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        displayMenu();
        String choice = kb.nextLine();

        switch (choice.substring(0,1).toLowerCase()){
            case "a":
                //call method for a stuff
                break;
            case "s":
                //call method for b stuff
                break;
            case "g":
                //call method for g stuff
                break;
            case "e":
                //exit
                break;
        }
	}
	public static void displayMenu(){
        System.out.println("Welcome to Grade Book Manager");
        System.out.println("Please enter the following");
        System.out.println("A) Add a new file to repository");
        System.out.println("S) Save data for a user");
        System.out.println("G) Get grades breakdown");
        System.out.println("E) exit");
    }


	public static void readFile(){
        String csvFile = "226-fall-1996.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            //read file line by line until the end
            while ((line = br.readLine()) != null) {

                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeBookDriver {

	public static ArrayList<ArrayList<String>> mainArray = new ArrayList<>();
	public static ArrayList<String> layer1 = new ArrayList<>();
	public static ArrayList<String> layer2 = new ArrayList<>();
	public static ArrayList<String> layer3 = new ArrayList<>();
	
	public static void main(String[] args) {
		setUp("226-fall-1996.csv");
		setUp("326-fall-1996.csv");
		setUp("326-fall-1997.csv");
		mainArray.add(layer1);
		mainArray.add(layer2);
		mainArray.add(layer3);
        Scanner kb = new Scanner(System.in);
        boolean flag = false;
        do {
	        displayMenu();
	        String choice = kb.nextLine();
	        switch (choice.substring(0,1).toLowerCase()){
	            case "a":
	                addData();
	                flag = false;
	                break;
	            case "s":
	                saveData();
	                flag = false;
	                break;
	            case "g":
	                studentsPerGrade();
	                flag = false;
	                break;
	            case "e":
	                System.exit(0);
	                flag = false;
	                break;
	            default:
	            	System.out.println("Invalid option.\n");
	            	flag = true;
	        }
        } while(flag);
        flag = false;
        kb.close();
    }
    public static void addData(){

    }
    public static void saveData(){

    }
    public static void studentsPerGrade(){

    }
    public void exit(){
        System.exit(0);
    }
	public static void displayMenu(){
        System.out.println("Welcome to Grade Book Manager");
        System.out.println("Please enter the following");
        System.out.println("A) Add a new file to repository");
        System.out.println("S) Save data for a user");
        System.out.println("G) Get grades breakdown");
        System.out.println("E) exit");
    }


	public static void setUp(String s){
        String csvFile = s;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile)); 
            while ((line = br.readLine()) != null) {

                String[] categories = line.split(cvsSplitBy);
                for(int i =  0; i < categories.length; i++){
                	if(s.equals("226-fall-1996.csv")) {
                		layer1.add(categories[i]);
                	}
                	if(s.equals("326-fall-1996.csv")) {
                		layer2.add(categories[i]);
                	}
                	if(s.equals("326-fall-1997.csv")) {
                		layer3.add(categories[i]);
                	}
              
               }
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

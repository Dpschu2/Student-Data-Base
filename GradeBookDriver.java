import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeBookDriver {

	public static ArrayList<ArrayList<String>> mainArray = new ArrayList<>();
	public static ArrayList<String> layer1 = new ArrayList<>();
	public static ArrayList<String> layer2 = new ArrayList<>();
	public static ArrayList<String> layer3 = new ArrayList<>();

	public static void main(String[] args) {
		//addLineToFile("hullo", "file.txt"); to test the method i created
		setUp("226-fall-1996.csv");
		setUp("326-fall-1996.csv");
		setUp("326-fall-1997.csv");
        System.out.println("Welcome to Grade Book Manager");
        Scanner kb = new Scanner(System.in);
        boolean flag = true;
        do {
	        displayMenu();
	        String choice = kb.nextLine();
	        switch (choice.substring(0,1).toLowerCase()){
	            case "a":
	                addData();
	                break;
	            case "s":
	                saveData();
	                break;
	            case "g":
	                studentsPerGrade();
	                break;
	            case "e":
	                System.exit(0);
	                flag = false;
	                break;
	            default:
	            	System.out.println("Invalid option.\n");
	        }
        } while(flag);
    }



    public static void addData(){
    	boolean more = true;
    	int iterate = 0;
    	Scanner kb = new Scanner(System.in);
    	do {
    		if(iterate != 0)
    			kb.nextLine();
    		iterate++;
	    	System.out.print("Enter a file name to add to repository: ");
	    	String s = kb.nextLine();
	    	if(s.equals("226-fall-1996.csv")) {
	    		mainArray.add(layer1);
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
	    	else if(s.equals("326-fall-1996.csv")) {
	    		mainArray.add(layer2);
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
	    	else if(s.equals("326-fall-1997.csv")) {
	    		mainArray.add(layer3);
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
	    	else {
	    		System.out.println("Not an option!");
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
    	} while(more);
    }

    public static void saveData(){
    	boolean more = false;
    	System.out.print("Enter student ID: ");
    	Scanner kb = new Scanner(System.in);
    	String studentID = kb.nextLine();
    	for (ArrayList<String> categories : mainArray) {
    		for (String element : categories) {
    			String[] s = element.split("\n");
    			for (String str : s) {
    				String[] line = str.split(",");
	    			for (String a : line) {
	    				if (a.equals(studentID)) {
	    					try {
	    						addLineToFile(categories.get(0), "student.csv");	
	    						addLineToFile(str, "student.csv");
	    						addLineToFile("\n", "student.csv");
	    					}
	    					catch(Exception e) {
	    						System.out.println("Error in CsvFileWriter !!!");
	    						e.printStackTrace();
	    					}
	    					more = true;
	    					System.out.println("\nCSV file was created successfully !!!");
	    				}
	    			}
    			}
    		}
    	}
    	if (!more) {
    		System.out.println("Not an option!");
    	}
    }
		/**
		* Adds a line to a file that has already been created.
		* If the file has not yet been created. Then a file will be created
		* And the first line will be the line parameter
		*
		*@param line -String that you want to add
		*@param pathToFile - that you want to append the line too
		*@author Michael Metz
		*/
    private static void addLineToFile(String line, String pathToFile){
			PrintWriter writer = null;
			try{
				writer = new PrintWriter( new FileOutputStream(pathToFile, true));
			}catch(FileNotFoundException e){
				System.out.println("file not found " + pathToFile);
			}
			writer.print(line);
			writer.close();
	}

    public static void studentsPerGrade(){

    }


	public static void displayMenu(){

        System.out.println("\nPlease enter the following");
        System.out.println("A) Add a new file to repository");
        System.out.println("S) Save data for a user");
        System.out.println("G) Get grades breakdown");
        System.out.println("E) exit");
    }


	public static void setUp(String s){
        String csvFile = s;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\n";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] categories = line.split(cvsSplitBy);
                for(int i =  0; i < categories.length; i++){
                	if(s.equals("226-fall-1996.csv")) {
                		layer1.add(categories[i] + "\n");
                	}
                	if(s.equals("326-fall-1996.csv")) {
                		layer2.add(categories[i] + "\n");
                	}
                	if(s.equals("326-fall-1997.csv")) {
                		layer3.add(categories[i] + "\n");
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

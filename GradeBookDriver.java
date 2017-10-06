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
	public static ArrayList<ArrayList<String>> tempArray = new ArrayList<>();
	public static ArrayList<String> layer1 = new ArrayList<>();
	public static ArrayList<String> layer2 = new ArrayList<>();
	public static ArrayList<String> layer3 = new ArrayList<>();
	public static String usedFiles = "";
	public static String file1 = "226-fall-1996.csv";
	public static String file2 = "326-fall-1996.csv";
	public static String file3 = "326-fall-1997.csv";

	public static void main(String[] args) {
		//addLineToFile("hullo", "file.txt"); to test the method i created

		setUp(file1);
		setUp(file2);
		setUp(file3);
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
	            	int[] grades = studentsPerGrade();
	                if(grades != null) {
	                	System.out.println("\n\nA: " + grades[0] + "  B: " + grades[1] + "  C: " + grades[2] + "  D: " + grades[3] + "  F: " + grades[4]);
	                }
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

    public static int[] studentsPerGrade(){
    	int[] grades = null;
    	boolean more = true;
    	int i1 = 0, i2 = 0;
    	int[] crs = new int[3], sem = new int[3];
    	int aGrade = 0, bGrade = 0, cGrade = 0, dGrade = 0, fGrade = 0;
    	Scanner kb = new Scanner(System.in);
    	System.out.print("Enter course number or \"none\" to skip: ");
    	String courseNumber = kb.nextLine();
    	System.out.print("Enter semester and year(ex:fall 1997) or \"none\" to skip: ");
    	String semYear = kb.nextLine();
    	if(semYear.equals("none") && courseNumber.equals("none"))
    		more = false;
    	if(!semYear.contains("1997") && !semYear.contains("1996") && !semYear.contains("fall"))
    		more = false;
    	if(!courseNumber.contains("226") && !courseNumber.contains("326"))
    		more = false;
    	if(more) {
        	if(courseNumber.equals("226")) {
        		crs[i1] = 1;
        		i1++;
        	}
        	if(courseNumber.equals("326")) {
        		crs[i1] = 2;
        		i1++;
        		crs[i1] = 3;
        		i1++;
        	}
        	if(semYear.contains("1997")) {
        		sem[i2] = 3;
        		i2++;
        	}
        	if(semYear.contains("1996")) {
        		sem[i2] = 1;
        		i2++;
        		sem[i2] = 2;
        		i2++;
        	}
        	int[] sorted = sort(crs, sem);
        	for(int i = 0; i < sorted.length; i++) {
        		if(sorted[i] == 1)
        			tempArray.add(layer1);
        		if(sorted[i] == 2)
        			tempArray.add(layer2);
        		if(sorted[i] == 3)
        			tempArray.add(layer3);
        	}
        	
        	for (ArrayList<String> userPicked : tempArray) {
        		for (String element : userPicked) {
        			String[] s = element.split("\n");
        			for (String str : s) {
        				String[] line = str.split(",");
    	    			if (line[line.length - 1].equals("A")) {
	    					aGrade++;
	    				}
    	    			if (line[line.length - 1].equals("B")) {
	    					bGrade++;
	    				}
    	    			if (line[line.length - 1].equals("C")) {
	    					cGrade++;
	    				}
    	    			if (line[line.length - 1].equals("D")) {
	    					dGrade++;
	    				}
    	    			if (line[line.length - 1].equals("F")) {
	    					fGrade++;
	    				}
        			}
        		}
        	}
    		
    	}
    	grades = new int[]{aGrade, bGrade, cGrade, dGrade, fGrade};
    	return grades;
    }

    public static int[] sort(int[] crs, int[] sem) {
    	int[] combined = new int[3];
    	int i = 0;
    	for(int j = 0; j < crs.length; j++) {
    		for(int k = 0; k < sem.length; k++) {
    			if(crs[j] == 1 && sem[k] == 1) {
    	    		combined[i] = 1;
    	    		i++;
    	    	}
    			if(crs[j] == 2 && sem[k] == 2) {
    	    		combined[i] = 2;
    	    		i++;
    	    	}
    			if(crs[j] == 3 && sem[k] == 3) {
    	    		combined[i] = 3;
    	    		i++;
    	    	}
    		}
    	}
    	return combined;
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
                		usedFiles += "226-fall-1996\n";
                	}
                	if(s.equals("326-fall-1996.csv")) {
                		layer2.add(categories[i] + "\n");
                		usedFiles += "326-fall-1996\n";
                	}
                	if(s.equals("326-fall-1997.csv")) {
                		layer3.add(categories[i] + "\n");
                		usedFiles += "326-fall-1997\n";
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeBookDriver {
    //new vars
    public static GradeBookRepository gradeBookRepo = new GradeBookRepository();

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Grade Book Manager");
        boolean flag = true;
        do
        {
            displayMenu();
            String choice = keyboard.nextLine();
            switch (choice.substring(0, 1).toLowerCase()) {
                case "a":
                    addData();
                    break;
                case "s":
                    saveData();
                    break;
                case "g":
                    int[] grades = studentsPerGrade();
                    if(!(grades == null)) {
                        System.out.printf("\n\nA: %d  B: %d C: %d D: %d F: %d",
                                grades[0],
                                grades[1],
                                grades[2],
                                grades[3],
                                grades[4]);
                    }
                    break;
                case "e":
                    System.exit(0);
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option.\n");
            }
        } while (flag);
        keyboard.close();
    }

    /**
     * Add data (‘a’ or ‘A’):
     *   i. Take file name from the user (course-semester-year.csv).
     *      along with the course number, semester and year data.
     * iii. Print the number of students whose data it just read, and how many students
     *      already existed in the repository.
     */
    public static void addData() {
        String course = null;
        String semster = null;
        String year = null;


        //i. Take file name from the user (course-semester-year.csv).
        //get filename that user wants to add.
        System.out.print("Enter a file name to add to repository: ");
        String filename = keyboard.nextLine();

        String[] filenameArray = filename.split("-");
        if(filenameArray.length != 3)
        {
            System.out.println("invalid File Name Schema\n Must be named as follows\n");
            System.out.println("course-semester-year.csv");
            return;
        }

        course = filenameArray[0];
        semster = filenameArray[1];
        year = filenameArray[2];
        String[] y = year.split("\\.");
        if(y.length != 2)
        {
            System.out.println("invalid File Name Schema\n Must be named as follows\n");
            System.out.println("course-semester-year.csv");
            return;
        }
        year = y[0];
        //ii. Read the provided file (if it exists), extract the data and add it to the repository
        Scanner fileReader = null;
        try
        {
            fileReader = new Scanner(new File(filename));
        }catch (FileNotFoundException e)
        {
            System.out.println(filename + " not found");
            return;
        }

        // READING FILE NOTES FROM ASSIGNMENT INSTRUCTIONS
        //
        // Columns are separated by commas
        //
        // Some column data may legitimately contain commas.
        // In this case, the contents of that column will be enclosed in double quotes.
        //
        // Important column names
        //
        // [Interchangeable column names] : [description]
        //
        //           User ID <=> Student Id : unique ULID for each student
        // Grade <=> grade <=> Letter grade : contents are letter grade A,B,C,D,F
        //
        // All other columns are contain individual grades for each assignment.

        //At this point the file is located, now we must read it and check whether it valid.
        ArrayList<String> readLines = new ArrayList<>(50);
        while(fileReader.hasNextLine())
        {
            String line  = fileReader.nextLine();
            readLines.add(line);
        }
        fileReader.close();
        String[][] file = null;
        //now that we have every line in a file. we can create a primitive 2d array to represent the table
        //[row][column]
        if(1 <= readLines.size())
        {
            String[] columnNames = readLines.get(0).split(",");
            int numColumns = columnNames.length;
            int numRows = readLines.size();
            file = new String[numRows][numColumns];

            //process each line and store in the 2d array.
            //The end of this should be a representation of the data, as if it were opened in excel.
            for(int i = 0; i < readLines.size(); i++)
            {
                String line = readLines.get(i);
                String[] rowContents = Utility.splitLineByDeliminatorButIgnoreIfInBetweenQuotes(line, ',');

                if(rowContents.length != columnNames.length)
                    System.out.println("row length does not match column length");
                else
                    //dump contents of row into each column.
                    for(int j = 0; j < rowContents.length; j++)
                    {
                        file[i][j] = rowContents[j];

                    }
            }
        }

        //iii. Print the number of students whose data it just read, and how many students already existed in the repository.
        int numLinesRead = readLines.size();
        if(numLinesRead == 0 || numLinesRead == 1){
            System.out.println("file did not contain any students");
        }
        else {
            System.out.println("File contained " + (numLinesRead-1) + " Students");

        }


        //Adding the new file to the grade book repository.

        GradeBook newBook = new GradeBook();
        newBook.setBook(file);
        newBook.setCourseNumber(course);
        newBook.setSemester(semster);
        newBook.setYear(year);
        int currentStudents = gradeBookRepo.numStudents();
        gradeBookRepo.addGradeBook(newBook);
        int newStudents = newBook.getClassSize();
        int newTotal = newStudents + currentStudents;
        System.out.println("----------------");
        System.out.printf("Repository contained %d students%n%s contained %d students%nRepository now contains %d%n", currentStudents, filename, newStudents, newTotal);
        System.out.println("----------------");

    }

    public static void saveData() {
        System.out.println("Enter student ID:");
        String id = keyboard.nextLine();
        System.out.println("Enter Name of File you want to export Student data to");
        String fileName = keyboard.nextLine();
        String allStudentData = "Student Id,Course,Semester,Year,Assignment Name,Points\n";
        allStudentData += gradeBookRepo.getStudentInfo(id);

        addLineToFile(allStudentData, fileName);

        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(fileName);
            writer.print(allStudentData);
            writer.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("file not found " + fileName);
            return;
        }


    }

    /**
     * Adds a line to a file that has already been created.
     * If the file has not yet been created. Then a file will be created
     * And the first line will be the line parameter
     *
     * @param line       -String that you want to add
     * @param pathToFile - that you want to append the line too
     * @author Michael Metz
     */
    private static void addLineToFile(String line, String pathToFile) {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new FileOutputStream(pathToFile, true));
        } catch (FileNotFoundException e)
        {
            System.out.println("file not found " + pathToFile);
            return;
        }
        writer.print(line);
        writer.close();
    }

    public static int[] studentsPerGrade() {

        while (true) {
            System.out.print("Enter course number or \"none\" to skip: ");
            String courseNumber = keyboard.nextLine();

            System.out.print("Enter semester and year(ex:fall 1997)  or \"none\" to skip: ");
            String semYear = keyboard.nextLine();


            boolean flag = true;

            boolean semYearIsNone = semYear.trim().equalsIgnoreCase("none");
            boolean courseNumberIsNone = courseNumber.trim().equalsIgnoreCase("none");

            if (semYearIsNone && courseNumberIsNone)
                return null;

            if (semYearIsNone && !courseNumberIsNone)
                return gradeBookRepo.getGrades(courseNumber);

            if (!semYearIsNone && courseNumberIsNone) {
                String[] a = semYear.trim().split(" ");
                if (a.length != 2) {
                    System.out.println("You did not enter semester and year in the proper format. Try Again");
                    continue;
                }
                return gradeBookRepo.getGrades(a[0], a[1]);
            }
            if(!courseNumberIsNone && !semYearIsNone){
                String[] a = semYear.trim().split(" ");
                if (a.length != 2) {
                    System.out.println("You did not enter semester and year in the proper format. Try Again");
                    continue;
                }
                return gradeBookRepo.getGrades(courseNumber,a[0],a[1]);
            }
        }
    }


    public static void displayMenu() {

        System.out.println("\nPlease enter the following");
        System.out.println("A) Add a new file to repository");
        System.out.println("S) Save data for a user");
        System.out.println("G) Get grades breakdown");
        System.out.println("E) exit");
    }

}


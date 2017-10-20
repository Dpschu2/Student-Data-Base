public class GradeBook{
    private static String[] GRADE_COLUMN_NAMES = {"Grade", "grade", "Letter grade"};
    private static String[] ID_COLUMN_NAMES = {"Student Id", "User ID"};

    private String courseNumber;
    private String semester;
    private String year;

    private String[][] book;

    /**
     *
     * @param courseNumber - ex. 226, 326
     * @param semester - ex. Fall, Spring
     * @param year - ex. 1996, 1997, 2001
     * @param book - a 2d array representing a csv from reggie net. first line is column headers
     */
    public GradeBook(String courseNumber, String semester, String year, String[][] book) {
        this.courseNumber = courseNumber;
        this.semester = semester;
        this.year = year;
        this.book = book;
    }
    public GradeBook(){
    }
    //various methods
    public void printBookDetails(){
        System.out.printf("%d are in : %s, %s, #%s\n", getClassSize(), year, semester, courseNumber);
    }

    public String getStudentData(String studentId){
        if(book.length <= 1)
            return "";

        //find Student Id Column
        int columnNum = -1;
        int finalExamCol = -1;

        for(int i = 0; i < book[0].length; i++){
            for(String col : ID_COLUMN_NAMES)
                if(book[0][i].equals(col))
                    columnNum = i;
            if(book[0][i].equalsIgnoreCase("Final Exam"))
                finalExamCol = i;
        }
        if(columnNum == -1)
            return "";

        //find student row
        int studentRow = -1;
        for(int i = 1; i < book.length; i++){
            if(book[i][columnNum].equals(studentId))
                studentRow = i;
        }

        if(studentRow == -1)
            return "";

        String finalExam = "Final Exam";
        String points = "";

        if(finalExamCol == -1)
            finalExam = "";
        else
            points = book[studentRow][finalExamCol];


        return String.format("%s,%s,%s,%s,%s,%s",studentId,courseNumber,semester,year,finalExam,points);
    }
    //creative get methods
    public int getClassSize(){
        int rowLength = book.length;
        rowLength -= 1; //because first line is column headings
        return rowLength;
    }

    /**
     * Scans grade book for the grade column( indicated by GRADE_COLUMN_NAMES)
     * Then returns an array with the totals for each letter grade.
     *
     * a[0] = total number of A's in class
     * a[1] = total number of B's in class
     * a[2] = total number of C's in class
     * a[3] = total number of D's in class
     * a[4] = total number of F's in class
     * @return
     */
    public int[] getGradeBreakDownForClass(){
        int[] grades = new int[5];
        int gradeColumn = -1;

        //find column column
        for(int i = 0; i < book[0].length; i++)
            for(String validColumnName : GRADE_COLUMN_NAMES)
                if(book[0][i].equals(validColumnName))
                    gradeColumn = i;


        if(gradeColumn == -1){
            //grade column cannot be found
            return new int[] {0,0,0,0,0};
        }
        if(book.length == 1){
            //book only contain column names, does not contain any rows.
            return new int[] {0,0,0,0,0};
        }

        //grade column is at i
        for(int i = 1; i < book.length; i++)
        {
            String letterGrade = book[i][gradeColumn];
            switch (letterGrade){
                case "A":
                    grades[0] = grades[0] + 1;
                    break;
                case "B":
                    grades[1] = grades[1] + 1;
                    break;
                case "C":
                    grades[2] = grades[2] + 1;
                    break;
                case "D":
                    grades[3] = grades[3] + 1;
                    break;
                case "F":
                    grades[4] = grades[4] + 1;
                    break;
            }
        }

        return grades;
    }

    //instance Variable getters and setters
    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String[][] getBook() {
        return book;
    }

    public void setBook(String[][] book) {
        this.book = book;
    }
}
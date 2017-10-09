public class GradeBook{
    private static String[] GRADE_COLUMN_NAMES = {"Grade", "grade", "Letter grade"};

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


    //creative get methods
    public int getClassSize(){
        int rowLength = book.length;
        rowLength -= 1; //because first line is column headings
        return rowLength;
    }

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
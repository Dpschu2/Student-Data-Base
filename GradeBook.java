public class GradeBook{
    private String courseNumber;
    private String semesterSeason;
    private String year;

    private String[][] book;

    /**
     *
     * @param courseNumber - ex. 226, 326
     * @param semesterSeason - ex. Fall, Spring
     * @param year - ex. 1996, 1997, 2001
     * @param book - a 2d array representing a csv from reggie net. first line is column headers
     */
    public GradeBook(String courseNumber, String semesterSeason, String year, String[][] book) {
        this.courseNumber = courseNumber;
        this.semesterSeason = semesterSeason;
        this.year = year;
        this.book = book;
    }
    public GradeBook(){
    }
    //various methods
    public void printBookDetails(){
        System.out.printf("%d are in : %s, %s, #%s\n", getClassSize(), year, semesterSeason, courseNumber);
    }


    //creative get methods
    public int getClassSize(){
        int rowLength = book.length;
        rowLength -= 1; //because first line is column headings
        return rowLength;
    }




    //instance Variable getters and setters
    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getSemesterSeason() {
        return semesterSeason;
    }

    public void setSemesterSeason(String semesterSeason) {
        this.semesterSeason = semesterSeason;
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
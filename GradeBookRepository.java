import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by michael on 10/6/2017.
 */
public class GradeBookRepository {
    LinkedList<GradeBook> gradeBooks;

    public GradeBookRepository(){
        gradeBooks = new LinkedList<>();
    }
    public void addGradeBook(GradeBook gb){
        gradeBooks.add(gb);
    }

    public String getStudentInfo(String studentID){
        String s = "";
        Iterator<GradeBook> iterator = gradeBooks.iterator();

        //find book in gradeBooks list
        while (iterator.hasNext())
        {
            GradeBook i = iterator.next();
            s+= i.getStudentData(studentID) + "\n";
        }
        return s;
    }
    //hasGradeBook methods

    /**
     * Returns the number of gradebooks that match the courseNumber.
     *
     * @param courseNumber
     * @return
     */
    public int hasGradeBooks(String courseNumber){
        return 0;
    }

    /**
     * Returns the number of gradebooks that match the semester and year.
     *
     * @param semester
     * @param year
     * @return
     */
    public int hasGradeBooks(String semester, String year){
        return 0;
    }

    /**
     * Returns the number of gradebooks that match the courseNumber, semester, and year
     * @param courseNumber
     * @param semester
     * @param year
     * @return
     */
    public int hasGradeBooks(String courseNumber, String semester, String year){
        return 0;
    }

    //Get Grade Methods
    /**
     * grade breakdown of a given course across all the semesters and year
     *
     * @param courseNumber
     * @return
     */
    public int[] getGrades(String courseNumber){
        Iterator<GradeBook> iterator = gradeBooks.iterator();
        int[] gradeTotal = {0,0,0,0,0};
        //find book in gradeBooks list
        while (iterator.hasNext())
        {
            GradeBook i = iterator.next();
            String cn = i.getCourseNumber();

            if (cn.equals(courseNumber))
            {
                //grade book matches criteria so add to the gradeTotal.
                gradeTotal = addArraysElementsTogether(gradeTotal,i.getGradeBreakDownForClass());
            }
        }
        return gradeTotal;
    }
    public int numStudents(){
        Iterator<GradeBook> iterator = gradeBooks.iterator();
        int totalStudents = 0;
        //find book in gradeBooks list
        while (iterator.hasNext())
        {
            GradeBook i = iterator.next();
            totalStudents += i.getClassSize();
        }
        return totalStudents;
    }
    /**
     * grade breakdown of all courses across semester and year
     * @param semester
     * @param year
     * @return
     */
    public int[] getGrades(String semester, String year){
        Iterator<GradeBook> iterator = gradeBooks.iterator();
        int[] gradeTotal = {0,0,0,0,0};
        //find book in gradeBooks list
        while (iterator.hasNext())
        {
            GradeBook i = iterator.next();
            String sem = i.getSemester();
            String yr = i.getYear();
            if (sem.equals(semester) && yr.equals(year))
            {
                //grade book matches criteria so add to the gradeTotal.
                gradeTotal = addArraysElementsTogether(gradeTotal,i.getGradeBreakDownForClass());
            }
        }
        return gradeTotal;
    }

    /**
     * Add two arrays together.  If one array has less elements than the other,
     * then the array is still added.
     * ex.
     * a:{3,1,3,4}
     * b:{2,3}
     *
     * returns {5, 4, 3, 4}
     * @param a - first array
     * @param b - second array
     * @return one array { a[i] + b[i], ...., a[n] + b[n]}
     */
    private int[] addArraysElementsTogether(int[] a, int[] b){
        if(a.length == 0 )
            return b;
        if(b.length == 0)
            return a;
        if(a.length < b.length)
        {
            for (int i =0; i < a.length; i++)
            {
                b[i] = b[i] + a[i];
            }
            return b;
        }
        else{
            //b is equal to or less then a
            for (int i =0; i < b.length; i++)
            {
                a[i] = b[i] + a[i];
            }
            return a;
        }
    }
    /**
     * grade breakdown of a given course across a given semester and year
     * First locates the proper book the the gradeBooks list
     * Then returns the getGradeBreakDownForClass On that given book.
     *
     * @param courseNumber
     * @param semester
     * @param year
     * @return
     */
    public int[] getGrades(String courseNumber, String semester, String year){

        Iterator<GradeBook> iterator = gradeBooks.iterator();
        GradeBook book = null;

        //find book in gradeBooks list
        while (iterator.hasNext())
        {
            GradeBook i = iterator.next();
            String cn = i.getCourseNumber();
            String sem = i.getSemester();
            String yr = i.getYear();

            if (cn.equals(courseNumber))
            {
                if (sem.equals(semester) && yr.equals(year))
                {
                        book = i;
                }
            }
        }

        if(book == null){
            //no books can be found in repository
            return new int[] {0,0,0,0,0};
        }
        return book.getGradeBreakDownForClass();
    }


}

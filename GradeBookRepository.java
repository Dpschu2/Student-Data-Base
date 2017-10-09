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
        return new int[3];
    }

    /**
     * grade breakdown of all courses across semester and year
     * @param semester
     * @param year
     * @return
     */
    public int[] getGrades(String semester, String year){
        return new int[3];
    }

    /**
     * grade breakdown of a given course across a given semester and year
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

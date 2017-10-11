import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GradeBookRepositoryTest {

    static GradeBookRepository repo = new GradeBookRepository();
    static GradeBook b1,b2,b3;

    @BeforeAll
    public static void beforeClass(){
        String[][] b1gb = new String[3][3];
        String[][] b2gb = new String[3][3];
        String[][] b3gb = new String[3][3];


        //column rows fro each book
        b1gb[0] = new String[]{"User ID",   "Asg1",   "Letter grade"};
        b2gb[0] = new String[]{"Student Id","project","grade"};
        b3gb[0] = new String[]{"User ID",   "exam",   "Grade"};

        //grade rows for each book
        //grades book 1
        b1gb[1] = new String[]{"met01","8", "A"};
        b1gb[2] = new String[]{"aej03","21","D"};
        //grades book 2
        b2gb[1] = new String[]{"met01","3", "B"};
        b2gb[2] = new String[]{"des02","3", "C"};
        //grades book 3
        b3gb[1] = new String[]{"met01","22","D"};
        b3gb[2] = new String[]{"ame01","4", "F"};

        b1 = new GradeBook("226","fall","2017", b1gb);
        b2 = new GradeBook("261","fall","2017", b2gb);
        b3 = new GradeBook("226","spring","2017", b3gb);

        repo.addGradeBook(b1);
        repo.addGradeBook(b2);
        repo.addGradeBook(b3);

    }

    @Test
    public void hasGradeBooks() throws Exception {
    }

    @Test
    public void hasGradeBooks1() throws Exception {
    }

    @Test
    public void hasGradeBooks2() throws Exception {
    }


    /*Grades should be returned in an array as follows
     * a[0] = total number of A's in class
     * a[1] = total number of B's in class
     * a[2] = total number of C's in class
     * a[3] = total number of D's in class
     * a[4] = total number of F's in class
     */
    @Test
    public void getGradesByCourseNumber() throws Exception {
        int[] result1 = repo.getGrades("226");
        assertArrayEquals(new int[] {1,0,0,2,1}, result1);

        int[] result2 = repo.getGrades("261");
        assertArrayEquals(new int[] {0,1,1,0,0}, result2);

        int[] result3 = repo.getGrades("001");
        assertArrayEquals(new int[] {0,0,0,0,0}, result3);

    }

    @Test
    public void getGrades1SemesterAndYear() throws Exception {
        int[] result1 = repo.getGrades("fall","2017");
        assertArrayEquals(new int[] {1,1,1,1,0}, result1);

        int[] result2 = repo.getGrades("spring","2017");
        assertArrayEquals(new int[] {0,0,0,1,1}, result2);

        int[] result3 = repo.getGrades("winter","2017");
        assertArrayEquals(new int[] {0,0,0,0,0}, result3);
    }

    @Test
    public void getGrades2ByCourseNumberAndSemesterAndYear() throws Exception {
        int[] result1 = repo.getGrades("226","spring","2017");
        assertArrayEquals(new int[] {0,0,0,1,1}, result1);

        int[] result2 = repo.getGrades("001","spring","2017");
        assertArrayEquals(new int[] {0,0,0,0,0}, result2);

        int[] result3 = repo.getGrades("226","winter","2017");
        assertArrayEquals(new int[] {0,0,0,0,0}, result3);

        int[] result4 = repo.getGrades("226","spring","1683");
        assertArrayEquals(new int[] {0,0,0,0,0}, result4);

    }

}
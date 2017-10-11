import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeBookTest {
    static GradeBook b1,b2,b3,b4;

    @BeforeAll
    public static void beforeClass(){
        String[][] b1gb = new String[3][3];
        String[][] b2gb = new String[3][3];
        String[][] b3gb = new String[3][3];
        String[][] b4gb = new String[1][3];


        //column rows fro each book
        b1gb[0] = new String[]{"User ID",   "Asg1",   "Letter grade"};
        b2gb[0] = new String[]{"Student Id","project","grade"};
        b3gb[0] = new String[]{"User ID",   "exam",   "Grade"};
        b4gb[0] = new String[]{"User ID",   "exam",   "Grade"};

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
        b4 = new GradeBook("000","winter","1632", b4gb);


    }

    @Test
    public void getClassSize() throws Exception {
        assertEquals(2,b1.getClassSize());
        assertEquals(2,b2.getClassSize());
        assertEquals(2,b3.getClassSize());
        assertEquals(0,b4.getClassSize());
    }

    @Test
    public void getGradeBreakDownForClass() throws Exception {
        //grades should be returned in an array as follows
        /*
         * a[0] = total number of A's in class
         * a[1] = total number of B's in class
         * a[2] = total number of C's in class
         * a[3] = total number of D's in class
         * a[4] = total number of F's in class
         */
       assertArrayEquals(new int[]{1,0,0,1,0}, b1.getGradeBreakDownForClass());
       assertArrayEquals(new int[]{0,1,1,0,0}, b2.getGradeBreakDownForClass());
       assertArrayEquals(new int[]{0,0,0,1,1}, b3.getGradeBreakDownForClass());
       assertArrayEquals(new int[]{0,0,0,0,0}, b4.getGradeBreakDownForClass());
    }

}
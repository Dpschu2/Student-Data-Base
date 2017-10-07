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



}

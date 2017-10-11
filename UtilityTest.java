import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests the Utility methods. find and replace needs to be looked at, its causing allot of errors.
 */
public class UtilityTest {

    @BeforeAll
    public static void setUp() throws Exception {
    }

    @AfterAll
    public static void tearDown() throws Exception {
    }

    @Test()
    public void findAndReplace() throws Exception {

        //replace single character
        String single = "Ben Zobrist";
        String singleFind = "o";
        String singleReplace = "u";
        String singleResult = Utility.findAndReplace(singleFind, single, singleReplace);
        assertEquals("Ben Zubrist", singleResult);

        //replace multiple word
        String word = "The chicken walks at midnight";
        String wordFind = "walks";
        String wordReplace = "runs";
        String wordResult = Utility.findAndReplace(wordFind, word, wordReplace);
        assertEquals("The chicken runs at midnight", wordResult);

        //this one causes recursive loop

        //find and replace entire string
        String append = "Ben Zobrist, Jose Altuve, Arron Judge";
        String appendFind = new String(append);
        String appendReplace = new String(append) + ", Madison Bumgarner";
        String appendResult = Utility.findAndReplace(appendFind, append, appendReplace);


    }

    @Test()
    public void testFindAndReplaceEdgeCases() throws Exception {
//        //edge cases
//        String edge = "Ben Zobrist, Jose Altuve, Arron Judge";
//        String frontFindChar = edge.substring(0,1);
//        String frontFindWord = edge.substring(0,3);
//        String endFindChar = edge.substring(edge.length() - 1);
//        String endFindWord = edge.substring(edge.length() - 5);
//        String edgeReplaceChar =  "e";
//        String edgeReplaceWord = "Kyle SCHWARBER";
//
//        //replace edge with single character
//        String frontCharReplaceChar = Utility.findAndReplace(frontFindChar, edge, edgeReplaceChar);
//        String frontWordReplaceChar = Utility.findAndReplace(frontFindWord, edge, edgeReplaceChar);
//        String endCharReplaceChar = Utility.findAndReplace(endFindChar, edge, edgeReplaceChar);
//        String endWordReplaceChar = Utility.findAndReplace(endFindWord, edge, edgeReplaceChar);
//
//        //replace edge with word
//        String frontCharReplaceWord = Utility.findAndReplace(frontFindChar, edge, edgeReplaceWord);
//        String frontWordReplaceWord = Utility.findAndReplace(frontFindWord, edge, edgeReplaceWord);
//        String endCharReplaceWord = Utility.findAndReplace(endFindChar, edge, edgeReplaceWord);
//        String endWordReplaceWord = Utility.findAndReplace(endFindWord, edge, edgeReplaceWord);
//
//        assertEquals(edgeReplaceChar + edge.substring(1), frontCharReplaceChar);
    }

    @Test
    public void countOccurrences() throws Exception {

        assertEquals(0, Utility.countOccurrences("", ""));
        assertEquals(0, Utility.countOccurrences("", ","));
        assertEquals(0, Utility.countOccurrences(",", ""));
        assertEquals(1, Utility.countOccurrences(",", ","));
        assertEquals(1, Utility.countOccurrences(",", ",."));
        assertEquals(1, Utility.countOccurrences(",", ".,"));
        assertEquals(2, Utility.countOccurrences(",", ".,,."));
        assertEquals(2, Utility.countOccurrences("1001", "1001j1001"));

    }

    @Test
    public void splitLineByDeliminatorButIgnoreIfInBetweenQuotes() throws Exception {
        String mostCases = "\"ben, zobrist\", 6ft, 3in, 210lb, \"2B,SS,OF\", Cubs";
        String[] mostCasesExpected = {"\"ben, zobrist\"", " 6ft", " 3in", " 210lb", " \"2B,SS,OF\"", " Cubs"};
        String[] result = Utility.splitLineByDeliminatorButIgnoreIfInBetweenQuotes(mostCases, ',');
        assertArrayEquals(mostCasesExpected, result);

        String edgeCase = "\"Ben, Zobrist\"";
        String[] edgeCaseExpected = {"\"Ben, Zobrist\""};
        String[] edgeCaseResult = Utility.splitLineByDeliminatorButIgnoreIfInBetweenQuotes(edgeCase, ',');
        assertEquals(edgeCaseExpected, edgeCaseResult);

        String edgeCase2 = "Starting today,\"Ben, Zobrist\"";
        String[] edgeCaseExpected2 = {"Starting today", "\"Ben, Zobrist\""};
        String[] edgeCaseResult2 = Utility.splitLineByDeliminatorButIgnoreIfInBetweenQuotes(edgeCase2, ',');
        assertEquals(edgeCaseExpected2, edgeCaseResult2);
    }

}
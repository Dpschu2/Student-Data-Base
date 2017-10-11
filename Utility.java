import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * This class contains static methods that can be used throughout the program.
 * These methods are more specifically useful throughout when reading as input
 */
public class Utility {
    /**
     * Recursively find all occurrences of a substring in a string.
     * And then replace them with another string
     * Infinite loop of needle is a substring of the newValue.
     * Infinite loop if you try to replace the entire string with a bigger string.
     * <p>
     * Since java Strings are immutable, a more efficient approach would be
     * To use a primitive chare array, or the String builder class
     *
     * @param needle   - The substring that you want to replace
     * @param haystack - The big string containing needles (if any)
     * @param newValue - The String to replace the needle
     * @return - A String with with all occurrences of needle replaced by newValue.
     * @author Michael Metz
     * <p>
     * <p>
     * But for now this works fine.
     */
    public static String findAndReplace(String needle, String haystack, String newValue) {
        //will cause infinate loop and does not change anything
        if(needle.equals(newValue))
            return haystack;
        //base case
        if (!(haystack.contains(needle)))
            return haystack;

        //reduce to the basecase
        int startOfNeedleIndex = haystack.indexOf(needle);
        String beginning = haystack.substring(0, startOfNeedleIndex);
        int endOfNeedleIndex = startOfNeedleIndex + needle.length();
        String ending = haystack.substring(endOfNeedleIndex);
        String s = beginning + newValue + ending;
        //recursive call
        return findAndReplace(needle, s, newValue);
    }

    /**
     * Utilizes the three parameter countOccurrences method
     *
     * @param needle   - substring to count
     * @param haystack - big string that may contain the given substring
     * @return - number of times needle exists in the haystack
     * @author Michael Metz
     */
    public static int countOccurrences(String needle, String haystack) {
        return countOccurrences(needle, haystack, 0);
    }

    /**
     * Recursively count all occurrences of a certain substring with in a string
     * <p>
     * Works by finding the first index of the needle in the haystack,
     * Then updates haystack to a string that doesn't include this needle it just found
     * Then recursively calls count Occurrences on the updated haystack
     *
     * @param needle   - substring to count
     * @param haystack - big string that may contain the given substring
     * @param num      - number of times you've found the string
     * @return - number of times needle exists in the haystack
     * @author Michael Metz
     */
    private static int countOccurrences(String needle, String haystack, int num) {
        //base case 1
        if(needle.length() == 0 || haystack.length() == 0)
            return num;
        //base case 2
        if (!(haystack.contains(needle)))
            return num;

        //reduce to base case
        int endIndex = haystack.indexOf(needle) + needle.length();
        haystack = haystack.substring(endIndex);
        num++;
        //recursive call
        return countOccurrences(needle, haystack, num);

    }

    /**
     * works just like String.split(",");,
     * but if there is the deliminator is between two quotes then it will not be treated as a deliminator
     *
     * ex.
     * {ben,zobrist, "Metz, Michael", jose, altuve}
     *
     * would return and array with the following elements
     *
     * ["ben", "zobrist", "\"Metz, Michael\"", "jose", "altuve"]
     *
     * Known Issues.
     * if there is a quoted deliminator at the last index in a deliminator string
     * ex.
     * {ben ,zobrist, jose, altuve,"Metz, Michael"}
     * it will still treat that quoted deliminator as a deliminator.
     * ["ben", "zobrist", "jose", "altuve", "\"Metz", "Michael\""]
     *
     * The method uses stack and queue data structure, Which works for the most part, but im sure there is a better
     * way to tackle this problem
     *
     * @author Michael Metz
     * @param line - string that you want to split apart
     * @param deliminator - split apart by
     * @return an array of delimited strings
     */
    public static String[] splitLineByDeliminatorButIgnoreIfInBetweenQuotes(String line, char deliminator){

        char[] lineArray = line.toCharArray();

        Stack<Character> lineStack = new Stack<Character>();
        ArrayList<String> splitList = new ArrayList<>(15);
        LinkedList<Character> splitStack = new LinkedList<>();

        //put chars in a stack with the last characters being first to go in
        for(int i = lineArray.length-1; 0 <= i; i--) {
            Character c = new Character(lineArray[i]);
            lineStack.push(c);
        }

        boolean withInQuotes = false;
        while (!(lineStack.isEmpty()))
        {
            Character nextChar = lineStack.peek().charValue();

            //deliminator encountered
            if(nextChar == deliminator)
            {
                if(withInQuotes)
                {
                    //there is a possibility we are in between two quotes
                    //keep pushing into stack

                }else {
                    //we are not in between quotes
                    //treat this character as deliminator
                    lineStack.pop();

                    //flush the splitStack contents and add to the splitList
                    int size = splitStack.size();
                    char[] word = new char[size];

                    //iterate from end because splitStack.pop() holds the last character pushed in
                    for(int i = size-1; 0 <= i; i--)
                    {
                        Character c = splitStack.pop();
                        word[i] = c.charValue();
                    }
                    splitList.add(new String(word));
                    continue;
                }
            }
            
            //deliminator not encountered
            if(nextChar == '"')
            {
                //if we previously encountered a quote, then this next quote will close the quote.
                //If we curretly not in between quotes, then this next quote will open the quote.
                withInQuotes = !(withInQuotes);
            }
            Character c = lineStack.pop();
            splitStack.push(c);

        }
        //if there are still values in the splitStack that means we only ecountered 1 quote.
        // i.e the quote was never closed.
        //therefore treat every deliminator as a deliminator.
        StringBuilder s = new StringBuilder();
        while( !(splitStack.isEmpty()) )
        {
            char c = splitStack.pollLast().charValue();
            if(c == deliminator)
            {
                String word = s.toString();
                splitList.add(word); 
                //reset s
                s = new StringBuilder();
            }
            else {
                s.append(c);
            }
        }
        if( 0 < s.length())
            splitList.add(s.toString());
        return splitList.toArray(new String[splitList.size()]);

        
    }

}

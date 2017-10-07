
/**
 * This class contains static methods that can be used throughout the program.
 * These methods are more specifically useful throughout when reading as input
 */
public class Utility {
    /**
     * Recursively find all occurrences of a substring in a string.
     * And then replace them with another string
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
        //base case
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
     * ex.
     * ben,zobrist, "Metz, Michael", jose, altuve
     *
     * would return and array with the following elements
     *
     * ["ben", "zobrist", "Metz, Michael", "jose", "altuve"]
     *
     * @param line - string that you want to split apart
     * @param deliminator - split apart by
     * @return an array of delimited strings
     */
    public static String[] splitLineByDeliminatorButIngoreIfInBetweenQuotes(String line, char deliminator){
        return line.split(Character.toString(deliminator));

        //Adam below is my attempt at this method.


        //        ArrayList<String> list = new ArrayList<String>(10);
//
//        int delimIndex = line.indexOf(deliminator);
//
//        while(delimIndex != -1)
//        {
//             delimIndex = line.indexOf(",");
//
//            String beforeDelim = line.substring(0,delimIndex);
//            String afterDelim = line.substring(delimIndex+1);
//            //check if there is a " behind the delim and a " after the delim.
//            int quoteCountBeforeDelim = countOccurrences("\"", beforeDelim);
//            int quoteCountAfterDelim = countOccurrences("\"", afterDelim);
//
//            if(! (isOdd(quoteCountBeforeDelim)))
//            {
//                //number of quotes before delim is even ex. { "example", }
//                //therefore this is a valid delimiter.
//            }
//            if(isOdd(quoteCountAfterDelim))
//            //if so do not treat this comma as a delimiter and go to the next one.
//
//            //get everything after the deliminator
//            break;
//        }
//
//
//        return new String[3];
    }

    /**
     * Checks if a number is odd or even
     *  0%2 = 0 so this will return true;
     * -1%2 = -1 so it will return false;
     * @param n - number
     * @return true if number is odd, false of number is even.
     */
    private static boolean isOdd(int n){
        if(n%2 == 0)
            return true;
        else
            return false;
    }

}

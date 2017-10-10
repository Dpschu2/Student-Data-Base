/**
 * This class contains static methods that can be used throughout the program.
 * These methods are more specifically useful throughout when reading as input
 */
package edu.ilstu;
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
}

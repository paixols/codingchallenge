import java.util.HashSet;
import java.util.Set;

/**
 * Collection of debugging exercises
 * - Comment & change the broken code to produce the desired result
 * - First go through the assessment https://aonecode.com/amazon-online-assessment-debugging-questions
 */

public class DebuggingExercises {

    public static void main(String[] args) {

        //Calculate sum of numbers in string
        System.out.print("Sum: " + calculateSumOfNumbersInString("199"));

        //Check Pair Sum Exists
        System.out.print("\nCheck pair sum exists: " + checkPairSumExists(3, 3, new int[][]{{1, 2, 3}, {1, 2, 3}}, 4));

        //Remove consecutive vowels
        System.out.print("\nRemove consecutive vowels: " + removeConsecutiveVowels("aba"));

        //Reverse alphabetical order
        System.out.print("\nReverse alphabetical order: " + reverseAlphabetCharactersOnly("^a+b-c+"));

        //Compare product
        System.out.print("\nOdd product: " + compareProduct(2841));

        //Triplet Sum
        System.out.print("\nTriplet sum: " + countTripletSumPermutations(5, new int[]{1, 1, 2, 2, 4}, 2) + "\n");

    }


    /*
     * Calculate Sum of numbers in String:
     * The following function returns a boolean value representing if there is a pair with given sum exists in the array.
     * */
    public static int calculateSumOfNumbersInString(String inputString) {
        String temp = "";
        int sum = 0;
        for (int i = 0; i < inputString.length(); i++) {
            char ch = inputString.charAt(i);
            if (Character.isDigit(ch))
                temp += ch;
            else
                sum += Integer.parseInt(temp);
            //temp = "0";
        }
        return sum + Integer.parseInt(temp);
    }

    /*
     * Check Pair Sum Exists:
     * The following function returns a boolean value representing if there is a pair with given sum exists in the array.
     * */
    public static boolean checkPairSumExists(int rows, int cols, int[][] arr, int sum) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (set.contains(sum - arr[i][j])) {
                    return true;
                } else {
                    //set.add(sum);
                    set.add(arr[i][j]);
                }
            }
        }
        return false;
    }

    /*
     * Remove Consecutive Vowels
     * The following function returns a string value representing the string left after removing consecutive vowels for
     * the string.
     * */
    public static boolean is_vowel(char ch) {
        return (ch == 'a') || (ch == 'e') || (ch == 'i') || (ch == 'o') || (ch == 'u');
    }

    public static String removeConsecutiveVowels(String str) {
        String str1 = "";
        str1 = str1 + str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if ((!is_vowel(str.charAt(i - 1))) || !is_vowel(str.charAt(i))) {
                //if ((!is_vowel(str.charAt(i - 1))) && !is_vowel(str.charAt(i))) {
                char ch = str.charAt(i);
                str1 = str1 + ch;
            }
        }
        return str1;
    }

    /*
     * Reverse Alphabet Chars Only
     * The following function returns a string representing the reversed string in such way that the position of the
     * special chars are not affected.
     * */

    public static String reverseAlphabetCharactersOnly(String inputString) {
        char[] inputChar = inputString.toCharArray();
        int right = inputString.length() - 1;
        int left = 0;

        while (left < right) {
            if (!Character.isAlphabetic(inputChar[left])) {
                left++;
            } else if (!Character.isAlphabetic(inputChar[right])) {
                right--;
            } else {
                char temp = inputChar[left];
                inputChar[left] = inputChar[right];
                inputChar[right] = temp;
                left++;
                right--;
            }
            //left ++;
            //right --;
        }
        return new String(inputChar);
    }

    /*
     * Compare product
     * The following function returns a Boolean value "true" if the product of digits at the even and odd places of a
     * number are equal.
     * */
    public static boolean compareProduct(int num) {
        if (num < 10) {
            return false;
        }

        int oddProdValue = 1;
        int evenProdValue = 1;

        while (num > 0) {

            //int digit = num / 10;
            int digit = num % 10;
            oddProdValue *= digit;
            num /= 10;

            if (num == 0) {
                break;
            }

            //digit = num / 10;
            digit = num % 10;
            evenProdValue *= digit;
            num /= 10;
        }

        if (evenProdValue == oddProdValue) {
            return true;
        }
        return false;
    }

    /*
     * Count Triplet Sum Permutations
     * The following function countTripletSumPermutations returns an integer representing the number of triplets from the
     * list whose product is equal to the given triplet sum.
     * */
    public static int countTripletSumPermutations(int size, int[] arr, int tripletSum) {
        int count = 0;
        for (int i = 0; i < size - 2; i++) {
            if (tripletSum % arr[i] == 0) {
                //for (int j = i; j < size - 1; j ++) {
                for (int j = i + 1; j < size - 1; j++) {
                    if (tripletSum % (arr[i] * arr[j]) == 0) {
                        int value = tripletSum / (arr[i] * arr[j]);
                        for (int k = j + 1; k < size; k++) {
                            if (arr[k] == value) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

}

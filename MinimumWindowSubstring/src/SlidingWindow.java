import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    public static void main(String[] args) {

        /*
         * The question asks us to return the minimum window from the String S which has all the characters of the string T.
         * Let us call a window desirable if it has all the characters from T.
         *
         * We can use a simple sliding window approach to solve this problem.
         *
         * In any sliding window based problem we have two pointers. One right pointer whose job is to expand the current
         * window and then we have  the left pointer whose job is to contract a given window. At any pint in time only one
         * these pointers move and the other one remains fixed.
         *
         * The solution is pretty intuitive. We keep expanding the window by moving the right pointer. When the window has
         * all the desired characters, we contract (if possible) and save the smallest window till now.
         *
         * The answer is the smallest desirable window
         *
         * - Test cases:
         *  S = ADOBECODEBANC, T = ABC
         *  S = a , T = a
         *  S = ab, T = a
         * */

        /*
         * Java Version
         * */
        System.out.print(minimumWindowSubstring("ADOBECODEBANC", "ABC") + "\n");

        /*
         * Kotlin Version
         * */
        System.out.print(
                KtSlidingWindow.Companion.getInstance().minimumWindowSubstring("ADOBECODEBANC", "ABC") + "\n"
        );

        /*
         * Kotlin Optimized Version
         * */
        System.out.print(
                KtSlidingWindow.Companion.getInstance().optimalSolution("ADOBECODEBANC", "ABC") + "\n"
        );

    }

    public static String minimumWindowSubstring(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();

        // Left and Right pointer
        int l = 0, r = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                l++;
            }

            // Keep expanding the window once we are done contracting.
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }


}

class KtSlidingWindow {

    object Holder {
        val instance: KtSlidingWindow = KtSlidingWindow()
    }

    companion object {
        val instance: KtSlidingWindow = Holder.instance
    }

    /*
    * My solution
    * */
    fun minimumWindowSubstring(s: String, t: String): String {
        //Edge case
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }
        //Dictionary which keeps a count of all the unique characters in t
        val dicT: MutableMap<Char, Int> = hashMapOf()
        for (char in t.toCharArray()) {
            val count = dicT.getOrDefault(char, 0)
            dicT[char] = count + 1
        }

        //Number of unique characters in t, which need to be present in the desired window.
        val required = dicT.size

        //Left and right pointer
        var l = 0
        var r = 0

        /*
        * [formed] is used to keep track of how many unique characters in t are present in the current window
        * in it's desired frequency.
        * E.g. if t is "AABC" then the window must have two A's, one B and one C.
        * Thus formed would be = 3 when all these conditions are met
        * */
        var formed = 0;

        //Dictionary which keeps a count of all the unique characters in the current window
        val windowCounts: MutableMap<Char, Int> = hashMapOf()

        //List of the form (window length, left , right)
        val ans: MutableList<Int> = mutableListOf(-1, 0, 0)

        while (r < s.length) {
            //Add one character to the window by moving to the right.
            var c = s[r]
            val count = windowCounts.getOrDefault(c, 0)
            windowCounts[c] = count + 1

            /*
            * If the frequency of the current character added equals to the desired count in t
            * increment the formed count by 1
            * */
            if (dicT.containsKey(c) && windowCounts[c] == dicT[c]) {
                formed++
            }

            //Try and contract the window until the point where it ceases to be desirable
            while (l <= r && formed == required) {
                c = s[l]
                //Save the smallest window until now
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1
                    ans[1] = l
                    ans[2] = r
                }

                //The character at the position pointed by the left pointer is no longer part of the window

                windowCounts[c]?.let { windowCounts[c] = it - 1 }
                val currentWindowCharFrequency = windowCounts[c] ?: 0
                val currentDictCharFrequency = dicT[c] ?: 0
                if (dicT.containsKey(c) && currentWindowCharFrequency < currentDictCharFrequency) {
                    formed --
                }
                //Move left pointer to the right
                l++
            }
            //Keep expanding the window once we are done contracting it
            r++

        }

        return if (ans[0] == -1) "" else s.substring(ans[1], ans[2] + 1)
    }

    /*
    * Optimal researched solution
    * */
    fun optimalSolution(s: String, t:String): String {
        val lenS = s.length
        var (minStart, minLen) = 0 to lenS + 1
        var (windowStart, windowEnd) = 0 to 0
        var missing = t.length
        val pattern = IntArray(128)
        for (ch in t) {
            pattern[ch.toInt()]++
        }
        while (windowEnd < lenS) {
            val ch = s[windowEnd].toInt() // int representation of the char
            if (pattern[ch] > 0) missing--
            pattern[ch]--
            while (missing == 0 && windowStart <= windowEnd) {
                if (windowEnd - windowStart < minLen) {
                    minLen = windowEnd - windowStart
                    minStart = windowStart
                }
                val releasedChar = s[windowStart].toInt()
                pattern[releasedChar]++
                if (pattern[releasedChar] > 0) missing++
                windowStart++
            }
            windowEnd++
        }
        if (minLen == lenS + 1) return ""
        return s.substring(minStart, minStart + minLen + 1)
    }
}
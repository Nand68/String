class Solution {

    public int shortestMatchingSubstring(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        if (patternLength == 2) {
            return 0;
        }

        List<Integer> starPositions = new ArrayList<>();
        for (int i = 0; i < patternLength; i++) {
            if (pattern.charAt(i) == '*') {
                starPositions.add(i);
            }
        }

        String prefix = pattern.substring(0, starPositions.get(0));
        String middle = pattern.substring(starPositions.get(0) + 1, starPositions.get(1));
        String suffix = pattern.substring(starPositions.get(1) + 1);

        int prefixLen = prefix.length();
        int middleLen = middle.length();
        int suffixLen = suffix.length();

        int[] prefixLPS = computeLPS(prefix + "#" + text);
        int[] middleLPS = computeLPS(middle + "#" + text);
        int[] suffixLPS = computeLPS(suffix + "#" + text);

        prefixLPS = Arrays.copyOfRange(prefixLPS, prefixLen + 1, prefixLPS.length);
        middleLPS = Arrays.copyOfRange(middleLPS, middleLen + 1, middleLPS.length);
        suffixLPS = Arrays.copyOfRange(suffixLPS, suffixLen + 1, suffixLPS.length);

        int minLength = Integer.MAX_VALUE;
        int i = 0, j = 0, k = 0;

        while (i + middleLen + suffixLen < textLength) {
            while (i < textLength && prefixLPS[i] != prefixLen)
                i++;
            if (i >= textLength)
                break;

            while (j < textLength && (j < i + middleLen || middleLPS[j] != middleLen))
                j++;
            if (j >= textLength)
                break;

            while (k < textLength && (k < j + suffixLen || suffixLPS[k] != suffixLen))
                k++;
            if (k >= textLength)
                break;

            minLength = Math.min(minLength, k - i + prefixLen);
            i++;
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int maxlen = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(maxlen)) {
                maxlen++;
                lps[i] = maxlen;
                i++;
            } else {
                if (maxlen > 0) {
                    maxlen = lps[maxlen - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

}

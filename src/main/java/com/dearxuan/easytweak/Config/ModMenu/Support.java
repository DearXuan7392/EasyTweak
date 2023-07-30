package com.dearxuan.easytweak.Config.ModMenu;

public class Support {

    public static final String[] StringsEmpty = new String[]{};

    /**
     * 判断字符串是否匹配
     */
    public static boolean isMatch(String str, String pattern) {
        assert str != null && pattern != null;
        boolean[][] dp = new boolean[str.length() + 1][pattern.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pattern.length(); ++i) {
            if (pattern.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= str.length(); ++i) {
            for (int j = 1; j <= pattern.length(); ++j) {
                if (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[str.length()][pattern.length()];
    }
}

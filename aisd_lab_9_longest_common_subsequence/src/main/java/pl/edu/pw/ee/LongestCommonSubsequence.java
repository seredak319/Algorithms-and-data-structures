package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private final String left;
    private final String top;
    private int[][] map;
    private char[][] sign;
    private char[][] signFinalResult;
    private boolean alreadyFound = false;

    public LongestCommonSubsequence(String leftSrc, String topSrc) {
        this.left = leftSrc;
        this.top = topSrc;

        if (left != null && top != null) {
            map = new int[left.length() + 1][top.length() + 1];
            sign = new char[left.length() + 1][top.length() + 1];
            signFinalResult = new char[left.length() + 1][top.length() + 1];

            for (int i = 0; i < left.length(); i++) {
                map[i][0] = 0;
            }

            for (int i = 0; i < top.length(); i++) {
                map[0][i] = 0;
            }
        }

    }

    public String findLCS() {
        alreadyFound = true;
        if (left == null || top == null) {
            return new String("");
        }

        for (int i = 1; i <= left.length(); i++) {
            for (int j = 1; j <= top.length(); j++) {
                if (left.charAt(i - 1) == top.charAt(j - 1)) {
                    map[i][j] = map[i - 1][j - 1] + 1;
                    sign[i][j] = '\\';

                } else {
                    if (map[i][j - 1] >= map[i - 1][j]) {
                        map[i][j] = map[i][j - 1];
                        sign[i][j] = '<';
                    } else {
                        map[i][j] = map[i - 1][j];
                        sign[i][j] = '^';
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        createString(sb, left.length(), top.length());

        return sb.reverse().toString();
    }

    private void createString(StringBuilder sb, int i, int j) {
        if (map[i][j] == 0) {
            return;
        }

        if (sign[i][j] == '\\') {
            signFinalResult[i][j] = '\\';
            sb.append(top.charAt(j - 1));
            createString(sb, i - 1, j - 1);
        } else if (sign[i][j] == '^') {
            signFinalResult[i][j] = '^';
            createString(sb, i - 1, j);
        } else if (sign[i][j] == '<') {
            signFinalResult[i][j] = '<';
            createString(sb, i, j - 1);
        } else if (sign[i][j] != '\0') {
            throw new IllegalArgumentException("String cannot be created, illegal character given\n"
                    + ":\t[ " + sign[i][j] + " ]\n");
        }
    }

    public void display() {
        if (!alreadyFound) {
            throw new NullPointerException("There is nothing to display, use lcs.findLCS()");
        }

        if (map == null) {
            return;
        }

        sign = signFinalResult;

        StringBuilder display = new StringBuilder();
        int[] sizes = new int[top.length() + 2];
        sizes[0] = 3;
        sizes[1] = 1;
        for (int i = 0; i < top.length(); i++) {
            int lengthOfSubstring = map[left.length()][i + 1];
            int requireSpace = Integer.toString(lengthOfSubstring).length();
            sizes[i + 2] = requireSpace;
        }

        int gapSize = 4;

        for (int i = 0; i < left.length() + 2; i++) {
            for (int k = 0; k < 4; k++) {
                display.append('\n');
                for (int j = 0; j < top.length() + 2; j++) {
                    switch (k) {
                        case 0: {
                            display.append('+');
                            display.append("-".repeat(Math.max(0, gapSize + sizes[j])));
                            if (j == top.length() + 1) {
                                display.append('+');
                            }
                            break;
                        }
                        case 2: {
                            display.append('|');
                            if (i == 0) {
                                if (j == 0 || j == 1) {
                                    repeatSpace(display, 4 + sizes[j]);
                                } else {
                                    repeatSpace(display, 2);
                                    if (isSpecialChar(top.charAt(j - 2))) {
                                        appendSpecialChar(display, top.charAt(j - 2));

                                        repeatSpace(display, 1 + (sizes[j] - Integer.toString(map[i][j - 1]).length()));
                                    } else {
                                        display.append(top.charAt(j - 2));
                                        repeatSpace(display, 2 + (sizes[j] - Integer.toString(map[i][j - 1]).length()));
                                    }

                                }
                            } else if (i == 1) {
                                if (j == 0) {
                                    repeatSpace(display, gapSize + sizes[j]);
                                } else {
                                    repeatSpace(display, 2);
                                    display.append(map[i - 1][j - 1]);
                                    repeatSpace(display, 2 + (sizes[j] - Integer.toString(map[i - 1][j - 1]).length()));
                                }
                            } else {
                                if (j == 0) {
                                    repeatSpace(display, 3);
                                    if (isSpecialChar(left.charAt(i - 2))) {
                                        appendSpecialChar(display, left.charAt(i - 2));
                                        repeatSpace(display, 2);
                                    } else {
                                        display.append(left.charAt(i - 2));
                                        repeatSpace(display, 3);
                                    }

                                } else {
                                    if (sign[i - 1][j - 1] == '<') {
                                        display.append("< ").append(map[i - 1][j - 1]);
                                        repeatSpace(display, 2 + (sizes[j] - Integer.toString(map[i - 1][j - 1]).length()));
                                    } else {
                                        repeatSpace(display, 2);
                                        display.append(map[i - 1][j - 1]);
                                        repeatSpace(display, 2 + (sizes[j] - Integer.toString(map[i - 1][j - 1]).length()));
                                    }
                                }
                            }
                            if (j == top.length() + 1) {
                                display.append('|');
                            }
                            break;
                        }
                        case 3: {
                            display.append('|');
                            repeatSpace(display, 4 + sizes[j]);
                            if (j == top.length() + 1) {
                                display.append('|');
                            }
                            break;
                        }
                        case 1: {
                            display.append('|');
                            if ((i >= 1 && j >= 1) && sign[i - 1][j - 1] == '\\') {
                                display.append('\\');
                                repeatSpace(display, 3 + sizes[j]);
                            } else if ((i >= 1 && j >= 1) && sign[i - 1][j - 1] == '^') {
                                repeatSpace(display, 2);
                                display.append('^');
                                repeatSpace(display, 1 + sizes[j]);
                            } else {
                                repeatSpace(display, 4 + sizes[j]);
                            }
                            if (j == top.length() + 1) {
                                display.append('|');
                            }
                            break;
                        }
                    }
                }
            }
        }
        display.append('\n');
        for (int i = 0; i < top.length() + 2; i++) {
            display.append('+');
            display.append("-".repeat(Math.max(0, gapSize + sizes[i])));
            if (i == top.length() + 1) {
                display.append('+');
            }
        }

        System.out.println(display);
    }

    private void repeatSpace(StringBuilder sb, int n) {
        sb.append(" ".repeat(n));
    }

    private boolean isSpecialChar(char c) {
        return c == '\'' || c == '\"' || c == '\\' || c == '\t' || c == '\b' || c == '\r' || c == '\f' || c == '\n';
    }

    private void appendSpecialChar(StringBuilder sb, char c) {
        switch (c) {
            case '\n': {
                sb.append(("\\" + "n"));
                break;
            }
            case '\"': {
                sb.append(("\\" + "\""));
                break;
            }
            case '\'': {
                sb.append(("\\" + "'"));
                break;
            }
            case '\t': {
                sb.append(("\\" + "t"));
                break;
            }
            case '\b': {
                sb.append(("\\" + "b"));
                break;
            }
            case '\r': {
                sb.append(("\\" + "r"));
                break;
            }
            case '\f': {
                sb.append(("\\" + "f"));
                break;
            }
            case '\\': {
                sb.append(("\\" + "\\"));
                break;
            }
        }
    }
}

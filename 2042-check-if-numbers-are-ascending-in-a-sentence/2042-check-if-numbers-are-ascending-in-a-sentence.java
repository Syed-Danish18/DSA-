class Solution {
    public boolean areNumbersAscending(String s) {
        int value = 0;
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            else if (num > 0) {
                if (num <= value) {
                    return false;
                }

                value = num;
                num = 0;
            }
        }

        // Last number check
        if (num > 0 && num <= value) {
            return false;
        }

        return true;
    }
}
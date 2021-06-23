package second_compiler_attempt;

import compiling.tokens.TokenNames;

import java.util.ArrayList;

public class Tokenizer {
    public static String[] tokenize(String string) {
        ArrayList<Integer> positionsToAddSpace = new ArrayList<>();
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (matchesArray(TokenNames.operator_keywords, "" + charArray[i])
                    || matchesArray(TokenNames.special_keywords, "" + charArray[i])
            ) {
                positionsToAddSpace.add(i);
                positionsToAddSpace.add(i + 1);
            }
        }
        int offset = 0;
        for (int i = 0; i < positionsToAddSpace.size(); i++) {
            string = string.substring(0, positionsToAddSpace.get(i) + offset) + " " + string.substring(positionsToAddSpace.get(i) + offset);
            offset++;
        }
        String[] strings = string.split("\\s+|\\n");
        return strings;
    }
    private static boolean matchesArray(String[] strings, String string) {
        for(String str : strings) {
            if(str.equals(string))
                return true;
        }
        return false;
    }
}

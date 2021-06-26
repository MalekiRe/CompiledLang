package third_compiler_attempt;


import java.util.ArrayList;

public class Tokenizer {
    public static String[] tokenize(String string) {
        ArrayList<Integer> positionsToAddSpace = new ArrayList<>();
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (matchesArray(PunctuationType.single_char_operators, "" + charArray[i])
                    || matchesArray(PunctuationType.punctuations, "" + charArray[i])
            ) {
                if (i + 1 < charArray.length) {
                    if (matchesArray(PunctuationType.multi_char_operators, "" + charArray[i] + "" + charArray[i + 1])
                            || matchesArray(PunctuationType.multi_char_operators, "" + charArray[i - 1] + "" + charArray[i])) {
                        continue;
                    }
                }
                positionsToAddSpace.add(i);
                positionsToAddSpace.add(i + 1);
            }
        }
        int offset = 0;
        for (Integer integer : positionsToAddSpace) {
            string = string.substring(0, integer + offset) + " " + string.substring(integer + offset);
            offset++;
        }
        char[] charArray2 = string.toCharArray();
        String[] strings = string.split("\\s+|\\n");
        return strings;
    }

    public static boolean matchesArray(String[] strings, String string) {
        for (String str : strings) {
            if (str.equals(string))
                return true;
        }
        return false;
    }
}

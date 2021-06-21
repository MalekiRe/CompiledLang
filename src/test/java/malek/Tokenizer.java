package malek;

import compiling.tokens.TokenNames;
import malek.tokens.*;

import java.util.ArrayList;

public class Tokenizer {


    public static AbstractToken[] tokenize(String string) {
        ArrayList<Integer> positionsToAddSpace = new ArrayList<>();
        char[] charArray = string.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            if(matchesArray(TokenNames.operator_keywords, ""+charArray[i])
            || matchesArray(TokenNames.special_keywords, ""+charArray[i])
            ) {
                positionsToAddSpace.add(i);
                positionsToAddSpace.add(i+1);
            }
        }
        int offset = 0;
        for(int i = 0; i < positionsToAddSpace.size(); i++) {
            string = string.substring(0, positionsToAddSpace.get(i)+offset) + " " + string.substring(positionsToAddSpace.get(i)+offset);
            offset++;
        }
        String[] strings = string.split("\\s+|\\n");
        AbstractToken[] tokens = new AbstractToken[strings.length];

        for(int currentToken = 0; currentToken < strings.length; currentToken++) {
            if (matchesArray(TokenNames.access_keywords, strings[currentToken])) {
                tokens[currentToken] = new AccessToken();
                tokens[currentToken].setText(strings[currentToken]);
            } else if (matchesArray(TokenNames.type_keywords, strings[currentToken])) {
                tokens[currentToken] = new TypeToken();
                tokens[currentToken].setText(strings[currentToken]);
            } else if (matchesArray(TokenNames.operator_keywords, strings[currentToken])) {
                tokens[currentToken] = new OperatorToken();
                tokens[currentToken].setText(strings[currentToken]);
            } else if (matchesArray(TokenNames.special_keywords, strings[currentToken])) {
                tokens[currentToken] = new SpecialToken();
                tokens[currentToken].setText(strings[currentToken]);
            }
            else if(Character.isDigit(strings[currentToken].toCharArray()[0])
            || (currentToken > 0 && (strings[currentToken-1].toCharArray()[0]) == '\'')) {
                tokens[currentToken] = new LiteralToken();
                tokens[currentToken].setText(strings[currentToken]);
            }
            else {
                tokens[currentToken] = new IdToken();
                tokens[currentToken].setText(strings[currentToken]);
            }
        }
        return tokens;
    }
    private static boolean matchesArray(String[] strings, String string) {
        for(String str : strings) {
            if(str.equals(string))
                return true;
        }
        return false;
    }
}

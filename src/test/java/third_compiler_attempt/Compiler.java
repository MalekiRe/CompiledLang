package third_compiler_attempt;


import third_compiler_attempt.ast.ASTToken;
import third_compiler_attempt.enums.HighLevelType;
import third_compiler_attempt.enums.IDType;
import third_compiler_attempt.enums.VariableType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static third_compiler_attempt.enums.HighLevelType.VARIABLE;
import static third_compiler_attempt.enums.HighLevelType.*;
import static third_compiler_attempt.enums.IDType.*;
import static third_compiler_attempt.enums.VariableType.*;


public class Compiler {
    public static void main(String[] args) throws IOException {
        String str;
        {
            FileInputStream src = new FileInputStream("test/src/MalekFieldTest.langsrc");
            byte[] bytes = new byte[src.available()];
            src.read(bytes);
            src.close();
            str = new String(bytes);
        }
        System.out.println(str);
        str = str.replace("\r", "");
        StringBuilder out = new StringBuilder();
        ArrayList<String> access = new ArrayList<>();
        String[] tokens = Tokenizer.tokenize(str);
        System.out.print("[");
        for (String string : tokens) {
            System.out.print(string + ",");
        }
        System.out.print("]\n");
        ContextToken[] contextTokens = new ContextToken[tokens.length];
        ArrayList<ASTToken> children = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            contextTokens[i] = new ContextToken(getHighLevelType(tokens[i]), tokens[i]);
        }
//        int numberOfChildren = 0;
//        for(int i = 0; i < contextTokens.length; i++) {
//            if(contextTokens[i].type == HighLevelType.ID) {
//                System.out.println(contextTokens[i].value);
//                int i2 = i;
//                while(!contextTokens[i2].value.equals("{")) {
//                    i2++;
//                }
//                i2++;
//                int brackets = 1;
//                numberOfChildren++;
//                for(int i3 = i2; brackets != 0; i3++) {
//                    if (contextTokens[i3].value.equals("{")) brackets++;
//                    if (contextTokens[i3].value.equals("}")) brackets--;
//                    i = i3;
//                }
//
//            }
//        }
//        int filledChildren = 0;
//        children = new ASTToken[numberOfChildren];
//        for(int i = 0; i < contextTokens.length; i++) {
//            if(contextTokens[i].type == HighLevelType.ID) {
//                int saveStarting = i;
//                int i2 = i;
//                while(!contextTokens[i2].value.equals("{")) {
//                    i2++;
//                }
//                i2++;
//                int brackets = 1;
//
//                for(int i3 = i2; brackets != 0; i3++) {
//                    if (contextTokens[i3].value.equals("{")) brackets++;
//                    if (contextTokens[i3].value.equals("}")) brackets--;
//                    i = i3;
//                }
//                children[filledChildren] = new ASTToken(contextTokens, saveStarting, 0, i);
//            }
      //  }
        int brackets = 1;
        int i2 = 0;
        while(!contextTokens[i2].value.equals("class")) {
            i2++;
        }
        i2++;
        children.add(new ASTToken(CREATE_CLASS, contextTokens, i2));
        for(ASTToken token : children) {
            if(token != null) {
                System.out.println(token.getString(0));
            }
        }

//        System.out.println(children.get(0).children.get(0).children.get(0).getString());


    }
    public static VariableType getVariableTypeFromToken(ContextToken[] contextTokens, int startIndex) {
        switch (contextTokens[startIndex-1].value) {
            case "int" : return INT;
            case "char" : return CHAR;
            case "double" : return DOUBLE;
            case "float" : return FLOAT;
            case "boolean" : return BOOLEAN;
            case "class" : return CLASS;
            case "void" : return VOID;
        }
        //Bandaid fix for booleans.
        switch (contextTokens[startIndex].value) {
            case "true" :
            case "false" :
                return BOOLEAN;
        }
        return CLASS;
    }
    public static IDType getIDType(ContextToken[] contextTokens, int startIndex) {
        if(startIndex <= 1) {
            if(contextTokens[startIndex].type == ID)
                return CREATE_CLASS;
            return NULL;
        }
        if(contextTokens[startIndex].type != ID) {
            return NULL;
        }
        if(contextTokens[startIndex-1].value.equals("new")) {
            //The only case you would have "new" before something is if you are instantaing a new class.
            return NEW_CLASS;
        }
        if(contextTokens[startIndex-1].type == VARIABLE) {
            if(contextTokens[startIndex+1].value.equals("{")) {
                if(contextTokens[startIndex-1].value.equals("class")) {
                    /*
                    public class MyClass {
                    }
                     */
                    return CREATE_CLASS;
                }
            }
            if(contextTokens[startIndex+1].value.equals("(")) {
                /*
                public static void myMethod() {
                }
                 */
                return CREATE_METHOD;
            }
            /*
            int myInt;
             */
            return INSTANTIATE_VARIABLE;
        }
        if(contextTokens[startIndex+1].value.equals("(")) {
            /*
            myMethod();
             */
            return CALL_METHOD;
        }
        if(contextTokens[startIndex-1].type == ID) {
            /*
            when you make an object like
            MyObject myObject;
             */
            return INSTANTIATE_VARIABLE;
        }
        //Everything else.
        return CALL_VARIABLE;
    }
    public static HighLevelType getHighLevelType(String token) {
        if(matchesArray(PunctuationType.access_modifiers, token)) {
            return ACCESS_MODIFIER;
        }
        if(matchesArray(PunctuationType.operators, token)) {
            return OPERATOR;
        }
        if(matchesArray(PunctuationType.punctuations, token)) {
            return PUNCTUATION;
        }
        if(matchesArray(PunctuationType.variables, token)) {
            return VARIABLE;
        }
        else {
            return ID;
        }
    }
    private static boolean matchesArray(String[] strings, String string) {
        for(String str : strings) {
            if(str.equals(string))
                return true;
        }
        return false;
    }
}

package second_compiler_attempt;

import second_compiler_attempt.enums.*;
import second_compiler_attempt.tokens.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static second_compiler_attempt.enums.Context.GLOBAL;
import static second_compiler_attempt.enums.HighLevelType.*;
import static second_compiler_attempt.enums.IDType.*;
import static second_compiler_attempt.enums.VariableType.*;

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
        for(String string : tokens) {
            System.out.print(string +",");
        }
        System.out.print("]\n");
        ContextToken[] contextTokens = new ContextToken[tokens.length];

        for(int i = 0; i < tokens.length; i++) {
            contextTokens[i] = new ContextToken(getHighLevelType(tokens[i]), tokens[i]);
        }

//        System.out.println(new MethodToken(VOID, "main").getStringRep());
//        VariableToken int1 = new VariableToken("int1", INT);
//        MethodToken temp = new MethodToken(INT, "addOne");
//        temp.arguments.add(int1);
//        System.out.println(temp.getStringRep());
        Context currentContext = GLOBAL;
        Token[] tokens2 = new Token[contextTokens.length];
        ArrayList<ClassToken> classTokens = new ArrayList<>();
        for(int i = 0; i < contextTokens.length; i++) {
            if(contextTokens[i].value.equals("class")) {
                i++;
                classTokens.add(new ClassToken(CLASS, tokens[i], contextTokens, i, StaticLevel.NON_STATIC, FinalLevel.MUTABLE, ProtectionLevel.PUBLIC));
            }
        }
        for(ClassToken classToken : classTokens) {
            System.out.println(classToken.toString());
        }


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
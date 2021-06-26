package third_compiler_attempt;


import compiling.Cleaner;
import second_compiler_attempt.tokens.FileToken;
import third_compiler_attempt.action_tree.ActionFile;
import third_compiler_attempt.ast.ASTToken;
import third_compiler_attempt.enums.*;
import third_compiler_attempt.tokens.BaseToken;
import third_compiler_attempt.tokens.InstantiableToken;
import third_compiler_attempt.tokens.class_tokens.ClassToken;
import third_compiler_attempt.tokens.method_tokens.MethodToken;
import third_compiler_attempt.tokens.variable_tokens.ClassVariableToken;
import third_compiler_attempt.tokens.variable_tokens.MethodVariableToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static third_compiler_attempt.enums.FinalLevel.FINAL;
import static third_compiler_attempt.enums.FinalLevel.MUTABLE;
import static third_compiler_attempt.enums.HighLevelType.VARIABLE;
import static third_compiler_attempt.enums.HighLevelType.*;
import static third_compiler_attempt.enums.IDType.*;
import static third_compiler_attempt.enums.IDType.CLASS;
import static third_compiler_attempt.enums.ProtectionLevel.PRIVATE;
import static third_compiler_attempt.enums.ProtectionLevel.PUBLIC;
import static third_compiler_attempt.enums.StaticLevel.NON_STATIC;
import static third_compiler_attempt.enums.StaticLevel.STATIC;
import static third_compiler_attempt.enums.VariableType.*;


public class Compiler {
    public static void main(String[] args) throws IOException {
        String str;
        {
            FileInputStream src = new FileInputStream("test/src/Test2.langsrc");
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
        //string = string.replaceAll("^.*-\\s\\s.*$", "-=");
        for (int i = 0; i < tokens.length; i++) {
            contextTokens[i] = new ContextToken(getHighLevelType(tokens[i]), tokens[i]);
        }
        int brackets = 1;
        int i2 = 0;
        while(!contextTokens[i2].value.equals("class")) {
            i2++;
        }
        i2++;
        children.add(new ASTToken(CREATE_CLASS, contextTokens, i2));
        for(ASTToken token : children) {
            if(token != null) {
                System.out.println(token.getString(1));
            }
        }
        ArrayList<ClassToken> classTokens = new ArrayList<>();
        AccessLevel tempDefault = new AccessLevel(PUBLIC, NON_STATIC, MUTABLE);
        for(ASTToken astToken : children) {
            if(astToken != null) {
                classTokens.add(ClassToken.createClassToken(contextTokens, astToken, tempDefault));
            }
        }

        for(ClassToken classToken : classTokens) {
            System.out.println(classToken.getStringRep());
        }









        //System.out.println(new MethodVariableToken(NON_STATIC, FinalLevel.MUTABLE, PUBLIC, "myInt", INT).getStringRep());
//        InstantiableToken t = new ClassVariableToken(STATIC, FINAL, PRIVATE, NON_STATIC, MUTABLE, PUBLIC, "classChar", CHAR);
//        InstantiableToken t2 = new ClassVariableToken(STATIC, FINAL, PRIVATE, NON_STATIC, MUTABLE, PUBLIC, "classInt", INT);
//        ClassToken classToken = new ClassToken(NON_STATIC, MUTABLE, PUBLIC, NON_STATIC, MUTABLE, PUBLIC, "MyClass");
//        classToken.addChild(t);
//        classToken.addChild(t2);
//
//        InstantiableToken t3 = new MethodVariableToken(STATIC, FINAL, PRIVATE, "testVar", BOOLEAN);
//        MethodToken method = new MethodToken(NON_STATIC, MUTABLE, PUBLIC, NON_STATIC, MUTABLE, PUBLIC, "test", BOOLEAN);
//        method.addChild(t3);
//        classToken.addChild(method);
//        System.out.println(classToken.getStringRep());
//        System.out.println(children.get(0).children.get(0).children.get(0).getString());


    }
    public static VariableType getVariableTypeFromToken(ContextToken[] contextTokens, int startIndex) {
        switch (contextTokens[startIndex-1].value) {
            case "int" : return INT;
            case "char" : return CHAR;
            case "double" : return DOUBLE;
            case "float" : return FLOAT;
            case "boolean" : return BOOLEAN;
            case "class" : return VariableType.CLASS;
            case "void" : return VOID;
        }
        //Bandaid fix for booleans.
        switch (contextTokens[startIndex].value) {
            case "true" :
            case "false" :
                return BOOLEAN;
        }
        return VariableType.CLASS;
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
//        if(getIDType(contextTokens, startIndex+1) == CALL_VARIABLE) {
//            return CLASS;
//        }
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

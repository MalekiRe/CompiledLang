package second_compiler_attempt.tokens;

import second_compiler_attempt.Compiler;
import second_compiler_attempt.ContextToken;
import second_compiler_attempt.enums.*;

import java.util.ArrayList;

import static second_compiler_attempt.enums.FinalLevel.FINAL;
import static second_compiler_attempt.enums.FinalLevel.MUTABLE;
import static second_compiler_attempt.enums.ProtectionLevel.*;
import static second_compiler_attempt.enums.StaticLevel.NON_STATIC;
import static second_compiler_attempt.enums.StaticLevel.STATIC;
import static second_compiler_attempt.enums.VariableType.CLASS;

public class ClassToken extends Token {
    public final VariableType returnType;
    public final String name;
    public FinalLevel finalLevel = MUTABLE;
    public ProtectionLevel protectionLevel = PUBLIC;
    public StaticLevel staticLevel = NON_STATIC;
    public ArrayList<VariableToken> arguments = new ArrayList<>();
    ContextToken[] childContextTokens;
    public Token[] childTokens;

    public ClassToken(VariableType returnType, String name, ContextToken[] contextTokens, int index, StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultP) {
        super(defaultS, defaultF, defaultP);
        this.returnType = returnType;
        this.name = name;
        determineEverything(contextTokens, index);
    }

    public void determineEverything(ContextToken[] contextTokens, int index) {
        determineAccessLevels(contextTokens, index - 2);
        setChildContextTokens(contextTokens, index);
        setChildTokens();
    }
    @Override
    public String toString() {
        return getHeader()+childTokensToString()+getFooter();
    }
    public String getHeader() {
        return "class " + name + " "+strProtLvl(protectionLevel)+strStaticLvl(staticLevel)+strFinalLvl(finalLevel) + "\n";
    }
    public String getFooter() {
        return "end";
    }

    public void setChildTokens() {
        int currentToken = 0;
        int childTokensLength = 0;
        for (int i = 0; i < childContextTokens.length; i++) {
            switch (Compiler.getIDType(childContextTokens, i)) {
                case CREATE_CLASS:
                    childTokensLength++;
                    int brackets = 1;
                    for (int i2 = i; brackets != 0; i2++) {
                        System.out.println(brackets);
                        if (childContextTokens[i2].value.equals("{")) brackets++;
                        if (childContextTokens[i2].value.equals("}")) brackets--;
                    }
                    break;
                case CREATE_METHOD:
                    childTokensLength++;
                    break;
                default :
            }
        }
        childTokens = new Token[childTokensLength];
        for (int i = 0; i < childContextTokens.length; i++) {
            switch (Compiler.getIDType(childContextTokens, i)) {
                case CREATE_CLASS:
                    childTokens[currentToken] = new ClassToken(CLASS, childContextTokens[i].value, childContextTokens, i, staticLevel, finalLevel, protectionLevel);
                    int brackets = 1;
                    for (int i2 = i; brackets != 0; i2++) {
                        if (childContextTokens[i2].value.equals("{")) brackets++;
                        if (childContextTokens[i2].value.equals("}")) brackets--;
                    }
                    currentToken++;
                    break;
                case CREATE_METHOD:
                    childTokens[currentToken] = new MethodToken(Compiler.getVariableTypeFromToken(childContextTokens, i), childContextTokens[i].value, staticLevel, finalLevel, protectionLevel, childContextTokens, currentToken);
                    currentToken++;
                    break;
            }
        }
    }

    public void setChildContextTokens(ContextToken[] contextTokens, int index) {
        int curlyBraces = 1;
        int childTokenLength = 0;
        for (int i = index + 2; i < contextTokens.length && curlyBraces != 0; i++) {
            if (contextTokens[i].type == HighLevelType.PUNCTUATION) {
                if (contextTokens[i].value.equals("{")) {
                    curlyBraces++;
                } else if (contextTokens[i].value.equals("}")) {
                    curlyBraces--;
                }
            }
            childTokenLength = i - (index + 2);
        }
        childContextTokens = new ContextToken[childTokenLength];
        for (int i = 0; i < childContextTokens.length; i++) {
            childContextTokens[i] = contextTokens[i + index + 2];
        }
    }

    public void determineAccessLevels(ContextToken[] contextTokens, int index) {
        determineFinalLevel(contextTokens, index);
        determineStaticLevel(contextTokens, index);
        determineProtectionLevel(contextTokens, index);
    }

    public void determineFinalLevel(ContextToken[] contextTokens, int index) {
        int i = index;
        while (i > 0 && contextTokens[i].type == HighLevelType.ACCESS_MODIFIER) {
            if (contextTokens[i].value.equals("final")) {
                finalLevel = FINAL;
            }
            i--;
        }
    }

    public void determineStaticLevel(ContextToken[] contextTokens, int index) {
        int i = index;
        while (i > 0 && contextTokens[i].type == HighLevelType.ACCESS_MODIFIER) {
            if (contextTokens[i].value.equals("static")) {
                staticLevel = STATIC;
            }
            i--;
        }
    }

    public void determineProtectionLevel(ContextToken[] contextTokens, int index) {
        int i = index;
        while (i > 0 && contextTokens[i].type == HighLevelType.ACCESS_MODIFIER) {
            if (contextTokens[i].value.equals("protected")) {
                protectionLevel = PROTECTED;
                return;
            }
            if (contextTokens[i].value.equals("private")) {
                protectionLevel = PRIVATE;
                return;
            }
            if (contextTokens[i].value.equals("public")) {
                protectionLevel = PUBLIC;
                return;
            }
            i--;
        }
    }
    public String childTokensToString() {
        StringBuilder str = new StringBuilder();
        for (Token token : childTokens) {
            str.append(token.toString());
        }
        return str.toString();
    }

    public String childContextTokensToString() {
        StringBuilder str = new StringBuilder();
        for (ContextToken token : childContextTokens) {
            str.append(token.toString());
        }
        return str.toString();
    }
}

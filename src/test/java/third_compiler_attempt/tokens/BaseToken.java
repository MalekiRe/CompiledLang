package third_compiler_attempt.tokens;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.ast.ASTToken;
import third_compiler_attempt.enums.*;
import third_compiler_attempt.ContextToken;

import static third_compiler_attempt.enums.FinalLevel.FINAL;
import static third_compiler_attempt.enums.ProtectionLevel.*;
import static third_compiler_attempt.enums.StaticLevel.STATIC;
import third_compiler_attempt.Compiler;
public abstract class BaseToken {

    public abstract String getStringRep();
    public static String getName(ContextToken[] contextTokens, ASTToken astToken) {
        return contextTokens[astToken.positionInContext].value;
    }
    public static AccessLevel findAccessLevel(ContextToken[] contextTokens, ASTToken astToken) {
        System.out.println(getName(contextTokens, astToken));
        AccessLevel accessLevel = new AccessLevel();
        int pos = astToken.positionInContext;
        while(pos != 0 && !accessLevel.everythingSet() && Compiler.getHighLevelType(contextTokens[pos].value) != HighLevelType.PUNCTUATION) {
            if(Compiler.getHighLevelType(contextTokens[pos].value) == HighLevelType.ACCESS_MODIFIER) {
                switch (contextTokens[pos].value) {
                    case "public" : accessLevel.p = PUBLIC;
                    case "private" : accessLevel.p = PRIVATE;
                    case "protected" : accessLevel.p = PROTECTED;
                    case "static" : accessLevel.s = STATIC;
                    case "final" : accessLevel.f = FINAL;
                }
            }
            pos--;
        }
        return accessLevel;
    }
    public static AccessLevel determineAccessLevel(ContextToken[] contextTokens, AccessLevel defaultAccess, ASTToken astToken) {
        AccessLevel newAccess = findAccessLevel(contextTokens, astToken);
        return setAccessLevels(defaultAccess, newAccess);
    }
    public static AccessLevel setAccessLevels(AccessLevel defaultAccess, AccessLevel newAccess) {
        System.out.println(newAccess.p);
        if(defaultAccess.p == PRIVATE) {
            newAccess.p = PRIVATE;
        }
        if(defaultAccess.s == STATIC) {
            newAccess.s = STATIC;
        }
        if(defaultAccess.f == FINAL) {
            newAccess.f = FINAL;
        }
        if(newAccess.p == PUBLIC && defaultAccess.p == PROTECTED) {
            newAccess.p = PROTECTED;
        }
        if(newAccess.s == null) {
            newAccess.s = defaultAccess.s;
        }
        if(newAccess.p == null) {
            newAccess.p = defaultAccess.p;
            newAccess.p = PUBLIC;
        }
        if(newAccess.f == null) {
            newAccess.f = defaultAccess.f;
        }
        return newAccess;
    }
    protected static String strProtLvl(ProtectionLevel protectionLevel) {
        switch (protectionLevel) {
            case PUBLIC: return "P";
            case PRIVATE: return "I";
            case PROTECTED: return "O";
        }
        return "";
    }
    public static String strStaticLvl(StaticLevel staticLevel) {
        switch (staticLevel) {
            case STATIC: return "S";
            case NON_STATIC: return "";
        }
        return "";
    }
    public static String strFinalLvl(FinalLevel finalLevel) {
        switch (finalLevel) {
            case FINAL: return "F";
            case MUTABLE: return "";
        }
        return "";
    }
    public String variableTypeToString(VariableType type) {
        //DOESNT HANDLE CLASS VARIABLE TYPE
        switch (type) {
            case BOOLEAN: return "B";
            case FLOAT: return "F";
            case DOUBLE: return "D";
            case INT: return "I";
            case CHAR: return "C";
            case VOID: return "V";
        }
        System.out.println("TRIED TO CONVERT CLASS VARIABLE TYPE TO STRING ERROR");
        return "";
    }
}

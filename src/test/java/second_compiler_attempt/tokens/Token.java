package second_compiler_attempt.tokens;

import second_compiler_attempt.ContextToken;
import second_compiler_attempt.enums.FinalLevel;
import second_compiler_attempt.enums.ProtectionLevel;
import second_compiler_attempt.enums.StaticLevel;
import second_compiler_attempt.enums.VariableType;

import java.util.function.Consumer;

public class Token {
    public final StaticLevel defaultStaticLevel;
    public final FinalLevel defaultFinalLevel;
    public final ProtectionLevel defaultProtectionLevel;
    public Token(StaticLevel defaultStaticLevel, FinalLevel defaultFinalLevel, ProtectionLevel defaultProtectionLevel) {
        this.defaultStaticLevel = defaultStaticLevel;

        this.defaultFinalLevel = defaultFinalLevel;
        this.defaultProtectionLevel = defaultProtectionLevel;
    }
    public String getStringRep() {
        return this.toString();
    }
    protected static String strProtLvl(ProtectionLevel protectionLevel) {
        switch (protectionLevel) {
            case PUBLIC: return "P";
            case PRIVATE: return "I";
            case PROTECTED: return "O";
        }
        return "";
    }
    protected static String strStaticLvl(StaticLevel staticLevel) {
        switch (staticLevel) {
            case STATIC: return "S";
            case NON_STATIC: return "";
        }
        return "";
    }
    protected static String strFinalLvl(FinalLevel finalLevel) {
        switch (finalLevel) {
            case FINAL: return "F";
            case MUTABLE: return "";
        }
        return "";
    }
    protected String variableTypeToString(VariableType type) {
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
    protected ContextToken[] doLoop(int i, ContextToken[] contextToken) {
        int brackets = 1;
        ContextToken[] tokenReturn;
        int length = 0;
        for (int i2 = i; brackets != 0; i2++) {
            if (contextToken[i2].value.equals("{")) brackets++;
            if (contextToken[i2].value.equals("}")) brackets--;
            length++;
        }
        tokenReturn = new ContextToken[length];
        brackets = 1;
        for(int i2 = i; brackets != 0; i2++) {
            if (contextToken[i2].value.equals("{")) brackets++;
            if (contextToken[i2].value.equals("}")) brackets--;
            tokenReturn[i2-i] = contextToken[i2];
        }
        return tokenReturn;
    }

}

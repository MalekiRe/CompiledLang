package third_compiler_attempt.tokens;

import third_compiler_attempt.enums.VariableType;
import third_compiler_attempt.ContextToken;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;

public abstract class BaseToken {

    public abstract String getStringRep();

    protected static String strProtLvl(second_compiler_attempt.enums.ProtectionLevel protectionLevel) {
        switch (protectionLevel) {
            case PUBLIC: return "P";
            case PRIVATE: return "I";
            case PROTECTED: return "O";
        }
        return "";
    }
    protected static String strStaticLvl(second_compiler_attempt.enums.StaticLevel staticLevel) {
        switch (staticLevel) {
            case STATIC: return "S";
            case NON_STATIC: return "";
        }
        return "";
    }
    protected static String strFinalLvl(second_compiler_attempt.enums.FinalLevel finalLevel) {
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
}

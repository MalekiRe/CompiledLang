package second_compiler_attempt.tokens;

import second_compiler_attempt.enums.FinalLevel;
import second_compiler_attempt.enums.ProtectionLevel;
import second_compiler_attempt.enums.StaticLevel;
import second_compiler_attempt.enums.VariableType;

import static second_compiler_attempt.enums.FinalLevel.MUTABLE;
import static second_compiler_attempt.enums.ProtectionLevel.PUBLIC;
import static second_compiler_attempt.enums.StaticLevel.NON_STATIC;

public class VariableToken extends Token{
    public final String name;
    public final VariableType variableType;
    public FinalLevel finalLevel = MUTABLE;
    public ProtectionLevel protectionLevel = PUBLIC;
    public StaticLevel staticLevel = NON_STATIC;
    public VariableToken(String name, VariableType variableType, StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultP) {
        super(defaultS, defaultF, defaultP);
        this.name = name;
        this.variableType = variableType;
    }
    public String getStringCreationRep() {
        return "local " + name + " " + variableTypeToString(this.variableType);
    }
    @Override
    public String getStringRep() {
        return getStringCreationRep();
    }
    public String getClassRep() {
        return "";
    }
}

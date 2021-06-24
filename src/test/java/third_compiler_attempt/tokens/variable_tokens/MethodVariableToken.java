package third_compiler_attempt.tokens.variable_tokens;

import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.enums.VariableType;
import third_compiler_attempt.tokens.InstantiableToken;

public class MethodVariableToken extends VariableToken{

    public final VariableType type;
    public MethodVariableToken(StaticLevel staticLevel, FinalLevel finalLevel, ProtectionLevel protectionLevel, String name, VariableType type) {
        super(StaticLevel.NON_STATIC, FinalLevel.MUTABLE, ProtectionLevel.PUBLIC, staticLevel, finalLevel, protectionLevel, name);
        this.type = type;
        InstantiableToken.instantiableMap.put(name, this);
    }
    @Override
    public String getStringRep() {
        return "local " + name + " " + this.variableTypeToString(type);
    }
}

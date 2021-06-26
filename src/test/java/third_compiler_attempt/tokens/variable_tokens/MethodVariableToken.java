package third_compiler_attempt.tokens.variable_tokens;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.enums.VariableType;
import third_compiler_attempt.tokens.InstantiableToken;

public class MethodVariableToken extends VariableToken{

    public final VariableType type;
    protected MethodVariableToken(AccessLevel defaultAccess, AccessLevel attemptedAccess, String name, VariableType type) {
        super(defaultAccess, attemptedAccess, name);
        this.type = type;
        InstantiableToken.instantiableMap.put(name, this);
    }


    @Override
    public String getStringRep() {
        return "local " + name + " " + this.variableTypeToString(type);
    }
}

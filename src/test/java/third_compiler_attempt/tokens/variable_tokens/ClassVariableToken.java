package third_compiler_attempt.tokens.variable_tokens;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.enums.VariableType;
import third_compiler_attempt.tokens.InstantiableToken;

public class ClassVariableToken extends VariableToken{

    VariableType type;
    protected ClassVariableToken(AccessLevel defaultAccess, AccessLevel attemptedAccess, String name, VariableType type) {
        super(defaultAccess, attemptedAccess, name);
        InstantiableToken.instantiableMap.put(name, this);
        this.type = type;
    }


    @Override
    public String getStringRep() {
        return "field " + name + " "+ strProtLvl(accessLevel.p) + strStaticLvl(accessLevel.s) + strFinalLvl(accessLevel.f) + "|" + variableTypeToString(type);
    }
}

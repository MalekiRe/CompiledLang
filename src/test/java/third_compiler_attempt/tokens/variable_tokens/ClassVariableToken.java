package third_compiler_attempt.tokens.variable_tokens;

import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.tokens.InstantiableToken;

public class ClassVariableToken extends VariableToken{


    protected ClassVariableToken(StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultProtection, String name, StaticLevel staticLevel, FinalLevel finalLevel, ProtectionLevel protectionLevel) {
        super(defaultS, defaultF, defaultProtection, staticLevel, finalLevel, protectionLevel, name);
        InstantiableToken.instantiableMap.put(name, this);
    }

    @Override
    public String getStringRep() {
        return null;
    }
}

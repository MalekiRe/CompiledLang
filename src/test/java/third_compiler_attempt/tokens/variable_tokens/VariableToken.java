package third_compiler_attempt.tokens.variable_tokens;

import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.tokens.InstantiableToken;

public abstract class VariableToken extends InstantiableToken {

    protected VariableToken(StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultProtection, StaticLevel staticLevel, FinalLevel finalLevel, ProtectionLevel protectionLevel, String name) {
        super(defaultS, defaultF, defaultProtection, name, staticLevel, finalLevel, protectionLevel);
    }
}

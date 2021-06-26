package third_compiler_attempt.tokens.variable_tokens;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.tokens.InstantiableToken;

public abstract class VariableToken extends InstantiableToken {


    protected VariableToken(AccessLevel defaultAccess, AccessLevel attemptedAccess, String name) {
        super(defaultAccess, attemptedAccess, name);
    }
}

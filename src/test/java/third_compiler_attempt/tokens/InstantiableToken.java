package third_compiler_attempt.tokens;

import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;

public abstract class InstantiableToken extends BaseToken{

    public InstantiableToken(StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultProtection) {
        super(defaultS, defaultF, defaultProtection);
    }
    public abstract String getCreateThing();
    public abstract String getExistingThing();
}

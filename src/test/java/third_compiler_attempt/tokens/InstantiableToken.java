package third_compiler_attempt.tokens;

import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class InstantiableToken extends BaseToken{
    public static final Map<String, InstantiableToken> instantiableMap = new HashMap<>();
    public final StaticLevel defaultS;
    public final FinalLevel defaultF;
    public final ProtectionLevel defaultProtection;
    public final String name;
    public final StaticLevel staticLevel;
    public final FinalLevel finalLevel;
    public final ProtectionLevel protectionLevel;
    protected InstantiableToken(StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultProtection, String name, StaticLevel staticLevel, FinalLevel finalLevel, ProtectionLevel protectionLevel) {
        this.defaultS = defaultS;
        this.defaultF = defaultF;
        this.defaultProtection = defaultProtection;
        this.name = name;
        this.staticLevel = staticLevel;
        this.finalLevel = finalLevel;
        this.protectionLevel = protectionLevel;
    }


}

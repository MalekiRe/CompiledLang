package third_compiler_attempt.tokens;

import third_compiler_attempt.AccessLevel;

import java.util.HashMap;
import java.util.Map;

public abstract class InstantiableToken extends BaseToken{
    public static final Map<String, InstantiableToken> instantiableMap = new HashMap<>();
    public AccessLevel defaultAccess;
    public AccessLevel accessLevel;
    public final String name;
    protected InstantiableToken(AccessLevel defaultAccess, AccessLevel accessLevel, String name) {
        this.name = name;
        this.defaultAccess = defaultAccess;
        this.accessLevel = accessLevel;

    }


}

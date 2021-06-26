package third_compiler_attempt;



import third_compiler_attempt.enums.FinalLevel;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;

public class AccessLevel {
    public ProtectionLevel p;
    public StaticLevel s;
    public FinalLevel f;
    public AccessLevel() {}
    public AccessLevel(ProtectionLevel p, StaticLevel s, FinalLevel f) {
        this.p = p;
        this.s = s;
        this.f = f;
    }
    public AccessLevel(AccessLevel accessLevel) {
        this.p = accessLevel.p;
        this.s = accessLevel.s;
        this.f = accessLevel.f;
    }
    public boolean everythingSet() {
        return p != null && s != null && f != null;
    }
}

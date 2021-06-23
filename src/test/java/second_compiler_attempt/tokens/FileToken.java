package second_compiler_attempt.tokens;

import second_compiler_attempt.enums.FinalLevel;
import second_compiler_attempt.enums.ProtectionLevel;
import second_compiler_attempt.enums.StaticLevel;

public class FileToken extends Token{
    public FileToken(StaticLevel defaultStaticLevel, FinalLevel defaultFinalLevel, ProtectionLevel defaultProtectionLevel) {
        super(defaultStaticLevel, defaultFinalLevel, defaultProtectionLevel);
    }
}

package third_compiler_attempt;

import third_compiler_attempt.enums.HighLevelType;
import third_compiler_attempt.enums.BracketType;

import java.util.ArrayList;

public class ContextToken {
    public final HighLevelType type;
    public final String value;
    ContextToken(HighLevelType type, String value) {
        this.type = type;
        this.value = value;
    }


}

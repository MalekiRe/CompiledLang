package second_compiler_attempt;

import second_compiler_attempt.enums.HighLevelType;

import java.util.ArrayList;

public class ContextToken {
    public final HighLevelType type;
    public final String value;
    public ArrayList<ContextToken> children = new ArrayList<ContextToken>();
    ContextToken(HighLevelType type, String value) {
        this.type = type;
        this.value = value;
    }
    @Override
    public String toString() {
        return "type : " +type + ", value : " + value;
    }

}

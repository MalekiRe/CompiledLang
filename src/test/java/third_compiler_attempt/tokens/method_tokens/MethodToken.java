package third_compiler_attempt.tokens.method_tokens;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.enums.VariableType;
import third_compiler_attempt.tokens.InstantiableToken;

import java.util.ArrayList;

public class MethodToken extends InstantiableToken {
    ArrayList<InstantiableToken> childTokens = new ArrayList<>();
    VariableType type;

    protected MethodToken(AccessLevel defaultAccess, AccessLevel attemptedAccess, String name, VariableType type) {
        super(defaultAccess, attemptedAccess, name);
        this.type = type;
    }
    public String getHeader() {
        return "method " + name + " "+ strProtLvl(accessLevel.p) + strStaticLvl(accessLevel.s) + strFinalLvl(accessLevel.f) + "()" + variableTypeToString(type);
    }
    public void addChild(InstantiableToken token) {
        childTokens.add(token);
    }
    public String getFooter() {
        if(type == VariableType.VOID) {
            return "returnV \nend";
        }
        return "return \nend";
    }
    public String getChildrenString() {
        StringBuilder s = new StringBuilder();
        for(InstantiableToken token : childTokens) {
            s.append("\n").append(token.getStringRep());
        }
        return s.toString();
    }
    @Override
    public String getStringRep() {
        return getHeader() + getChildrenString() + "\n" + getFooter();
    }
}

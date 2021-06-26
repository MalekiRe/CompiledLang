package third_compiler_attempt.tokens.class_tokens;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.ContextToken;
import third_compiler_attempt.ast.ASTToken;
import third_compiler_attempt.tokens.InstantiableToken;

import java.util.ArrayList;

public class ClassToken extends InstantiableToken {
    ArrayList<InstantiableToken> childrenTokens = new ArrayList<>();
    ContextToken[] contextTokens;
    ASTToken astToken;
    public ClassToken(ContextToken[] contextTokens, ASTToken astToken, AccessLevel defaultAccess, AccessLevel accessLevel, String name) {
        super(defaultAccess, accessLevel, name);
        this.contextTokens = contextTokens;
        this.astToken = astToken;
        for(ASTToken astToken1 : astToken.children) {
            createChild(contextTokens, astToken1);
        }
    }
    public String getChildrenString() {
        StringBuilder s = new StringBuilder();
        for(InstantiableToken token : childrenTokens) {
            s.append("\n").append(token.getStringRep());
        }
        return s.toString();
    }
    public String getHeader() {
        return "class " + this.name + " "+ strProtLvl(accessLevel.p) + strStaticLvl(accessLevel.s) + strFinalLvl(accessLevel.f);
    }
    public String getFooter() {
        return "";
    }
    @Override
    public String getStringRep() {
        return getHeader() + getChildrenString() + "\n" + getFooter();
    }
    public void addChild(InstantiableToken token) {
        childrenTokens.add(token);
    }
    public void createChild(ContextToken[] contextTokens, ASTToken astToken) {
        switch (astToken.type) {
            case CREATE_CLASS:
                addChild(createClassToken(contextTokens, astToken, this.accessLevel));
            break;
            default:
                //System.out.println("default");

        }
    }
    public static ClassToken createClassToken(ContextToken[] contextTokens, ASTToken astToken, AccessLevel defaultAccessLevel) {
        System.out.println(determineAccessLevel(contextTokens, defaultAccessLevel, astToken).p);
       return new ClassToken(contextTokens, astToken, defaultAccessLevel, determineAccessLevel(contextTokens, defaultAccessLevel, astToken), getName(contextTokens, astToken));
    }
}

package second_compiler_attempt.tokens;

import second_compiler_attempt.ContextToken;
import second_compiler_attempt.enums.FinalLevel;
import second_compiler_attempt.enums.ProtectionLevel;
import second_compiler_attempt.enums.VariableType;
import second_compiler_attempt.enums.StaticLevel;

import java.util.ArrayList;

import static second_compiler_attempt.enums.FinalLevel.MUTABLE;
import static second_compiler_attempt.enums.ProtectionLevel.PUBLIC;
import static second_compiler_attempt.enums.StaticLevel.NON_STATIC;
import static second_compiler_attempt.enums.VariableType.CLASS;
import static second_compiler_attempt.enums.VariableType.VOID;

public class MethodToken extends Token {
    public final VariableType returnType;
    public final String name;
    public FinalLevel finalLevel = MUTABLE;
    public ProtectionLevel protectionLevel = PUBLIC;
    public StaticLevel staticLevel = NON_STATIC;
    public ArrayList<VariableToken> arguments = new ArrayList<>();
    ContextToken childContextTokens[];
    Token[] childTokens;
    int index;
    public MethodToken(VariableType returnType, String name, StaticLevel defaultS, FinalLevel defaultF, ProtectionLevel defaultP, ContextToken[] tokens, int index) {
        super(defaultS, defaultF, defaultP);
        this.returnType = returnType;
        this.name = name;
        childContextTokens = doLoop(index, tokens);
        this.index = index;
        setEverything();
    }
    private void setEverything() {

    }
    private String getChildrenString() {
        StringBuilder returnString = new StringBuilder();
        for(Token token : childTokens) {
            returnString.append(token.getStringRep());
        }
        return returnString.toString();
    }
    private String getFooter() {
        if(returnType == VOID) {
            return "return" + variableTypeToString(returnType) +"\n";
        }
        return "return\n";
    }
    @Override
    public String toString() {
        return getStringRep();
    }

    @Override
    public String getStringRep() {
        return getHeader() + "\n" + getChildrenString()+ "\n" + getFooter();
    }
    private String getHeader() {
        return "method " + name + " "+strProtLvl(protectionLevel)+strStaticLvl(staticLevel)+strFinalLvl(finalLevel)+
                "("+getArgumentsString()+")"
                +variableTypeToString(returnType);
    }
    private String getArgumentsString() {
        String rep = "";
        for(VariableToken variableToken : arguments) {
            rep += getStringRepresentationOfVariable(variableToken);
        }
        return rep;
    }
    private String getStringRepresentationOfVariable(VariableToken variableToken) {
        if(variableToken.variableType == CLASS) {
            return variableToken.getClassRep();
        }
        return variableTypeToString(variableToken.variableType);
    }

}

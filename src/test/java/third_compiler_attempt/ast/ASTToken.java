package third_compiler_attempt.ast;

import third_compiler_attempt.Compiler;
import third_compiler_attempt.PunctuationType;
import third_compiler_attempt.Tokenizer;
import third_compiler_attempt.enums.HighLevelType;
import third_compiler_attempt.ContextToken;
import third_compiler_attempt.enums.IDType;

import java.util.ArrayList;

public class ASTToken {
    ContextToken[] contextTokens;
    public ArrayList<ASTToken> children = new ArrayList<>();
    public final int positionInContext;
    IDType type;
    public ASTToken(IDType idType, ContextToken[] contextTokens, int positionInContext) {
        this.positionInContext = positionInContext;
        this.contextTokens = contextTokens;
        this.type = idType;
        switch (type) {
            case CREATE_CLASS:
                int pos = positionInContext;
                while(!contextTokens[pos].value.equals("{")) {
                    pos++;
                }
                callWhenCreateClass(pos+1);
                break;
            case CREATE_METHOD:
                pos = positionInContext;
                while(!contextTokens[pos].value.equals("{")) {
                    pos++;
                }
                callWhenCreateMethod(pos+1);
                break;
            case INSTANTIATE_VARIABLE:
                pos = positionInContext+1;
                callWhenInstantiateVariable(pos);
                break;
            case CALL_METHOD:
                pos = positionInContext+1;
                callWhenCallMethod(pos);
                break;
            case CALL_VARIABLE:
                callWhenCallVariable(positionInContext+1);
                break;
            case OPERATION:
                callWhenOperation(positionInContext+1);
                break;
            case NEW_CLASS:
                pos = positionInContext+1;
                callWhenNewClass(pos);
            case STATIC_CLASS:
                callWhenStaticClass(positionInContext+1);
            default:

        }


    }
    public void callWhenCallVariable(int pos) {
        for(int i = pos; !contextTokens[i].value.equals(";"); i++) {
            if(Tokenizer.matchesArray(PunctuationType.operators, contextTokens[i].value)) {
                children.add(new ASTToken(IDType.OPERATION, contextTokens, i));
            }
        }
    }
    public void callWhenStaticClass(int pos) {
        for(int i = pos; !contextTokens[i].value.equals(";"); i++) {
            if(contextTokens[i].value.equals(".")) {
                i++;
                switch (Compiler.getIDType(contextTokens, i)) {
                    case CALL_METHOD:
                        children.add(new ASTToken(IDType.CALL_METHOD, contextTokens, i));
                        while(!contextTokens[i].value.equals(";")) {
                            i++;
                        }
                        break;
                    case CALL_VARIABLE:
                        children.add(new ASTToken(IDType.CALL_VARIABLE, contextTokens, i));
                        while(!contextTokens[i].value.equals(";")) {
                            i++;
                        }
                        break;
                }
            }
        }
    }
    public void callWhenOperation(int pos) {
        for(int i = pos; !contextTokens[i].value.equals(";"); i++) {
            if (contextTokens[i].type == HighLevelType.ID) {
                children.add(new ASTToken(IDType.CALL_VARIABLE, contextTokens, i));
            }
        }
    }
    public void callWhenNewClass(int pos) {
        for(int i = pos; !contextTokens[i].value.equals(";"); i++) {
            if (contextTokens[i].type == HighLevelType.ID) {
                children.add(new ASTToken(IDType.CALL_VARIABLE, contextTokens, i));
            }
        }
    }
    public void callWhenCreateMethod(int pos) {
        defaultCallWhenCreate(pos);
    }
    public void callWhenInstantiateVariable(int pos) {
        for(int i = pos; !contextTokens[i].value.equals(";"); i++) {
            if(Tokenizer.matchesArray(PunctuationType.operators, contextTokens[i].value)) {
                children.add(new ASTToken(IDType.OPERATION, contextTokens, i));
            }
        }
    }
    public void callWhenCreateClass(int pos) {
        defaultCallWhenCreate(pos);
    }
    public void callWhenCallMethod(int pos) {
        for(int i = pos; !contextTokens[i].value.equals(";"); i++) {
            if (contextTokens[i].type == HighLevelType.ID) {
                children.add(new ASTToken(IDType.CALL_VARIABLE, contextTokens, i));
            }
        }
    }
    private void defaultCallWhenCreate(int pos) {
        int brackets = 1;
        for(int i = pos; i < contextTokens.length && brackets != 0; i++) {
            if (contextTokens[i].value.equals("{")) brackets++;
            if (contextTokens[i].value.equals("}")) brackets--;
            if (contextTokens[i].type == HighLevelType.OPERATOR) {
                System.out.println("operator");
                children.add(new ASTToken(IDType.OPERATION, contextTokens, i));
                for(int i2 = i; !contextTokens[i2].value.equals(";"); i2++) {
                    i = i2;
                }
            }
            if (contextTokens[i].type == HighLevelType.ID) {
                IDType childType = Compiler.getIDType(contextTokens, i);
                switch (childType) {
                    case CREATE_CLASS:
                        children.add(new ASTToken(IDType.CREATE_CLASS, contextTokens, i));
                        int pos2 = i;
                        while (!contextTokens[pos2].value.equals("{")) {
                            pos2++;
                        }
                        pos2++;
                        int brackets2 = 1;
                        for (int i2 = pos2; brackets2 != 0; i2++) {
                            if (contextTokens[i2].value.equals("{")) brackets2++;
                            if (contextTokens[i2].value.equals("}")) brackets2--;
                            i = i2;
                        }
                        break;
                    case CREATE_METHOD:
                        children.add(new ASTToken(IDType.CREATE_METHOD, contextTokens, i));
                        pos2 = i;
                        while (!contextTokens[pos2].value.equals("{")) {
                            pos2++;
                        }
                        pos2++;
                        brackets2 = 1;
                        for (int i2 = pos2; brackets2 != 0; i2++) {
                            if (contextTokens[i2].value.equals("{")) brackets2++;
                            if (contextTokens[i2].value.equals("}")) brackets2--;
                            i = i2;
                        }
                        break;
                    case INSTANTIATE_VARIABLE:
                        children.add(new ASTToken(IDType.INSTANTIATE_VARIABLE, contextTokens, i));
                        pos2 = i;
                        while(!contextTokens[pos2].value.equals(";")) {
                            pos2++;
                        }
                        i = pos2;
                        break;
                    case CALL_METHOD:
                        children.add(new ASTToken(IDType.CALL_METHOD, contextTokens, i));
                        pos2 = i;
                        while(!contextTokens[pos2].value.equals(";")) {
                            pos2++;
                        }
                        i = pos2;
                        break;
                    case CALL_VARIABLE:
                        children.add(new ASTToken(IDType.CALL_VARIABLE, contextTokens, i));
                        pos2 = i;
                        while(!contextTokens[pos2].value.equals(";")) {
                            pos2++;
                        }
                        i = pos2;
                        break;
                    case NEW_CLASS:
                        children.add(new ASTToken(IDType.NEW_CLASS, contextTokens, i));
                        pos2 = i;
                        while(!contextTokens[pos2].value.equals(";")) {
                            pos2++;
                        }
                        i = pos2;
                        break;
                    case STATIC_CLASS:
                        children.add(new ASTToken(IDType.STATIC_CLASS, contextTokens, i));
                        pos2 = i;
                        while(!contextTokens[pos2].value.equals(";")) {
                            pos2++;
                        }
                        i = pos2;
                        break;
                }
            }
        }
    }
    public String getString(int depth) {
        StringBuilder s = new StringBuilder();
        s.append("token : " + contextTokens[positionInContext].value + " type " + this.type + " at " + positionInContext + " children are");
        for(ASTToken token : children) {
            if(token != null) {
                s.append("\n");
                for(int i = 0; i < depth; i++) {
                    s.append("\t");
                }
                s.append(token.getString(depth+1));
            }
        }
        return s.toString();
    }
//                    int i2 = i;
//                    while (!contextTokens[i2].value.equals("{")) {
//                        i2++;
//                    }
//                    i2++;
//                    int brackets = 1;
//                    numberOfChildren++;
//                    for (int i3 = i2; brackets != 0; i3++) {
//                        if (contextTokens[i3].value.equals("{")) brackets++;
//                        if (contextTokens[i3].value.equals("}")) brackets--;
//                        i = i3;
//                    }
//
//                }
}

package third_compiler_attempt.ast;

import third_compiler_attempt.Compiler;
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
        }


    }
    public void callWhenCreateMethod(int pos) {
        int brackets = 1;
        for(int i = pos; i < contextTokens.length && brackets != 0; i++) {
            if (contextTokens[i].value.equals("{")) brackets++;
            if (contextTokens[i].value.equals("}")) brackets--;
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
                    case CALL_METHOD:
                    case CALL_VARIABLE:
                    case NEW_CLASS:
                    case STATIC_CLASS:
                }
            }
        }
    }
    public void callWhenCreateClass(int pos) {
        int brackets = 1;
        for(int i = pos; i < contextTokens.length && brackets != 0; i++) {
            if (contextTokens[i].value.equals("{")) brackets++;
            if (contextTokens[i].value.equals("}")) brackets--;
            if(contextTokens[i].type == HighLevelType.ID) {
                IDType childType = Compiler.getIDType(contextTokens, i);
                switch (childType) {
                    case CREATE_CLASS:
                        children.add(new ASTToken(IDType.CREATE_CLASS, contextTokens, i));
                        int pos2 = i;
                        while(!contextTokens[pos2].value.equals("{")) {
                            pos2++;
                        }
                        pos2++;
                        int brackets2 = 1;
                        for(int i2 = pos2; brackets2 != 0; i2++) {
                            if (contextTokens[i2].value.equals("{")) brackets2++;
                            if (contextTokens[i2].value.equals("}")) brackets2--;
                            i = i2;
                        }
                        break;
                    case CREATE_METHOD:
                        children.add(new ASTToken(IDType.CREATE_METHOD, contextTokens, i));
                        pos2 = i;
                        while(!contextTokens[pos2].value.equals("{")) {
                            pos2++;
                        }
                        pos2++;
                        brackets2 = 1;
                        for(int i2 = pos2; brackets2 != 0; i2++) {
                            if (contextTokens[i2].value.equals("{")) brackets2++;
                            if (contextTokens[i2].value.equals("}")) brackets2--;
                            i = i2;
                        }
                        break;
                    case INSTANTIATE_VARIABLE:
                    case CALL_METHOD:
                    case CALL_VARIABLE:
                    case NEW_CLASS:
                    case STATIC_CLASS:
                }
            }
        }
        if(brackets == 0) {
            System.out.println("ended cause brackets were balanced at " + contextTokens[positionInContext].value);
        }
    }
    public String getString(int depth) {
        StringBuilder s = new StringBuilder();
        s.append("token : " + contextTokens[positionInContext].value + " at " + positionInContext + " children are");
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

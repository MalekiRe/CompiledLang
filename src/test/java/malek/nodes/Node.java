package malek.nodes;

import malek.AbstractToken;
import malek.Type;

import java.util.ArrayList;

public class Node {
    Type type;
    public String name;
    public String levelAccessModifier;
    public String staticAccessModifier;
    public String finalAccessModifier;

    public static String getStringFromType(Type type) {
        switch (type) {
            case INT: return "I";
            case CHAR: return "C";
            case VOID: return "V";
            case FLOAT: return "F";
            case DOUBLE: return "D";
            case BOOLEAN: return "B";
            default:
                return "";
        }
    }
}

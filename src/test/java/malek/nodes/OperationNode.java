package malek.nodes;

import malek.Type;

import java.util.ArrayList;

public class OperationNode extends Node {

    public final String name;
    public final String levelAccessModifier;
    public final String staticAccessModifier;
    public final String finalAccessModifier;
    public final Type type;
    public final String returnTypeString;
    public final String value;
    ArrayList<Node> children = new ArrayList<>();
    public OperationNode(String name, String levelAccessModifier, String staticAccessModifier, String finalAccessModifier, Type type, String value) {
        this.name = name;
        this.levelAccessModifier = levelAccessModifier; //public, private, protected
        this.staticAccessModifier = staticAccessModifier; //static, or nothing.
        this.finalAccessModifier = finalAccessModifier; //Final or nothing.
        this.type = type;
        this.value = value;
        switch (type) {
            case INT:
                returnTypeString = "I";
                break;
            case VOID:
                returnTypeString = "V";
                break;
            default:
                returnTypeString = "ERROR";
        }
    }
    public void addChild(Node node) {
        this.children.add(node);
    }
    public ArrayList<Node> getChildren() {
        return children;
    }
    public String getChildrenStrings() {
        String string = "";
        for(int i = 0; i < children.size(); i++) {
            string += children.get(i).toString();
        }
        return string;
    }
    @Override
    public String toString() {
        if(staticAccessModifier.equals("static")) {
            return "push\nlocalc " + value + getChildrenStrings() + "\nsetl " + name + "\npop";
        }
        else {
            return null;
        }
    }

}

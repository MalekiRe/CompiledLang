package malek.nodes;

import malek.Type;

import java.util.ArrayList;

public class MethodNode extends Node{

    ArrayList<Node> children = new ArrayList<>();
    ArrayList<Node> arguments = new ArrayList<>();
    public final Type type;
    public final String returnTypeString;
    public MethodNode(String name, String levelAccessModifier, String staticAccessModifier, String finalAccessModifier, Type type) {
        this.name = name;
        this.levelAccessModifier = levelAccessModifier; //public, private, protected
        this.staticAccessModifier = staticAccessModifier; //static, or nothing.
        this.finalAccessModifier = finalAccessModifier; //Final or nothing.
        this.type = type;
        switch (type) {
            case INT : returnTypeString = "I"; break;
            case VOID: returnTypeString = "V"; break;
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
    public void addArgument(Node node) {
        this.arguments.add(node);
    }
    public ArrayList<Node> getArguments() {
        return arguments;
    }
    public String getChildrenStrings() {
        String string = "";
        for(int i = 0; i < children.size(); i++) {
            string += children.get(i).toString();
        }
        return string;
    }
    public String getArgumentsString() {
        String string = "";
        for(int i = 0; i < arguments.size(); i++) {
            string += arguments.get(i).toString();
        }
        return string;
    }
    @Override
    public String toString() {
        return "method " + name +" "
                + levelAccessModifier + staticAccessModifier + finalAccessModifier
                + "(" + getArgumentsString() + ")" + returnTypeString + getChildrenStrings() + "\nreturn"+returnTypeString;
    }
}

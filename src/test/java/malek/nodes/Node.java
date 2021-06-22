package malek.nodes;

import malek.AbstractToken;
import malek.Type;

import java.util.ArrayList;

public class Node {
    ArrayList<Node> children = new ArrayList<>();
    ArrayList<Node> arguments = new ArrayList<>();
    final AbstractToken token;
    public final boolean isStatic;
    public final boolean isFinal;
    public final Type type;
    public Node(AbstractToken token, boolean isStatic, boolean isFinal, Type type) {
        this.token = token;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.type = type;
    }
    public void setText(String text) {
        this.token.setText(text);
    }
    public String getText() {
        return this.token.getText();
    }
    public String getID() {
        return this.token.getID();
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
    @Override
    public String toString() {
        return getID() + ", " + getText() + ", " + getArguments() + ", " + getChildren() + ", is static : " + isStatic + ", is final : " + isFinal + ", type : " + type;
    }
}

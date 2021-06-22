package malek.nodes;

import malek.Type;

public class LiteralNode extends Node{
    public LiteralNode(String value, Type type) {
        this.name = value;
        this.type = type;
    }
}

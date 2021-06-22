package malek.nodes;

import malek.AbstractToken;
import malek.Type;

public class ClassNode extends Node{
    public ClassNode(AbstractToken token, boolean isStatic, boolean isFinal, Type type) {
        super(token, isStatic, isFinal, type);
    }
}

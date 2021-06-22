package malek.nodes;

import malek.Type;

public class InstanceNode extends Node{
    public final String returnTypeString;
    public InstanceNode(String name, String levelAccessModifier, String staticAccessModifier, String finalAccessModifier, Type type) {
        this.name = name;
        this.levelAccessModifier = levelAccessModifier; //public, private, protected
        this.staticAccessModifier = staticAccessModifier; //static, or nothing.
        this.finalAccessModifier = finalAccessModifier; //Final or nothing.
        this.type = type;
        this.returnTypeString = getStringFromType(type);
    }
    @Override
    public String toString() {
        return "local " + name + " " + returnTypeString;
    }
}

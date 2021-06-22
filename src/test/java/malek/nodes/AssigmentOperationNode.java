package malek.nodes;

public class AssigmentOperationNode extends Node{
    final Node thingToAssign;
    final Node thingToAssignFrom;

    public AssigmentOperationNode(Node thingToAssign, Node thingToAssignFrom) {
        this.thingToAssign = thingToAssign;
        this.thingToAssignFrom = thingToAssignFrom;
    }

    @Override
    public String toString() {
        if(thingToAssign.type != thingToAssignFrom.type) {
            System.out.println("tried to assign type that wasn't valid");
            System.out.println(thingToAssign.name);
            System.out.println(thingToAssignFrom.name);
        }
        return "push\nloadc "+thingToAssignFrom.name+"\nsetl " + thingToAssign.name+"\npop\n";
    }

}

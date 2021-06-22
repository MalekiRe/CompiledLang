package malek;

import compiling.Cleaner;
import malek.nodes.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static malek.Type.*;

public class Compiler {
    public static void main(String[] args) throws IOException {
        String str;
        {
            FileInputStream src = new FileInputStream("test/src/MalekFieldTest.langsrc");
            byte[] bytes = new byte[src.available()];
            src.read(bytes);
            src.close();
            str = new String(bytes);
        }
        System.out.println(str);
        str = str.replace("\r", "");
        StringBuilder out = new StringBuilder();
        ArrayList<String> access = new ArrayList<>();
        // TODO: figure this out
        AbstractToken[] tokens = Tokenizer.tokenize(Cleaner.stripComments(str));
        //We are gonna go through and check if any of these match any of these keywords, and then if it doesn't then we are gonna assume its a variable name.
        for(AbstractToken token : tokens) {
            if(token == null)
                System.out.println("token is null");
            System.out.println(token.getID() + " : " + token.getText());
        }

        String staticString = "";
        String finalString = "";
        String accessLevel = "P";
        boolean isInClass = false;
        int numberOfCurly = 0;
        boolean isMethod = true;
       Map<String, Node> nodeSearch = new HashMap<>();
       ArrayList<Node> operators = new ArrayList<>();
       for(int i = 0; i < tokens.length; i++) {
           Type returnType = null;
            if(tokens[i].getID().equals("access")) {
             if(tokens[i].getText().equals("static")) {
                    staticString = "S";
                 continue;
             }
                if(tokens[i].getText().equals("final")) {
                    finalString = "F";
                    continue;
                }
            }
           switch (tokens[i].getText()) {
               case "void" : returnType = VOID; continue;
               case "int" : returnType = INT;  i++;
               nodeSearch.put(tokens[i].getText(), new InstanceNode(tokens[i].getText(), accessLevel, staticString, finalString, returnType));
               operators.add(nodeSearch.get(tokens[i].getText()));
               i++;
               continue;
               case "char" : returnType = CHAR; continue;
           }
            if(tokens[i].getText().equals("=")) {
                if(Character.isDigit(tokens[i+1].getText().toCharArray()[0])) {
                    if (nodeSearch.get(tokens[i - 1].getText()) == null) {
                        System.out.println("null at : " + tokens[i - 1].getText());
                    }
                    operators.add(new AssigmentOperationNode(nodeSearch.get(tokens[i - 1].getText()), new LiteralNode(tokens[i + 1].getText(), INT)));
                }
                else {
                    if (nodeSearch.get(tokens[i - 1].getText()) == null) {
                        System.out.println("null at : " + tokens[i - 1].getText());
                    }
                    operators.add(new AssigmentOperationNode(nodeSearch.get(tokens[i - 1].getText()), (nodeSearch.get(tokens[i + 1].getText()))));
                }
            }

        }

        System.out.println(nodeSearch.size());

        for (Node node : operators) {
            System.out.println(node.toString());
        }

        InstanceNode myInt = new InstanceNode("myInt", "P", "S", "", INT);
        LiteralNode one = new LiteralNode("1", INT);
        AssigmentOperationNode setMyIntToOne = new AssigmentOperationNode(myInt, one);
        System.out.println(setMyIntToOne.toString());
        InstanceNode y = new InstanceNode("y", "P", "S", "", INT);
        System.out.println(new AssigmentOperationNode(y, myInt));

//        MethodNode tempMethodNode = new MethodNode("main", "P", "S", "", Type.VOID);
//        System.out.println(tempMethodNode.toString());

//		System.out.println(Arrays.toString(tokenize(str)));
    }



}

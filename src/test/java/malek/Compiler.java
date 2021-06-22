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


        String defaultStaticString = "";
        String defaultFinalString = "";
        String defaultAccessLevel = "P";
        boolean isInClass = false;
        int numberOfCurly = 0;
        boolean isMethod = true;
       Map<String, Node> nodeSearch = new HashMap<>();
       ArrayList<Node> operators = new ArrayList<>();
       for(int i = 0; i < tokens.length; i++) {
           String staticString = defaultStaticString;
           String finalString = defaultFinalString;
           String accessLevel = defaultAccessLevel;
           Type returnType = null;
           if (tokens[i].getID().equals("access")) {
               if (tokens[i].getText().equals("static")) {
                   staticString = "S";
                   continue;
               }
               if (tokens[i].getText().equals("final")) {
                   finalString = "F";
                   continue;
               }
           }
           /*
           if(tokens[i].getID().equals("access")) {
               TypeThing typeThing = getModiferType(tokens, i);
               switch (typeThing) {
                   case ACCESS_LEVEL_KEYWORD: accessLevel = getStringFromAccessLevel(tokens[i]);
                   case FINAL_KEYWORD : finalString = "F";
                   case STATIC_KEYWORD: staticString = "S";
               }
           }
            */
           if(tokens[i].getID().equals("id")) {
               TypeThing typeThing = getType(tokens, i);
               switch (typeThing) {
                   case METHOD:
                       Node temp = createMethodNode(tokens, i);
                       nodeSearch.put(temp.name, temp);
               }

           }

       }
            /*
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

             */

        System.out.println(nodeSearch.size());
        for(Node node : nodeSearch.values()) {
            System.out.println(node.toString());
        }
        for (Node node : operators) {
            System.out.println(node.toString());
        }
        /*
        InstanceNode myInt = new InstanceNode("myInt", "P", "S", "", INT);
        LiteralNode one = new LiteralNode("1", INT);
        AssigmentOperationNode setMyIntToOne = new AssigmentOperationNode(myInt, one);
        System.out.println(setMyIntToOne.toString());
        InstanceNode y = new InstanceNode("y", "P", "S", "", INT);
        System.out.println(new AssigmentOperationNode(y, myInt));

         */

//        MethodNode tempMethodNode = new MethodNode("main", "P", "S", "", Type.VOID);
//        System.out.println(tempMethodNode.toString());

//		System.out.println(Arrays.toString(tokenize(str)));
    }
    private static Node createMethodNode(AbstractToken[] tokens, int startingIndex) {
        String nodeName = tokens[startingIndex].getText();
        Type nodeReturnType = getReturnType(tokens, startingIndex);
        String finalString = "";
        String staticString = "";
        String accessString = "P";
        int i = startingIndex;
        while(i > 0 && tokens[i].getID().equals("access")) {
            switch (getModiferType(tokens, i)) {
                case FINAL_KEYWORD:
                finalString = "F"; break;
                case STATIC_KEYWORD: staticString = "S"; break;
                case ACCESS_LEVEL_KEYWORD: accessString = getStringFromAccessLevel(tokens[i]);
            }
            i--;
        }
        return new MethodNode(nodeName, accessString, staticString, finalString, nodeReturnType);
    }
    private static String getStringFromAccessLevel(AbstractToken token) {
        switch (token.getText()) {
            case "public" : return "P";
            case "private" : return "I";
            case "protected" : return "O";
            default: System.out.println("ERROR ACCESS LEVEL");
            return null;
        }
    }
    private static Type getReturnType(AbstractToken[] tokens, int startingIndex) {
        if(startingIndex == 0) {
            System.out.println("ERROR : MISSING IDENTIFER KEYWORD");
            return null;
        }
        switch (tokens[startingIndex-1].getText()) {
            case "int" : return INT;
            case "char" : return CHAR;
            case "boolean" : return BOOLEAN;
            case "float" : return FLOAT;
            case "double" : return DOUBLE;
            case "void" : return VOID;
            case "class" : return CLASS;
            default:
                System.out.println("ERROR : MISSING IDENTIFIER KEYWORD");
                return null;
        }

    }
    private static TypeThing getModiferType(AbstractToken[] tokens, int startingIndex) {
       if(tokens[startingIndex].getText().equals("static")) {
            return TypeThing.STATIC_KEYWORD;
        }
        else if(tokens[startingIndex].getText().equals("final")) {
            return TypeThing.FINAL_KEYWORD;
        }
        else if(tokens[startingIndex].getID().equals("access")) {
            return TypeThing.ACCESS_LEVEL_KEYWORD;
        }
        else {
            System.out.println("ERROR : NOT AN MODIFER");
            return null;
       }
    }
    private static TypeThing getType(AbstractToken[] tokens, int startingIndex) {
        if(!tokens[startingIndex].getID().equals("id")) {
            System.out.println("ERROR : TRIED TO GET THE TYPE OF A TOKEN THAT IS NOT AN ID TOKEN");
            return null;
        }
        if(startingIndex == 0) {
            System.out.println("ERROR : MISSING IDENTIFER KEYWORD");
            return null;
        }
        if(tokens[startingIndex-1].getText().equals("class")) {
            return TypeThing.CLASS;
        }
        else if(tokens[startingIndex+1].getText().equals("(")) {
            return TypeThing.METHOD;
        }
        else if(tokens[startingIndex].getID().equals("operator")) {
            return TypeThing.OPERATOR;
        }
        else {
            return TypeThing.VARIABLE;
        }
    }


}

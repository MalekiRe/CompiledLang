package malek;

import compiling.Cleaner;
import malek.nodes.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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

        boolean isStatic = false;
        boolean isFinal = false;
        boolean isInClass = false;
        int numberOfCurly = 0;
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < tokens.length; i++) {
            Type returnType = null;
            if(tokens[i].getID().equals("access")) {
                if(tokens[i].getText().equals("static")) {
                    isStatic = true;
                    i++;
                }
                if(tokens[i].getText().equals("final")) {
                    isFinal = true;
                    i++;
                }
                switch (tokens[i].getText()) {
                    case "void" : returnType = VOID; i++; break;
                    case "int" : returnType = INT; i++; break;
                    default: i++;
                }
            }
            if(tokens[i].getID().equals("id")) {
                nodes.add(new Node(tokens[i], isStatic, isFinal, returnType));
                i++;
            }
        }

        System.out.println(nodes.size());

        for (Node node : nodes) {
            System.out.println(node.toString());
        }



//		System.out.println(Arrays.toString(tokenize(str)));
    }

    private static String compileNode(Node node) {
        if(node.isStatic) {

        }
    }

}

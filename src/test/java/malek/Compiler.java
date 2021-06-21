package malek;

import compiling.Cleaner;
import malek.tokens.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
                continue;
            System.out.println(token.getID() + " : " + token.getText());
        }

//		System.out.println(Arrays.toString(tokenize(str)));
    }

}

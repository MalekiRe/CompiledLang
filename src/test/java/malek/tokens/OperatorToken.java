package malek.tokens;

import malek.AbstractToken;

public class OperatorToken extends AbstractToken {
    //Operators like +, =, /,
    @Override
    public String getID() {
        return "operator";
    }
}
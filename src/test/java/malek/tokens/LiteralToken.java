package malek.tokens;

import malek.AbstractToken;

public class LiteralToken extends AbstractToken {
    @Override
    public String getID() {
        return "literal";
    }
}

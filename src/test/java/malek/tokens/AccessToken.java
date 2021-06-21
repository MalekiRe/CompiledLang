package malek.tokens;

import malek.AbstractToken;

public class AccessToken extends AbstractToken {
    @Override
    public String getID() {
        return "access";
    }
}
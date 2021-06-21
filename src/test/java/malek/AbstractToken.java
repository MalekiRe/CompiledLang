package malek;

public abstract class AbstractToken {
    private String text = null;
     public void setText(String text) {
        this.text = text;
    }
    public String getText() {
         return this.text;
    }
    public abstract String getID();

}

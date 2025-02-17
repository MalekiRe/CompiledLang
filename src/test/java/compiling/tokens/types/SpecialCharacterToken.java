package compiling.tokens.types;

public class SpecialCharacterToken implements IToken {
	private final char c;
	
	public SpecialCharacterToken(char c) {
		this.c = c;
	}
	
	@Override
	public String getText() {
		return "" + c;
	}

	@Override
	public int getId() {
		return 3;
	}

	@Override
	public String toString() {
		return getText();
	}
}

package compiling.tokens;

public class TokenNames {
	public static final String[] access_keywords = {
			"public", "protected", "private",
			"static", "instance",
			"final", "modifiable",
	};
	
	public static final String[] type_keywords = {
			"class",
			"int", "long", "short", "byte",
			"float", "double",
			"void"
	};

	public static final String[] operator_keywords = {
			"+", "-", "*", "/", "=", "=="
	};


	public static final String[] special_keywords = {
			"{", "}", "(", ")", ";"
	};

}

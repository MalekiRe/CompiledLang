package third_compiler_attempt;

public class PunctuationType {
    public static final String[] punctuations = {
            "(", ")", "{", "}", ";", "->"
    };
    public static final String[] access_modifiers = {
            "public", "private", "protected", "static", "final", "mutable"
    };
    public static final String[] single_char_operators = {
            "+", "-", "*", "/", "="
    };
    public static final String[] operators = {
            "+", "-", "*", "/", "=", "==", "/=", "+=", "-=",  "*="
    };
    public static final String[] multi_char_operators = {
            "==", "/=", "+=", "*=", "-="
    };
    public static final String[] variables = {
            "void", "class", "char", "int", "double", "boolean", "float"
    };
}

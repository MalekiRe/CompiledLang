package third_compiler_attempt.action_tree;

import third_compiler_attempt.AccessLevel;
import third_compiler_attempt.Compiler;
import third_compiler_attempt.ContextToken;
import third_compiler_attempt.ast.ASTToken;
import third_compiler_attempt.enums.HighLevelType;
import third_compiler_attempt.enums.IDType;
import third_compiler_attempt.enums.ProtectionLevel;
import third_compiler_attempt.enums.StaticLevel;
import third_compiler_attempt.tokens.class_tokens.ClassToken;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import java.util.ArrayList;


import static third_compiler_attempt.enums.FinalLevel.FINAL;
import static third_compiler_attempt.enums.FinalLevel.MUTABLE;
import static third_compiler_attempt.enums.ProtectionLevel.*;
import static third_compiler_attempt.enums.StaticLevel.NON_STATIC;
import static third_compiler_attempt.enums.StaticLevel.STATIC;

public class ActionFile {
    /*
    public ArrayList<ASTToken> astTokens;
    public ContextToken[] contextTokens;
    public ArrayList<ClassToken> highestLevelClassTokens = new ArrayList<>();
    AccessLevel tempDefault = new AccessLevel(PUBLIC, NON_STATIC, MUTABLE);
    public void setClassTokens() {
        for(ASTToken astToken : astTokens) {
            if(astToken.type != IDType.CREATE_CLASS) {
                System.out.println("Compiler Error, non class found isolated in a file.");
              }
            else
            {
                AccessLevel accessLevel = determineAccessLevel(contextTokens, tempDefault, astToken);
                highestLevelClassTokens.add(new ClassToken(contextTokens, astToken, tempDefault, accessLevel, getName(contextTokens, astToken)));
            }
        }
    }
    public String getStringRep() {
        StringBuilder s = new StringBuilder();
        for(ClassToken token : highestLevelClassTokens) {
            s.append(token.getStringRep());
        }
        return s.toString();
    }
    public static String getName(ContextToken[] contextTokens, ASTToken astToken) {
        return contextTokens[astToken.positionInContext].value;
    }
    public static AccessLevel findAccessLevel(ContextToken[] contextTokens, ASTToken astToken) {
        System.out.println(getName(contextTokens, astToken));
        AccessLevel accessLevel = new AccessLevel();
        int pos = astToken.positionInContext;
        while(pos != 0 && !accessLevel.everythingSet() && Compiler.getHighLevelType(contextTokens[pos].value) != HighLevelType.PUNCTUATION) {
            if(Compiler.getHighLevelType(contextTokens[pos].value) == HighLevelType.ACCESS_MODIFIER) {
                switch (contextTokens[pos].value) {
                    case "public" : accessLevel.p = PUBLIC;
                    case "private" : accessLevel.p = PRIVATE;
                    case "protected" : accessLevel.p = PROTECTED;
                    case "static" : accessLevel.s = STATIC;
                    case "final" : accessLevel.f = FINAL;
                }
            }
        }
        return accessLevel;
    }
    public static AccessLevel determineAccessLevel(ContextToken[] contextTokens, AccessLevel defaultAccess, ASTToken astToken) {
        AccessLevel newAccess = findAccessLevel(contextTokens, astToken);
        return setAccessLevels(defaultAccess, newAccess);
    }
    public static AccessLevel setAccessLevels(AccessLevel defaultAccess, AccessLevel newAccess) {
        if(defaultAccess.s == STATIC) {
            newAccess.s = STATIC;
        }
        if(defaultAccess.f == FINAL) {
            newAccess.f = FINAL;
        }
        if(newAccess.p == ProtectionLevel.PUBLIC && defaultAccess.p == PROTECTED) {
            newAccess.p = PROTECTED;
        }
        if(newAccess.p == PROTECTED && defaultAccess.p == PRIVATE) {
            newAccess.p = PRIVATE;
        }
        return newAccess;
    }

     */
}

package com.comze.sanman00.lang;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class LanguageSyntax implements ISyntax {
    private static final LanguageSyntax INSTANCE = new LanguageSyntax();
    private static boolean variableValueContext = false;
    private static boolean variableIdentifierContext = false;
    private static boolean commentContext = false;
    private static boolean stringContext = false;
    private static int scope = 0;
    private static ArrayList<String> functionList = new ArrayList<>();
    private static ArrayList<String> varList = new ArrayList<>();
    private static HashMap<String, String> varMap = new HashMap<>();
    private static HashMap<String, Boolean> contexts = new HashMap<>();
    private static String[] symbols = new String[]{"&", "|", "^", "%", "*", "(", ")", "<", ">", "!", "`", "¬", "@", "'", "\"", "\\", ":", ";", "-", "+", "=", "[", "]", "{", "}", "#", "~", ",", ".", "?", "/"};

    /**
     * No need for instantiating the syntax class so don't let anyone do it.
     */
    LanguageSyntax() {
        Class<?> clazz = new SecurityManager() {
            Class<?> clazz = getClassContext()[2];
        }.clazz;
        
        if (!clazz.equals(getClass())) {
            throw new RuntimeException("Can't instantiate " + LanguageSyntax.class.getName() + "!");
        }
        
        for (Field f: getClass().getDeclaredFields()) {
            if (f.getType().equals(Boolean.TYPE)) {
                try {
                    contexts.put(f.getName(), f.getBoolean(null));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void parseSyntax(String code) {
        try {
            INSTANCE.parseSyntax0(code);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getVariableNames() {
        return varList;
    }
    
    public static HashMap<String, String> getVariableMap() {
        return varMap;
    }
    
    public static String[] getSymbolList() {
        return symbols;
    }
    
    public static ArrayList<String> getFunctionList() {
        return functionList;
    }
    
    public static int getScope() {
        return scope;
    }

    public static HashMap<String, Boolean> getCodeContexts() {
        return contexts;
    }
    
    @Override
    public void parseSyntax0(String code) throws SyntaxError {
        if (code.contains("let ")) {
            if (!variableIdentifierContext && !variableValueContext) {
                checkIsValidVar(code);
                variableIdentifierContext = false;
                variableValueContext = true;
            }
            
            if (variableValueContext) {
                getVarValue(code);
            }
        }
    }

    private static String checkIsValidVar(String name) {
        variableIdentifierContext = true;
        String varName = name.substring(name.indexOf("let ") + 4).trim();
        
        if(varName.contains(" = ")) {
            varName = varName.substring(0, varName.indexOf(" = "));
        }
        
        for (String symbol : symbols) {
            if (varName.contains(symbol)) {
                throw new SyntaxError("variable identifiers cannot contain symbols or start with a symbol");
            }
        }
        varList.add(varName);
        return varName;
    }
    
    private static String getVarValue(String var) {
        String varValue = var.substring(var.indexOf("=") + 1).trim();
        return varMap.put(var, varValue);
    }
    
    private static void checkIsValidFuncName(String name) {
        
    }
}

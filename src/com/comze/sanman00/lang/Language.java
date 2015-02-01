package com.comze.sanman00.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Language {
    public static void main(String[] args) throws Exception {
        boolean hasParsedFileArg = false;

        for (int x = 0; x < args.length; x++) {
            if (args[x].equalsIgnoreCase("-f") || args[x].equalsIgnoreCase("-file")) {
                if (!hasParsedFileArg) {
                    hasParsedFileArg = true;
                }

                else {
                    System.err.println("Cannot parse more than one file at the same time!");
                }
                
                File fileLocation = null;
                if (args.length > 1) {
                    fileLocation = new File(args[x + 1]);
                    if (!fileLocation.exists()) {
                        throw new FileNotFoundException(fileLocation.toString());
                    }
                }

                else {
                    throw new IllegalArgumentException("args.length must be greater than 1!");
                }

                BufferedReader br;
                if (fileLocation.toString().endsWith(".san")) {
                    try {
                        br = new BufferedReader(new FileReader(fileLocation));
                        String buffer = "";
                        String line;
                        while ((line = br.readLine()) != null) {
                            buffer += line;
                        }
                        br.close();
                        LanguageSyntax.parseSyntax(buffer);
                        System.out.println(buffer);
                        System.out.println(LanguageSyntax.getVariableMap());
                        System.out.println(LanguageSyntax.getCodeContexts());
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else {
                    System.err.println("File does not end in \".san\"!");
                    return;
                }
            }

            else if (args[x].equalsIgnoreCase("--help") || args[x].equalsIgnoreCase("-help")) {
                System.out.println("Help: \n"
                        + "'-f' or '-file' - run a file from the specified location\n"
                        + "'--help' or '-help' - for help\n"
                        + "'--version' or '-version' - get the version of this language\n"
                        + "'-e' - interpret code interactively\n"
                );
                System.exit(0);
            }

            if (args[x].equalsIgnoreCase("--version") || args[x].equalsIgnoreCase("-version")) {
                System.out.println("Version 0.0.5a (16/11/2014)");
            }

            else if (args[x].equalsIgnoreCase("-e")) {
                String code;
                if (args.length > (x + 1)) {
                    code = args[x + 1];
                    if (!(code == null) && !code.isEmpty()) {
                        LanguageSyntax.parseSyntax(code);
                    }
                    
                    else {
                        System.err.println("-e requires code after it!");
                    }
                }

                else {
                    throw new IllegalArgumentException("-e requires code after it!");
                }
                return;
            }

            else {
                System.err.println("Unrecognised command-line option: " + args[x] + "! Do \"--help\"");
            }
        }
    }
}

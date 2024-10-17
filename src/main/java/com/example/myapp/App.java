package com.example.myapp;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Simple Base64 converter. it takes either file or text as an input and will output a base64 form. it can also decode base64
 */

public class App {
    public static void main(String[] args) {
        Parser parser = new Parser(args);
        if (!parser.parse()) {
            System.out.println(parser.error());
            App.printHelp();
            return;
        }

        if(parser.showHelp()){
            App.printHelp();
        }

        try {
            if (parser.action().equals("ENCODE")) {
                String input = parser.input();
                byte[] bytes;
                if (parser.isInputFile()) {
                    bytes = Files.readAllBytes(Paths.get(input));
                } else {
                    bytes = input.getBytes();
                }
                
                String out = MyBase64.encode(bytes);
                if (parser.isOutFile()) {
                    Files.write(Paths.get(parser.output()), out.getBytes());
                } else {
                    System.out.println(out);
                }
            } else {
                String input = parser.input();
                String data;
                if (parser.isInputFile()) {
                    data = Files.readString(Paths.get(input));
                } else {
                    data = input;
                }

                byte[] bytes = MyBase64.decode(data);
                if (parser.isOutFile()) {
                    Files.write(Paths.get(parser.output()), bytes);
                } else {
                    System.out.println(new String(bytes));
                }
            }
        } catch (IOException e) {
            System.out.println("File operation failed: " + e.getMessage());
        }
    }


    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  java App encode [-f] <input> [-o <output>]");
        System.out.println("    Encode the input string or file to Base64.");
        System.out.println("    -f  Treat the input as a file path.");
        System.out.println("    -o  Specify an output file to write the result.");
        System.out.println();
        System.out.println("  java App decode [-f] <input> [-o <output>]");
        System.out.println("    Decode the Base64 string or file to its original form.");
        System.out.println("    -f  Treat the input as a file path.");
        System.out.println("    -o  Specify an output file to write the result.");
        System.out.println();
        System.out.println("  java App -h");
        System.out.println("    Show this help message.");
    }

}

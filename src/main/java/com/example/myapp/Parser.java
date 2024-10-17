package com.example.myapp;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private String action;
    private String input;
    private String output;
    private boolean isInputFile = false;
    private boolean isOutFile = false;
    private boolean showHelp = false;
    private String errorMessage = "";

    private String[] unparsed;

    public Parser(String[] args) {
        this.unparsed = args;
    }

    public boolean parse() {
        String[] args = this.unparsed;
        if (args.length < 2) {
            errorMessage = "Invalid number of arguments.";
            return false;
        }

        action = args[0].toUpperCase();
        if (!action.equals("ENCODE") && !action.equals("DECODE")) {
            errorMessage = "Unknown action. Use 'encode' or 'decode'.";
            return false;
        }

        List<String> argList = Arrays.asList(args);
        if (argList.contains("-h")) {
            showHelp = true;
            return false;
        }

        if (argList.contains("-f")) {
            int fileIndex = argList.indexOf("-f");
            if (fileIndex + 1 >= args.length) {
                errorMessage = "File path missing after '-f'.";
                return false;
            }
            input = args[fileIndex + 1];
            isInputFile = true;
        } else {
            if (args.length < 2) {
                errorMessage = "No input provided.";
                return false;
            }
            input = args[1];
        }

        if (argList.contains("-o")) {
            int outputIndex = argList.indexOf("-o");
            if (outputIndex + 1 >= args.length) {
                errorMessage = "Output file path missing after '-o'.";
                return false;
            }
            output = args[outputIndex + 1];
            isOutFile = true;
        }
        return true;
    }

    public String action() {
        return action;
    }

    public String input() {
        return input;
    }

    public boolean isInputFile() {
        return isInputFile;
    }

    public boolean isOutFile() {
        return isOutFile;
    }

    public String output() {
        return output;
    }

    public String error() {
        return errorMessage;
    }

    public boolean showHelp() {
        return showHelp;
    }

}

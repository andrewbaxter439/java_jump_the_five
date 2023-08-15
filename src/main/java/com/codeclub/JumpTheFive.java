package com.codeclub;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.HashMap;
import java.util.Map;


@Command(name = "JumpTheFive", description = "A handy, dandy app for doing whatever you like")
public class JumpTheFive
{

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private final boolean helpRequested = false;

    @Parameters(description = "Input text")
    private String[] inputText;

    private static final Map<Character, Character> lookup = new HashMap<>();

    private static void setNums() {

        char[] in_nums = "1234567890".toCharArray();
        char[] out_nums = "9876043215".toCharArray();

        for (int i = 0; i < in_nums.length; i++) {
            lookup.put(in_nums[i], out_nums[i]);
        }

    }

    public static void main( String[] args )
    {
        JumpTheFive init_app = new JumpTheFive();
        boolean no_text = false;

        try {
            new CommandLine(init_app).parseArgs(args);
        } catch (CommandLine.MissingParameterException e) {
            no_text = true;
        }

        if (init_app.helpRequested | no_text) {
            CommandLine.usage(new JumpTheFive(), System.out);
            return;
        }
        setNums();
        init_app.run();
    }

    public char subNumber(Character num) {
        return lookup.get(num);
    }

    public void run() {

        String joined = String.join( " ", inputText);

        char[] letters = joined.toCharArray();

        StringBuilder out;
        out = new StringBuilder();

        for (Character letter: letters) {
            if (Character.isDigit(letter)) {
                letter = subNumber(letter);
            }
            out.append(letter);
        }

        System.out.println(out);

    }
}

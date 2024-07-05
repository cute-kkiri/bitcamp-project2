package bitcamp.project2.util;

public class Ansi {

    public static String strikeout = "\033[9m";
    public static String bold = "\033[1m";

    public static String gray = "\033[38;5;240m";
    public static String lightGray = "\033[38;5;244m";
    public static String cyan = "\033[36;5;230m";
    public static String green = "\033[32;5;250m";
    public static String red = "\u001B[38;2;255;0;0m";
    public static String lightRed = "\u001B[38;2;255;170;170m";
    public static String orange = "\u001B[38;5;208m";
    public static String yellow = "\u001B[33m";
    public static String brightYellow = "\u001B[38;5;220m";
    public static String lightYellow = "\u001B[38;5;190m";

    public static String reset = "\033[0m";


}

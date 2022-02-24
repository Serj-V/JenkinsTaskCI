package utils;

public abstract class LineUtils {

    public static int deleteLettersSymbols(String string){
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }

    public static int getIntValue(String str){
        return Integer.parseInt(str);
    }
}

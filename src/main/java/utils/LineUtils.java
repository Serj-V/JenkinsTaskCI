package utils;

import java.util.Random;

public class LineUtils {
    public static String getRandomString(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<10;i++){
            int number= new Random().nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static int deleteLettersSymbols(String string){
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }

    public static int getIntValue(String str){
        return Integer.parseInt(str);
    }
}

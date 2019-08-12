package com.study.algorithm;

import java.util.Scanner;

public class StringZip {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(compressBad(str));
    }

    public static String compressBad(String str){
        int size = countCompression(str);
        if(size >= str.length()){
            return str;
        }
        StringBuffer mystr = new StringBuffer();
        char last = str.charAt(0);
        int count = 1;
        for(int i =1;i<str.length();i++){
            if(str.charAt(i)==last){
                count++;
            }else{
                mystr.append(last);
                mystr.append(count);
                last =str.charAt(i);
                count = 1;
            }
        }
        mystr.append(last);
        mystr.append(count);
        return  mystr.toString();
    }

    private static int countCompression(String str) {
        if(str == null || str.isEmpty()){
            return 0;
        }
        char last = str.charAt(0);
        int size = 0;
        int count = 1;
        for(int i = 1;i<str.length();i++){
            if(str.charAt(i) == last){
                count++;
            }else{
                last =str.charAt(i);
                size += 1+ String.valueOf(count).length();
                count =1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

}

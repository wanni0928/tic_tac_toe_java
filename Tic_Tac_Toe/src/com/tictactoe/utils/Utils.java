package com.tictactoe.utils;

public class Utils {
	public String[][] deepCopy(String[][] arSrc){
        String[][] arDest = new String[3][3];
        for (int i = 0; i < arDest.length; i++) {
            System.arraycopy(arSrc[i], 0, arDest[i], 0, arDest.length);
        }
        return arDest;
    }
}

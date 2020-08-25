package com.tictactoe.utils;

public class Utils {
	public String[][] deepCopy(String[][] arSrc, int depthOne, int depthTwo){
        String[][] arDest = new String[depthOne][depthTwo];
        for (int i = 0; i < arDest.length; i++) {
            System.arraycopy(arSrc[i], 0, arDest[i], 0, arDest.length);
        }
        return arDest;
    }
	public String[] deepCopy(String[] arSrc, int depthOne){
        String[] arDest = new String[depthOne];
        System.arraycopy(arSrc, 0, arDest, 0, arDest.length);
        return arDest;
    }
}

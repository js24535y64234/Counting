package com.example.count;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class TextFileReader {
    private final String filePath;
    private final ArrayList<String> wordsArrayList;
    private InputStream is = null;

    public TextFileReader(String filePath) {
        this.filePath = filePath;
        wordsArrayList = readText();
    }
    public InputStream getIs() {
        return is;
    }
    private ArrayList<String> readText() {
        ArrayList<String> wordsT = new ArrayList<>();
        Context mContext = MainActivity.tContext;
        MainActivity.doesFileExist = true;
        try {
            String[] files = mContext.getAssets().list("");
            assert files != null;
            for (String file : files) {
                System.out.println(file);
            }
            is = mContext.getAssets().open(filePath);
            System.out.println(is.read());
        } catch (IOException e) {
            System.out.println(" ");
            MainActivity.doesFileExist = false;
        }
        Scanner s = new Scanner(getIs());
        while (s.hasNext()) {
            String str = s.next();
            wordsT.add(str);
        }
        return wordsT;
    }
    public ArrayList<String> getWords() {
        return wordsArrayList;
    }
}
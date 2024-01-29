package com.example.count;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String fileName;
    static String t;
    TextView fileNotFound, results;
    EditText fileNameEdit;
    Button top, top5;
    @SuppressLint("StaticFieldLeak")
    public static Context tContext;
    public static boolean doesFileExist = true;

    @Override
    protected void onCreate(Bundle savedInstance) {
        tContext = MainActivity.this;
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        fileNotFound = (TextView) findViewById(R.id.fileNotFound);
        results = (TextView) findViewById(R.id.resulting);

        fileNameEdit = (EditText) findViewById(R.id.fileNameEdit);

        top = (Button) findViewById(R.id.top);
        top5 = (Button) findViewById(R.id.top5);

        top.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v){
                fileName = String.valueOf(fileNameEdit.getText());
                System.out.println(fileName);
                Counter counter = new Counter("commonWords.txt", fileName);
                if (doesFileExist) {
                    String[] fiveHighestWords = counter.getFiveHighestWords();
                    int[] fiveHighestFrequencies = counter.getFiveHighestFrequencies();
                    String set_text = "The most used word in file " + fileName + " was " +"\""+ fiveHighestWords[0] + "\"" + "with " + fiveHighestFrequencies[0] + " occurrences.";
                    results.setText(set_text);
                    System.out.println();
                } else {
                    fileNotFound.setText("No File Exists!");
                    results.setText("");
                }
            }
        });
        top5.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v){
                fileName = String.valueOf(fileNameEdit.getText());
                System.out.println(fileName);
                Counter counter = new Counter("commonWords.txt", fileName);
                if (doesFileExist) {
                    String[] fiveHighestWords = counter.getFiveHighestWords();
                    int[] fiveHighestFrequencies = counter.getFiveHighestFrequencies();
                    t = "The top " + fiveHighestWords.length + " words in text one and their frequencies are:\n";
                    printTopFive(fiveHighestWords, fiveHighestFrequencies, 0);
                    results.setText(t);
                } else {
                    fileNotFound.setText("No File Exists!");
                    results.setText("");
                }
            }
        });
    }

    private static void printTopFive(String[] HighestWords, int[] HighestFrequencies, int tCnt) {
        for (int i = 0; i < HighestWords.length; ++i) {
            if (i == 0) {
                t += (i+1 + ") " + HighestWords[i] + ": " + HighestFrequencies[i]);
                t += "\n";
            }
            else {
                if (HighestFrequencies[i] == HighestFrequencies[i-1]) {
                    tCnt++;
                    t += (i+1-tCnt + ") " + HighestWords[i] + ": " + HighestFrequencies[i]);
                    t += "\n";
                } else {
                    tCnt = 0;
                    t += (i+1 + ") " + HighestWords[i] + ": " + HighestFrequencies[i]);
                    t += "\n";
                }
            }
        }
    }
}
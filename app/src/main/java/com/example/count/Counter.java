package com.example.count;
import java.util.ArrayList;
public class Counter {
    private final ArrayList<Integer> occurrences;
    private final ArrayList<String> sortedWords;
    private final ArrayList<String> uniqueWords;

    public int[] fiveHighestFrequencies;
    public String[] fiveHighestWords;
    public Counter(String commonWordsFilePath, String textFilePath) {
        SortText sortText = new SortText(commonWordsFilePath, textFilePath);
        sortedWords = sortText.getSortedWords();
        uniqueWords = new ArrayList<>();
        occurrences = new ArrayList<>();
        uniqueOccurrences();
        topFive();
    }

    private void topFive() {
        ArrayList<String> topFiveWords = new ArrayList<>();
        ArrayList<Integer> topFiveFrequencies = new ArrayList<>();
        ArrayList<Integer> topFiveIndexes = new ArrayList<>();

        int i = 0, lastMax = -1;
        boolean shouldContinue = true;
        while (true) {
            int loopMaxValue = 0, x = -1;
            for (int j = 0; j < occurrences.size(); ++j) {
                if (topFiveIndexes.contains(j)) {
                    continue;
                }
                if (occurrences.get(j) >= loopMaxValue) {
                    x = j;
                    loopMaxValue = occurrences.get(j);
                }
            }
            if (i >= 5) shouldContinue = loopMaxValue == lastMax;
            if (!shouldContinue) {
                break;
            } else {
                topFiveIndexes.add(x);
                topFiveWords.add(uniqueWords.get(topFiveIndexes.get(i)));
                topFiveFrequencies.add(occurrences.get(topFiveIndexes.get(i)));
                ++i;
                lastMax = loopMaxValue;
            }
        }
        fiveHighestWords = new String[topFiveWords.size()];
        for (int k = 0; k < fiveHighestWords.length; ++k) {
            fiveHighestWords[k] = topFiveWords.get(k);
        }
        fiveHighestFrequencies = new int[topFiveFrequencies.size()];
        for (int k = 0; k < fiveHighestFrequencies.length; ++k) {
            fiveHighestFrequencies[k] = topFiveFrequencies.get(k);
        }
    }

    private void uniqueOccurrences() {
        for (String s : sortedWords) {
            int index = uniqueWords.indexOf(s);
            if (index != -1) {
                occurrences.set(index, occurrences.get(index) + 1);
            } else {
                uniqueWords.add(s);
                occurrences.add(1);
            }
        }
    }
    public String[] getFiveHighestWords() {
        return fiveHighestWords;
    }
    public int[] getFiveHighestFrequencies() {
        return fiveHighestFrequencies;
    }
}
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class TextModel {
    private HashMap<String, StatsObj> observations = new HashMap<>();
    private String[] wordList;

    public TextModel() {
    }

    private static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void loadText(String file, int order) throws IOException {
        String fileContents = readFile(file);
        String[] lines = splitFile(fileContents);
        wordList = lines;
        nthOrderMarkov(lines, order);
    }

    private void nthOrderMarkov(String[] lines, int order) {
        for (int i = 0; i < lines.length - order; i++) {
            String firstWords = "";
            for (int j = 0; j < order; j++) {
                firstWords += lines[i + j] + " ";
            }
            addPair(firstWords, lines[i + order]);
        }
    }

    public void addPair(String firstWord, String secondWord) {
        boolean inHashMap = observations.containsKey(firstWord);
        if (inHashMap) {
            observations.get(firstWord).add(secondWord);
        } else {
            observations.put(firstWord, new StatsObj(firstWord));
            observations.get(firstWord).add(secondWord);
        }
    }

    private String[] splitFile(String fileContents) {
        fileContents = fileContents.replaceAll("\n", " ");
        fileContents = fileContents.replaceAll("\r", " ");
        String[] lines = fileContents.split(" ");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }
        return removeExcessSpace(lines);
    }

    private String[] removeExcessSpace(String[] stripped) {
        ArrayList<String> lines2 = new ArrayList<>();
        for (String word : stripped) {
            if (!word.equals("")) lines2.add(word);
        }
        String[] lines = new String[lines2.size()];
        for (int i = 0; i < lines2.size(); i++) {
            lines[i] = lines2.get(i);
        }
        return lines;
    }

    public String simulateNextWord(String word) {
        if (observations.containsKey(word)) return observations.get(word).getRandom();
        return "";
    }

    public String getFirstN(int n) {
        String word = "";
        for (int i = 150; i < n + 150; i++) {
            word += wordList[i] + " ";
        }
        return word;
    }
}
import java.util.ArrayList;

public class StatsObj {
    private String word;
    private ArrayList<String> wordsAfter = new ArrayList<>();
    private ArrayList<Integer> instancesAfterObj = new ArrayList<>();

    public StatsObj(String word) {
        this.word = word;
    }

    public void add(String word) {
        boolean inWordsAfter = wordsAfter.contains(word);
        if (!inWordsAfter) {
            wordsAfter.add(word);
            instancesAfterObj.add(1);
        } else {
            incrementInstanceOfWord(word);
        }
    }

    private void incrementInstanceOfWord(String word) {
        for (int i = 0; i < wordsAfter.size(); i++) {
            if (wordsAfter.get(i).equals(word)) {
                instancesAfterObj.set(i, instancesAfterObj.get(i) + 1);
            }
        }
    }

    public String getRandom() {
        double random = Math.random();
        int total = totalTimesAppeared(instancesAfterObj);
        int instances = 0;
        for (int i = 0; i < wordsAfter.size(); i++) {
            instances += instancesAfterObj.get(i);
            if (random < (double) instances / total) {
                return wordsAfter.get(i);
            }
        }
        return "the"; //should never reach here
    }

    public int totalTimesAppeared(ArrayList<Integer> counts) {
        int sum = 0;
        for (int i : counts) {
            sum += i;
        }
        return sum;
    }
}
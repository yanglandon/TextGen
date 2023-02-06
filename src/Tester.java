import java.io.IOException;

public class Tester {

    public static void main(String[] args) {
        TextModel markovModel = new TextModel();
        int order = 2;
        try {
            markovModel.loadText("data/greeneggsandham.txt", order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nthOrderPrint(markovModel, order);
    }

    private static void nthOrderPrint(TextModel markovModel, int n) {
        System.out.println("Order: " + n);
        String text = markovModel.getFirstN(n);
        System.out.println(text);
        for (int i = 0; i < 300; i++) {
            String nextWord = markovModel.simulateNextWord(text);
            text += nextWord + " ";
            System.out.print(nextWord + " ");
            if (nextWord.contains(".")) System.out.println();
            int indexOfSpace = text.indexOf(" ");
            text = text.substring(indexOfSpace + 1);
        }
    }
}
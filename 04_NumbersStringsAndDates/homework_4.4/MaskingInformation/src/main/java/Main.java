public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        int secondDiamondsIndex = 1;
        while (secondDiamondsIndex > 0) {
            int firstDiamondsIndex = text.indexOf("<");
            secondDiamondsIndex = text.indexOf(">");
            String firstString, secondString;
            if ((firstDiamondsIndex > 0) & (secondDiamondsIndex > 0)) {
                firstString = text.substring(0, firstDiamondsIndex);
                secondString = text.substring(secondDiamondsIndex + 1);
                text = firstString + placeholder + secondString;
            }
        }
        return text;
    }

}
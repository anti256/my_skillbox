public class Main {

    public static void main(String[] args) {

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        //строка - скобка < - что угодно, кроме скобки > - скобка >
        text = text.replaceAll("<[^>]*>", placeholder);
        return text;
    }

}
public class alphabetChar {

  public static void main(String[] args) {
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    int stringLenght = alphabet.length();

    for (int i = 0; i < stringLenght; i++) {
      char symbol = alphabet.charAt(i);
      System.out.println("Символ " + alphabet.charAt(i) + ", код - " + ((int) symbol));
    }

  }

}

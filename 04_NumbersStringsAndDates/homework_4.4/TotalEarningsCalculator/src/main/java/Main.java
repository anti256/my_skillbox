public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    int spaceIndex, totalMoney = 0;
    String textCut;
    char firstChar;

    while (text.indexOf(" ") > -1) {
      spaceIndex = text.indexOf(" ");
      textCut = text.substring(0, spaceIndex);
      text = text.substring(spaceIndex + 1);
      firstChar = textCut.charAt(0);
      if (Character.isDigit(firstChar)) {totalMoney = totalMoney + Integer.parseInt(textCut);}
    }

    System.out.println(totalMoney);
  }

}
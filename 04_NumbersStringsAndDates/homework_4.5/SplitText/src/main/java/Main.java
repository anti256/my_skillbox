public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextInToWords(String text) {
    text = text.replaceAll("[^’'a-zA-Z]", " ");//заменяем все "небуквы" и "неапострофы" на пробелы
    text = text.replaceAll("\\s+", " ");//заменяем последовательности пробелов одним пробелом
    text = text.trim();//убираем лишние пробелы по краям строки
    text = text.replaceAll("\\s", "\r\n");//заменяем пробелы переводом на следующую строку
    return text;
  }

}
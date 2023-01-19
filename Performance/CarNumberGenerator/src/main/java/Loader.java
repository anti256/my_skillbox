import java.io.File;
import org.apache.commons.io.FileUtils;

public class Loader {

    public static void main(String[] args) throws Exception {
      FileUtils.cleanDirectory(
          new File("./Performance/CarNumberGenerator/res/numbers_files"));//очистка директории с сохраненными файламы
        long start = System.currentTimeMillis();//засекаем время

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};//набор букв для номера


        for (char firstLetter : letters) {//первая буква номера
          for (char secondLetter : letters) {//вторая буква номера
              new ThreadLetterNumber(firstLetter, secondLetter, start).start();
            }
        }

        //System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}

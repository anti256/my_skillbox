import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите путь до папки:");
            String inputDir = scanner.nextLine();
            if (inputDir.equals("0")){break;}//выход из бесконечного цикла
            System.out.println(FileUtils.pathToSize(inputDir));
        }
    }
}
/*получать через консоль путь от пользователя до папки;
получить размер всех файлов в указанной папке и ее подпапках, реализация этого должна быть написана в методе calculateFolderSize() класса FileUtils;
выводить полученную сумму файлов в удобочитаемом виде — в байтах, килобайтах, мегабайтах или гигабайтах;
программа должна перехватывать все исключения, возникающие при ошибках чтения файлов и папок, и выводить сообщение об ошибке с трассировкой стека (stack trace).*/
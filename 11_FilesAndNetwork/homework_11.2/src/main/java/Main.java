import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите папку, которую нужно копировать:");
            String firstDir = scanner.nextLine();
            if (firstDir.equals("0")){break;}//выход из бесконечного цикла
            System.out.println("Введите папку, в которую нужно копировать:");
            String secondDir = scanner.nextLine();
            if (secondDir.equals("0")){break;}//выход из бесконечного цикла
            FileUtils.copyFolder(firstDir,secondDir);
        }
    }
}
/*
- Напишите код, который копирует одну указанную папку в другую. При копировании должны сохраниться файлы и структура папки.
- Папки запрашивайте у пользователя в консоли.
- Копирование реализуйте в методе copyFolder() класса FileUtils и проверьте работу метода с помощью тестов.
- Программа должна перехватывать все исключения, возникающие при ошибках чтения файлов и папок, и
выводить сообщение об ошибке с трассировкой стека (stack trace).*/
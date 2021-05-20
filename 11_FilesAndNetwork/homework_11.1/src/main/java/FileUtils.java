import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static long calculateFolderSize(String path) {//вычисление суммы размеров файлов
        Long memory = 0l;
        MyFileVisitor MFV = new MyFileVisitor();//экземпляр класса обработчика директорий
        try {
            Files.walkFileTree(Paths.get(path), MFV);//пробег по директории path
            memory = MFV.getM();//сумма размеров файлов
        } catch (IOException e) {
            e.printStackTrace();
            return -1l;
        }
        return memory;
    }

    public static String pathToSize (String path){//преобразование строки-пути в инфо-строку-размер
        Long memSize = 0l;
        memSize = calculateFolderSize(path);//вычисление суммы размеров файлов
        if (memSize == -1l){return "";};
        Long firstSize = memSize;
        if (String.valueOf(memSize).length() < 5) return String.valueOf("Размер папки " + path +
                                " cоставляет " + memSize + " байт");
        memSize = memSize*10/1024;
        if (String.valueOf(memSize).length() < 6) return String.valueOf("Размер папки " + path +
                                " cоставляет " + (memSize/10.0) + " Кб или " + firstSize + " байт");
        memSize = memSize/1024;
        if (String.valueOf(memSize).length() < 6) return String.valueOf("Размер папки " + path +
                                " cоставляет " + (memSize/10.0) + " Мб или " + firstSize + " байт");
        memSize = memSize/1024;
        if (String.valueOf(memSize).length() < 6) return String.valueOf("Размер папки " + path +
                                " cоставляет " + (memSize/10.0) + " Гб или " + firstSize + " байт");
        memSize = memSize/1024;
        return String.valueOf("Размер папки " + path + " cоставляет " + (memSize/10.0) +
                                " Тб или " + firstSize + " байт");
        }
    }

/*
- метод walkFileTree пробегает по всем директориям и поддиректориям
- Первый аргумент это путь папки с которой начинается пробег
- второй аргумент это класс, наследующий класс SimpleFileVisitor
- SimpleFileVisitor наследует интерфейс FileVisitor
- в FileVisitor четыре метода работы с директориями, чтобы не приходилось переопределять
их всех, делается такое перенаследование
- в SimpleFileVisitor все методы интерфейса FileVisitor переопределены, нам остается переопределить только нужный нам
- по сути второй аргумент это обработчик директории
- SimpleFileVisitor заходит во все файлы в директории
 */
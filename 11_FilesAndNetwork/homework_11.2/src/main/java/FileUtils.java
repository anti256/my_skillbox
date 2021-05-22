import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        try {
            //экземпляр класса обработчика директорий
            MyFileVisitor myFileV = new MyFileVisitor(Paths.get(sourceDirectory),Paths.get(destinationDirectory));
            Files.walkFileTree(Paths.get(sourceDirectory), myFileV);//пробег по директории path
        } catch (Exception e) {e.printStackTrace();}
    }
}
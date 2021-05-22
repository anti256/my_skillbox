import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
  private Path fromPath;//путь откуда
  private Path toPath;//путь куда

  public MyFileVisitor(Path path1, Path path2){//конструктор
  fromPath = path1;
  toPath = path2;
  }

  @Override //обработчик до входа в каталог
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    try {
      //создание конечного пути, relativize вырезает кусок пути, resolve прибавляет вырезанный кусок к пути куда копируется
      Path targetPath = toPath.resolve(fromPath.relativize(dir));
      //если целевого каталога не существует, он создается – копируется имя
      if (!Files.exists(targetPath)) {Files.createDirectory(targetPath);}
    }
    catch (IOException ex){ex.getStackTrace();}
    return FileVisitResult.CONTINUE;
  }

  @Override //обработчик в каталоге
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
    try{
    //копирование файла, опция REPLACE_EXISTING означает, что если в целевой директории файл существует, он будет заменен
    Files.copy(file, toPath.resolve(fromPath.relativize(file)), StandardCopyOption.REPLACE_EXISTING);}
    catch (IOException ex){ex.getStackTrace();}
    return FileVisitResult.CONTINUE;}
  }

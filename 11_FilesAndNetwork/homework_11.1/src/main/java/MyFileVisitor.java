import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
  private Long m = 0l;

  public Long getM() {//геттер
    return m;
  }

  @Override //переопределение метода из SimpleFileVisitor
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    m += attrs.size();//размер файлов в байтах
    return FileVisitResult.CONTINUE;
  }

}
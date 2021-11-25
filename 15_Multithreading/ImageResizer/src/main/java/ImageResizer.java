import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class ImageResizer extends Thread{
  private File[] files;
  private int newWidth;
  private String dstFolder;

  public File[] getFiles() {
    return files;
  }

  //конструктор
  public ImageResizer(File[] files, int newWidth, String dstFolder) {
    this.files = files;
    this.newWidth = newWidth;
    this.dstFolder = dstFolder;
  }

  @Override
  public void run() {
    try {
      for (File file : files) {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
          continue;
        }

        int newHeight = (int) Math.round(
            image.getHeight() / (image.getWidth() / (double) newWidth));//новая высота
        //масштабируется картинка
        Image resultingImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        //создается пустая картинка
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        //отмасштабируемая картинка натягивается на пустую
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

        //если целевого каталога не существует, он создается – копируется имя
        if (!Files.exists(Paths.get(dstFolder))) {Files.createDirectory(Paths.get(dstFolder));}
        File newFile = new File(dstFolder + "/" + file.getName());
        ImageIO.write(outputImage, "jpg", newFile);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
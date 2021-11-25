import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        //String srcFolder = "c:/users/sortedmap/Desktop/src";
        String srcFolder = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() +
            "/src";//папка источника в windows
        //String dstFolder = "c:/users/sortedmap/Desktop/dst";
        String dstFolder = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() +
            "/dst";//папка получатель в windows
        //количество ядер, доступных java
        int cores = Runtime.getRuntime().availableProcessors();

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();//массив копируемых файлов
        int countFiles = files.length/cores;//количество файлов в каждом потоке

        //инициализация массива массивов с файлами
        ArrayList<File[]> filesArray = new ArrayList<File[]>();
        if (countFiles > 1) {
            for (int i = 0; i < cores-1; i++) {
                filesArray.add(new File[countFiles]);
            }
            //т.к. countFiles может быть не целым, последний элемент-массив содержит больше файлов
            filesArray.add(new File[countFiles + (files.length % cores)]);
        } else {filesArray.add(files);}//если процессор один, будет всего один элемент с начальным массивом файлов

        //Наполнение массивов файлами
        for (int i = 0; i < filesArray.size(); i++) {
            System.arraycopy(files,i * countFiles, filesArray.get(i),0, filesArray.get(i).length);
        }

        //arrayList обработчиков
        ArrayList<ImageResizer> resizerArray = new ArrayList<>();
        for (int i = 0; i < cores; i++) {
            resizerArray.add(new ImageResizer(filesArray.get(i), newWidth, dstFolder));
            resizerArray.get(i).run();
        }
    }
}

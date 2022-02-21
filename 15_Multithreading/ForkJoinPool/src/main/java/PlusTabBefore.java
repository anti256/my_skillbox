import java.util.ArrayList;
import java.util.Collections;

public class PlusTabBefore {

    public static ArrayList<String> putTab (ArrayList<String> urlList){
        ArrayList<String> readyList = new ArrayList<>();//готовый список
        ArrayList<Integer> countsTab = new ArrayList<>();//массив с данными по количеству табов в строке
        countsTab.add(0);//нулевой элемент списка ссылок всегда исходная ссылка поиска без табов
        Collections.sort(urlList);//сортируем исходный список "по-умолчанию"
        readyList.add(urlList.get(0));//добавляем в готовый список исходную ссылку поиска
        for (int j = 1; j < urlList.size(); j++) {//цикл по всем элементам исходного списка кроме првого
            int count = 0;//количество табов
            String defStrOne;//текущая строковая переменная

            //получаем количество / в ссылке, вычетаем две, т.к. они идут от http://
//            String defStrOne = urlList.get(j).replaceAll("/","");
//            int count = urlList.get(j).length() - defStrOne.length() - 2;

            //если ссылка заканчивается на /
            if(urlList.get(j).matches("(.+)[/]{1}")) {
                //count--;
                //из строки убираем пробелы с концов и / в конце строки
                defStrOne = urlList.get(j).strip().substring(0,urlList.get(j).strip().lastIndexOf("/"));
            } else {defStrOne = urlList.get(j).strip();}//иначе только убираем пробелы

            //обрабатываем предыдущую строку для анализа
            //из строки убираем пробелы с концов
            String beforeStr = urlList.get(j-1).strip();
            //если ссылка заканчивается на /
            if(beforeStr.matches("(.+)[/]{1}")) {
                // убираем её
                beforeStr = beforeStr.substring(0,beforeStr.lastIndexOf("/"));
            }

            //если элемент является "братом" предыдущего, т.е. они на одном уровне
            //если содержимое текущей строки до последней/ такое же, как и у предыдущей
            if (defStrOne.substring(0,defStrOne.lastIndexOf("/"))
                    .equals(beforeStr.substring(0,beforeStr.lastIndexOf("/")))){
                count = countsTab.get(j-1);//количество табов берем как у предыдущего
            } else {//иначе ищем предка в предыдущих элементах списка
                for (int k = j - 1; k >= 0; k--) {//проходим циклом по всем предыдущим элементам
                    String beforeSearchParent = urlList.get(k).strip();//убираем из анализируемого предыдущего элемента пробелы с концов
                    //если ссылка заканчивается на .html, удаляем эту часть
                    if (beforeSearchParent.matches(".+" + "html")) beforeSearchParent = beforeSearchParent
                            .substring(0,beforeSearchParent.lastIndexOf(".html"));
                    //если ссылка заканчивается на /
                    if(beforeSearchParent.matches("(.+)[/]{1}")) {
                        // убираем её
                        beforeSearchParent = beforeSearchParent.substring(0,beforeSearchParent.lastIndexOf("/"));
                    }
                    //если кусок текущей строки по последнюю / равен обработанному предыдущему элементу с / на конце, т.е является потомком
                    if (defStrOne.substring(0,defStrOne.lastIndexOf("/")+1).equals(beforeSearchParent + "/")) {
                        count = countsTab.get(k) + 1;// количество табов - как у предка +1
                        break;
                    } else count = 1;//при любых раскладах минимальный таб будет равен единице
                }
            }

            //набираем нужное количество табов в строку
            String plusStr = "";
            for (int i = 0; i < count; i++) {
                plusStr += "\t";
            }
            countsTab.add(count);

            readyList.add(plusStr + urlList.get(j));//получаем строку с нужным количеством табов спереди
        }
        return  readyList;
    }
}

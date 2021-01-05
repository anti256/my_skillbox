import java.util.ArrayList;

public class TodoList{

   ArrayList<String> list = new ArrayList<>();

    //обработка по слову ADD
    public void addToArrayList (String[] strArray){
        //если введеных слов минимум два и второе это число и это число положительное
        if ((strArray.length > 1) && (strArray[1].matches("\\d+"))
                && (Integer.parseInt(strArray[1]) >= 0) ) {
            int position = Integer.parseInt(strArray[1]); //сохраняем позицию
            //"уничтожаем" служебные слова
            strArray[0] = "";
            strArray[1] = "";
            //пересобираем строку из массива - остается только строка, которую нужно добавить в список
            String stroka = String.join(" ", strArray);
            stroka = stroka.trim();//первые два пустые элементы добавляют лишние пробелы, убираем их
            //если позиции не существует, добавляем в конец списка
            if (position >= getTodos().size()){add(stroka);}
            //иначе добавляем в указанную позицию
            else {add(position, stroka);}
        }
        //если не выполняется хотя бы одно из трех условий
        else {
            strArray[0] = "";//убираем слово ADD
            String stroka = String.join(" ", strArray);//пересобираем строку
            stroka = stroka.trim();//удаляем лишние пробелы
            add(stroka);//долбавляем строку в конец списка
        }
    }

    //обработка по слову EDIT
    public void editPosArrayList(String[] strArray){
        //если второе слово - число и оно положительное
        if (strArray[1].matches("\\d+") && (Integer.parseInt(strArray[1]) >= 0)){
            int position = Integer.parseInt(strArray[1]);//сохраняем позицию
            //"уничтожаем" служебные слова
            strArray[0] = "";
            strArray[1] = "";
            //пересобираем строку из массива - остается только нстрока под замену в списке
            String stroka = String.join(" ", strArray);
            stroka = stroka.trim();//первые два пустые элементы добавляют лишние пробелы, убираем их
            edit(stroka, position);}}//заменяем строку по позиции

   public void add(String todo) {
        list.add(todo);
    }

    public void add(int index, String todo) {
        if (index < list.size()) {list.add(index, todo);}
        else {list.add(todo);}
    }

    public void edit(String todo, int index) {
        if (index < list.size()) {list.set(index, todo);}
    }

    public void delete(int index) {
        if (index < list.size()){
            System.out.println("delet");
            list.remove(index);}
    }

    public ArrayList<String> getTodos() {
        return list;
    }


}
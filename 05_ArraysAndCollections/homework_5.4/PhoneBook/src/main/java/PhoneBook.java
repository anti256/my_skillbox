import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    TreeMap<String, ArrayList<String>> phoneMap = new TreeMap<>();

    //5.нет телефона, нет имени
    private void ifMapEmpty(String phoneME, String nameME){
        ArrayList<String> defaultValueArrayList = new ArrayList<>();
        defaultValueArrayList.add(phoneME);
        phoneMap.put(nameME, defaultValueArrayList);
        System.out.println("Контакт сохранен!");
    }

    //2.есть телефон, нет имени
    private void isPhoneNoName(String inputPhone, String inputName, String nameByInput){
      ArrayList<String> defaultArrayValue = new ArrayList<>(phoneMap.get(nameByInput));
      defaultArrayValue.remove(inputPhone);//удаляем из списка телефон
      if (!defaultArrayValue.isEmpty()) {//если получившийся список не пустой
        phoneMap.put(nameByInput, defaultArrayValue);//сохраняем запись уже без телефона
      } else {phoneMap.remove(nameByInput);}
      ifMapEmpty(inputPhone,inputName);//создаем новую запись имя-телефон
    }

    //3. нет телефона, есть имя
    private void noPhoneIsName (String inputPhone, String inputName){
      TreeSet<String> defaultPhoneList = getClearPhonesByName(inputName);//сохраняем список с телефоном
      defaultPhoneList.add(inputPhone);//добавляем к списку телефонов
      ArrayList<String> defaultArrayValue = new ArrayList<>(defaultPhoneList);//преобразуем список в ArrayList
      phoneMap.put(inputName,defaultArrayValue);//сохраняем запись уже c телефоном
      System.out.println("Контакт сохранен!");
    }

    //4.есть и телефон и имя, но они в разных записях
    private void isPhoneIsName(String inputPhone, String inputName, String nameByInput){
      TreeSet<String> defaultPhoneList = getClearPhonesByName(nameByInput);//сохраняем список с телефоном
      defaultPhoneList.remove(inputPhone);//удаляем из списка телефон
      ArrayList<String> defaultArrayValue = new ArrayList<>(defaultPhoneList);//преобразуем список в ArrayList
      if (!defaultArrayValue.isEmpty()) {//если получившийся список не пустой
      phoneMap.put(nameByInput, defaultArrayValue);//сохраняем запись уже без телефона
      } else {phoneMap.remove(nameByInput);}
      defaultArrayValue.addAll(defaultPhoneList);//преобразуем список в ArrayList
      phoneMap.put(nameByInput,defaultArrayValue);//сохраняем запись уже без телефона
      defaultPhoneList = getClearPhonesByName(inputName);//сохраняем список по имени
      defaultPhoneList.add(inputPhone);//добавляем к списку телефон
      defaultArrayValue.clear();
      defaultArrayValue.addAll(defaultPhoneList);//преобразуем список в ArrayList
      phoneMap.put(inputName,defaultArrayValue);//сохраняем запись уже c телефоном
      System.out.println("Контакт сохранен!");
    }

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        if ((name.matches("^[A-ZА-Я]{1}[a-zA-Zа-яА-Я0-9_-]*")) &&
            !(ValidationWord.wordToValidPhone(phone).equals("Mistake"))) {
            // если такой номер уже есть в списке, то перезаписать имя абонента
            String nameFromPhone = getClearNameByPhone(phone); //поиск имени соответствующему телефону
            Set<String> phoneFromName = getClearPhonesByName(name);//список телефонов соответствующих имени
            //может быть пять состояний пары:
            //1.телефон и имя в одной записи - просто возвращаем true
            //2.есть телефон, нет имени - из списка с телефоном удаляем телефон, пересохраняем запись с отредактированным списком,
             //вызываем метод ifMapEmpty
            //3.нет телефона, есть имя - к текущему имени добавляем еще телефон
            //4.есть и телефон и имя, но они в разных записях - из списка с телефоном удаляем телефон,
            // пересохраняем запись с отредактированным списком, к текущему имени добавляем еще телефон
            //5.нет телефона, нет имени - вызываем метод ifMapEmpty

            //1
            if (nameFromPhone.equals(phone)){return;}
            //2.есть телефон, нет имени
            if ((!nameFromPhone.equals("")) && (phoneFromName.isEmpty())){
             isPhoneNoName(phone,name,nameFromPhone);
             return;}
            //3. нет телефона, есть имя
            if ((nameFromPhone.equals("")) && (!phoneFromName.isEmpty())){
             noPhoneIsName(phone,name);
             return;}
            //4.есть и телефон и имя, но они в разных записях
            if ((!nameFromPhone.equals("")) && (!phoneFromName.isEmpty())
                && (!nameFromPhone.equals(phone))){
              isPhoneIsName(phone,name,nameFromPhone);
              return;}
            //5.нет телефона, нет имени
            if ((nameFromPhone.equals("")) && (phoneFromName.isEmpty())){
                this.ifMapEmpty(phone, name);
                return;}
        }
    }

    //метод нужен только для теста из проекта
    public String getNameByPhone(String phone) {
        String key = getClearNameByPhone(phone);
        if (!key.equals("")){//возвращаем из метода строку "Имя - Телефон"
          return key + " - " + phone;}
      // если контакт не найден - возвращаем пустую строку
        return "";
    }

    //метод выдает строку-имя, соответствующую определенному номеру телефона
    //рабочий метод, используемый и в классе и в main
    public String getClearNameByPhone (String phone){//возвращает только имя по телефону
      //перебор с помощью итератора
      Iterator<Entry<String, ArrayList<String>>> itr = phoneMap.entrySet().iterator();
      while(itr.hasNext()) {//цикл
        Map.Entry<String, ArrayList<String>> entry = itr.next();
        String key = entry.getKey();//получаем строку-ключ
        ArrayList<String> valueArrayList = entry.getValue(); //получаем значение в виде ArrayList
        if (valueArrayList.contains(phone)) {//если телефон есть в текущем ArrayList-значении
          //возвращаем из метода строку Имя
          return key;
        }
      }
      return "";
    }

    //метод нужен только для теста из проекта
    public TreeSet<String> getPhonesByName (String name) {
        TreeSet<String> phoneByName = new TreeSet<>();
        if (phoneMap.containsKey(name)){
            ArrayList<String> valueArrayList = phoneMap.get(name);//получаем значение в виде ArrayList
            for (int i = 0; i < valueArrayList.size(); i++){
                //добавляем строку в TreeSet в виде "Имя - Телефон"
                phoneByName.add(name + " - " + valueArrayList.get(i));}
        }
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        return phoneByName;
    }

    //метод выдает TreeSet, заполненный только телефонами, соответствующими определенному имени
    //рабочий метод, используемый и в классе и в main
    public TreeSet<String> getClearPhonesByName(String name){
      TreeSet<String> clearPhoneByName = new TreeSet<>();
      if (phoneMap.containsKey(name)){
        ArrayList<String> valueArrayList = phoneMap.get(name);//получаем значение в виде ArrayList
        for (int i = 0; i < valueArrayList.size(); i++){
          //добавляем строку в TreeSet
          clearPhoneByName.add(valueArrayList.get(i));}
      }
      return clearPhoneByName;
    }

    //составление списка всех телефонов в формате имя - телефон1, телефон2
    public Set<String> getAllContacts() {
        TreeSet<String> allPhoneBook = new TreeSet<>();
        //перебор с помощью итератора
        Iterator<Entry<String, ArrayList<String>>> itr = phoneMap.entrySet().iterator();
        while(itr.hasNext()) {
         Map.Entry<String, ArrayList<String>> entry =  itr.next();
         String key = entry.getKey();//получаем строку-ключ
         ArrayList<String> valueArrayList = entry.getValue(); //получаем значение в виде ArrayList
         String phoneComma = "";
         for (int i = 0; i < valueArrayList.size(); i++){
           phoneComma = phoneComma.concat(valueArrayList.get(i) + ", ");}
         phoneComma = phoneComma.substring(0,phoneComma.length()-2);//отрезаем от строки лишнее сзади
         allPhoneBook.add(key + " - " + phoneComma);//добавляем строку в TreeSet в виде "Имя - Телефон"
        }
        // если в телефонной книге нет контактов - вернется пустой TreeSet
        return allPhoneBook;
    }

}
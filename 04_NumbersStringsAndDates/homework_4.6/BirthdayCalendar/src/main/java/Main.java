import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 14;
        int month = 4;
        int year = 1976;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        int i = 0;//номер строки в отчете
        String dateString = "";//создаем пустую строку
        //создание формата вывода даты с русской локалью
        SimpleDateFormat myDateFormat = new SimpleDateFormat("dd.MM.yyyy - EEEE",new Locale("ru"));
        Calendar myBirthCalendar = GregorianCalendar.getInstance();//создаем новый календарь
        Date currentDate = myBirthCalendar.getTime();//текущая дата
        myBirthCalendar.set(year,(month-1),day);//устанавливаем календарь на нужную дату
        Date date = myBirthCalendar.getTime();//получаем дату из календаря
        while (!(date.after(currentDate))){//цикл по годам
        //добавляем к строке новую дату в установленном формате
        dateString = dateString.concat(i + " - " + myDateFormat.format(date) + System.lineSeparator());
        i++;//увеличиваем номер строки в отчете
        myBirthCalendar.add(Calendar.YEAR, 1);//перемещаем календарь на год вперед
        date = myBirthCalendar.getTime();//получаем дату из календаря
      }
        return dateString;//метод возвращает строку
    }
}

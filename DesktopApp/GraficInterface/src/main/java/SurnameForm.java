import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SurnameForm extends JFrame {

  //выделение памяти под элементы на форме
  private JLabel familyNameLabel;
  private JTextField familyNameField;
  private JLabel nameLabel;
  private JTextField nameField;
  private JLabel patronymicLabel;
  private JTextField patronymicField;
  private JButton btn1;
  private int formHeigh = 120;//начальная высота формы

  public SurnameForm(){//конструктор класса
    super.setBounds(100,100,300, formHeigh);//первоначальное положение формы и размеры
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//по закрытию формы закрывается приложение

    Container container = super.getContentPane();//создание контейнера для элементов
    container.setLayout(new GridBagLayout());//все будет расположено по сетке

    //инициализация элементов
    familyNameLabel = new JLabel("Фамилия");
    familyNameField = new JTextField("", 10);
    nameLabel = new JLabel("Имя");
    nameField = new JTextField("", 10);
    patronymicLabel = new JLabel("Отчество");
    patronymicField = new JTextField("", 10);
    btn1 = new JButton("Collapse");

    //создание объекта настройки для полей
    //gridx, gridy - координаты ячейки (нумерация), RELATIVE - следующая в обработке
    //gridwidth, gridheight - сколько ячеек занимает, REMAINDER - весь ряд или столбец
    //weightx, weighty - вес ячейки от 0 до 1 (дробный), по умолчанию 0. Приоритет кому больше достанется места
    //anchor - расположение элемента в ячейке, fill - заполнение
    //insets - отступы от границ ячейки, это объект, поэтому new
    GridBagConstraints gbcTextFields = new GridBagConstraints(GridBagConstraints.RELATIVE,GridBagConstraints.RELATIVE,
        GridBagConstraints.REMAINDER,1, 1.0f,1.0f,
        GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,
        new Insets(0,0,0,2),0,0);

    //создание объекта настройки для надписей на форме
    GridBagConstraints gbcLabels = new GridBagConstraints(GridBagConstraints.RELATIVE,GridBagConstraints.RELATIVE,
        1,1, 0.0f,0.0f,
        GridBagConstraints.EAST,GridBagConstraints.NONE,
        new Insets(0,4,0,4),0,0);

    //создание объекта настройки для кнопки
    GridBagConstraints gbcButton = new GridBagConstraints(1,GridBagConstraints.RELATIVE,
        1,1, 0.0f,0.0f,
        GridBagConstraints.CENTER,GridBagConstraints.CENTER,
        new Insets(0,0,0,0),0,0);

    //добавление элементов в контейнер с соответствующими настройками дизайна
    container.add(familyNameLabel, gbcLabels);
    container.add(familyNameField, gbcTextFields);
    container.add(nameLabel, gbcLabels);
    container.add(nameField,gbcTextFields);
    container.add(patronymicLabel, gbcLabels);
    container.add(patronymicField,gbcTextFields);
    container.add(btn1, gbcButton);

    super.setLocationRelativeTo(null);//показ формы по центру экрана

    ActionListener actionListener = new MyActionListener();//создание экземпляра класса-обработчика

    btn1.addActionListener(actionListener);//привязка экземпляра класса-обработчика к кнопке
  }

  public class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      //если надпись на кнопке "Collapse", все поля заполнены и соответствуют правилам
      if ((btn1.getText().equals("Collapse") && CanCollapse())){
        //в первое поле собирается строка со всех полей
        familyNameField.setText(familyNameField.getText().strip() + " " + nameField.getText().strip() + " " +
            patronymicField.getText().strip());
      familyNameLabel.setText("Ф.И.О.");//меняем надпись около первого текстового поля
      //скрываем лишние элементы
      nameLabel.setVisible(false);
      nameField.setVisible(false);
      patronymicLabel.setVisible(false);
      patronymicField.setVisible(false);
      btn1.setText("Expand");//меняем надпись на кнопке
      //меняем высоту формы
      SurnameForm.this.setSize(SurnameForm.this.getWidth(),formHeigh - nameField.getHeight()
          - patronymicField.getHeight());}
      else  //если надпись на кнопке "Expand", в поле три значения через пробелы и они соответствуют правилам
        if (btn1.getText().equals("Expand") && CanExpand()){
        //делаем массив строк из текста поля ФИО
        String[] familyStrArray = familyNameField.getText().strip().split("\\s+");
        //заполняем поля элементами массива
        familyNameField.setText(familyStrArray[0].strip());
        nameField.setText(familyStrArray[1].strip());
        patronymicField.setText(familyStrArray[0].strip());
        familyNameLabel.setText("Фамилия");
        //показываем скрытые элементы
        nameLabel.setVisible(true);
        nameField.setVisible(true);
        patronymicLabel.setVisible(true);
        patronymicField.setVisible(true);
        //меняем текст на кнопке
        btn1.setText("Collapse");
        //меняем высоту формы
        SurnameForm.this.setSize(SurnameForm.this.getWidth(),formHeigh);}
    }
  }

  //метод показа окна ошибки
  public void ShowMessage (String message, String title){
    JOptionPane.showMessageDialog(this,
        message,
        title,
        JOptionPane.WARNING_MESSAGE);
  }

  public Boolean CanCollapse(){
    //проверка введена ли фамилия
    if (familyNameField.getText().strip().equals("")) {
      ShowMessage("Не введена фамилия", "Отсутствуют данные");
      return false;
    }
    //проверка формата заполнения фамилии
    if (!familyNameField.getText().strip().matches("[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+(\\-[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+)?")){
      ShowMessage("Неверный формат фамилии. Введите кириллицей в формате ЗаглавнаяСтрочные "
          + "или ЗаглавнаяСтрочные-ЗаглавнаяСтрочные", "Неверный формат фамилии");
      return false;
    }
    //проверка введено ли имя
    if (nameField.getText().strip().equals("")) {
      ShowMessage("Не введено имя", "Отсутствуют данные");
      return false;
    }
    //проверка формата заполнения имени
    if (!nameField.getText().strip().matches("[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+(\\-[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+)?")){
      ShowMessage("Неверный формат имени. Введите кириллицей в формате ЗаглавнаяСтрочные "
          + "или ЗаглавнаяСтрочные-ЗаглавнаяСтрочные", "Неверный формат имени");
      return false;
    }
    //проверка введено ли отчество
    if (patronymicField.getText().strip().equals("")) {
      ShowMessage("Не введено отчество", "Отсутствуют данные");
      return false;
    }
    //проверка формата заполнения отчества
    if (!patronymicField.getText().strip().matches("[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+")){
      ShowMessage("Неверный формат отчества. Введите кириллицей в формате ЗаглавнаяСтрочные", "Неверный формат отчества");
      return false;
    }
    return true;
  }

  public Boolean CanExpand(){
    //делаем массив строк из текста поля ФИО
    String[] strArray = familyNameField.getText().strip().split("\\s+");
    //проверка количества элементов в ФИО
    if (strArray.length < 3){
      ShowMessage("Введены не все данные", "Недостаточно данных");
      return false;
    }
    if (strArray.length > 3){
      ShowMessage("Введены лишние данные", "Переизбыток данных");
      return false;
    }
    //проверка формата заполнения фамилии
    if (!strArray[0].strip().matches("[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+(\\-[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+)?")){
      ShowMessage(strArray[0] + " Неверный формат фамилии. Введите кириллицей в формате ЗаглавнаяСтрочные "
          + "или ЗаглавнаяСтрочные-ЗаглавнаяСтрочные", "Неверный формат фамилии");
      return false;
    }
    //проверка формата заполнения имени
    if (!strArray[1].strip().matches("[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+(\\-[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+)?")){
      ShowMessage(strArray[1] + " Неверный формат имени. Введите кириллицей в формате ЗаглавнаяСтрочные "
          + "или ЗаглавнаяСтрочные-ЗаглавнаяСтрочные", "Неверный формат имени");
      return false;
    }
    //проверка формата заполнения отчества
    if (!strArray[2].strip().matches("[А-Я&&[^ЪЬЙЫ]]{1}[а-я]+")){
      ShowMessage(strArray[2] + " Неверный формат отчества. Введите кириллицей в формате ЗаглавнаяСтрочные", "Неверный формат отчества");
      return false;
    }
    return true;
  }

}
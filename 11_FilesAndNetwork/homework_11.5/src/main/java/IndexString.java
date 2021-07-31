public class IndexString implements Comparable<IndexString> {

  public String getValue() {
    return value;
  }

  public IndexString(String value) {
    this.value = value;
  }

  private String value;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    IndexString that = (IndexString) o;

    return this.getValue().equals(((IndexString) o).getValue());
  }

  @Override
  public int hashCode() {
    return getValue() != null ? getValue().hashCode() : 0;
  }

  @Override
  public int compareTo(IndexString o) {
      //System.out.println("compareTo - " + value + " - " + o.value);
      //сравнение чисел между собой
      if (value.matches("\\d+") && o.value.matches("\\d+"))
      {return Integer.compare(Integer.parseInt(value), Integer.parseInt(o.value));}
      //сравнение число-буквенных элементов с числами
      if (value.matches("\\d+[a-zA-Z]+") && o.value.matches("\\d+")) {
        String str = value.replaceAll("[a-zA-Z]","");
        if (Integer.parseInt(str) >= Integer.parseInt(o.value)) {return 1;}
        return -1;
      }
      //сравнение чисел с число-буквенными элементами
      if (value.matches("\\d+") && o.value.matches("\\d+[a-zA-Z]+")) {
        String str = o.value.replaceAll("[a-zA-Z]","");
        if (Integer.parseInt(str) >= Integer.parseInt(value)) {return -1;}
        return 1;
      }
      //сравнение число-буквенных элементов с буквено-численными - 11A - D1
      if (value.matches("\\d+[a-zA-Z]+") && o.value.matches("[a-zA-Z]+\\d+")){return -1;}
      //сравнение буквено-численных элементов с число-буквенными - D1 - 11A
      if (o.value.matches("\\d+[a-zA-Z]+") && value.matches("[a-zA-Z]+\\d+")){return 1;}
      //сравнение число-буквенных элементов
      if (value.matches("[a-zA-Z]+\\d+") && o.value.matches("\\d+")) {return 1;}
      if (value.matches("\\d+") && o.value.matches("[a-zA-Z]+\\d+")) {return -1;}
      if (value.matches("[a-zA-Z]+\\d+") && o.value.matches("[a-zA-Z]+\\d+")) {
        String str1 = value.replaceAll("[a-zA-Z]","");
        String str2 = o.value.replaceAll("[a-zA-Z]","");
        return Integer.compare(Integer.parseInt(str1), Integer.parseInt(str2));}
      return 0;
    }
}

public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse (String[] strings){
        String varString; //просто строковая переменная для временного хранения значения элемента масива
        //половина длины массива, чтоб каждый раз не пересчитывать
        //дробная часть просто отбрасывается за ненужностью - если в массиве нечетное количество элементов,
        //средний элемент не имеет смысла обрабатывать
        int halfLength = strings.length / 2;
        for (int i = 0; i < halfLength; i++){//перебор элементов
            varString = strings[i];//закидываем значение элемента массива "с начала" в переменную
            //элементу "с начала" присваиваем значение элемента "с конца"
            strings[i] = strings[(strings.length - i)-1];
            //элементу "с конца" присваиваем значение временной переменной
            strings[(strings.length - i)-1] = varString;
        }
        return strings;}

}
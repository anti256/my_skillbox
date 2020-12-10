public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {
        char[][] charMassiv = new char[size][size];//объявляем массив
        for (int i = 0; i < size; i++){//перебор столбцов
            for (int j = 0 ; j <size; j++){//перебор строк в столбце
            charMassiv[i][j] = ' ';//заполняем всю строку пробелами
        }
            charMassiv[i][i] = symbol;//диагональ слева-направо
            charMassiv[i][size - 1 - i] = symbol;//диагональ справа-налево
        }
        //Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]

        return charMassiv;
    }
}

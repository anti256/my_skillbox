public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
     float[] massiv = new float[patientsCount];//объявление массива
     for (int i = 0; i<massiv.length; i++){//перебор элементов массива
         massiv[i] = (float)(8f * Math.random() + 32f);//задание значений элементов массива значениями от 32 до 40
     }

        return massiv;
    }

    public static String getReport(float[] temperatureData) {
       float middleTemperature = 0.0f;//переменная средней температуры
       int healthPatientsCount = 0;//переменная количества здоровых пациентв
       String tempLine = "";//строка температур
       for (int i = 0; i < temperatureData.length; i++){
         middleTemperature += temperatureData[i];//суммирование температур
         tempLine = tempLine.concat(temperatureData[i] + " ");//наполнение строки температур
         //если температура не меньше 36,2 и не больше 36,9, увеличиваем количество здоровых
         if (temperatureData[i] >= 36.2f && temperatureData[i] <= 36.9f) {healthPatientsCount++;}
       }
       middleTemperature /= temperatureData.length;//вычисление средней температуры
       tempLine = tempLine.strip();//из строки температур убираем лишние пробелы по краям
       String report =//строка отчета
                "Температуры пациентов: " + tempLine +
                        "\nСредняя температура: " + String.format("%.1f",middleTemperature) +
                        "\nКоличество здоровых: " + healthPatientsCount;

        return report;
    }
}

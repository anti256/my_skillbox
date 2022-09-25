import java.io.IOException;

public class RedisTest {

    public static void main(String[] args) throws IOException {
        createLine.create();//создание очереди в базе данных
        Storage redisStorage = new Storage();//создание экземпляра базы
        redisStorage.init();//инициализация и подключение к redis

        int flagRandom = 0; //флаг было ли из 10 куплено продвижение
        int schetchik = 0;//счетчик до 10
        while (true) {//бесконечный цикл
            schetchik++;//увеличение счетчика
            int minIndex = redisStorage.minCount();//текущий минимальный индекс
            if((flagRandom == 0) & (minIndex != 1)){//если в очереди из десяти не было продвижения и если это
                // не самый первый элемент, то разрешается возможность продвижения
                Double rand = 100 * Math.random();//вероятность продвижения
                if (rand < 10) {//если вероятность продвижения меньше 10%
                    int delta = (int) ((10 - schetchik) * Math.random());//расчет приращения индекса для продвижения
                    //следующие строки - перемещение продвинутого в очереди
                    redisStorage.line.put(minIndex -1, redisStorage.line.get(minIndex + delta));
                    //показ на экране об оплате продвижения
                    System.out.println("> Пользователь " + redisStorage.line.remove(minIndex + delta) +
                            " оплатил платную услугу");
                    minIndex--;//откат минимального индекса, т.к. продвинутый был вставлен перед очередью
                    flagRandom = 1;//установка флага, чтоб запретить возможность продвижения
                }
            }
            //показ первого в очереди
            System.out.println("— На главной странице показываем пользователя " +
                    redisStorage.line.get(minIndex));
            //следующие две строчки - перемещение первого в очереди в конец по условию задания
            String val = redisStorage.line.remove(minIndex);
            redisStorage.line.put(redisStorage.maxCount() + 1, val);

            if (schetchik == 10) {//если перебрали десять пользователей
                schetchik = 0;//обнуляем счетчик пользователей
                flagRandom = 0;//устанавливаем флаг возможности продвинуться
                if (minIndex > 100) {//чтобы в какой-то момент индекс не перевалил за границу целых чисел,
                    //индексы очереди уменьшаем до от нуля (можно вставить лубое число, меньше диапазона целых чисел)
                    redisStorage.reCount();
                }
            }

        }






    }


}
//replace(V oldObject, V newObject)
//Rename current object key to newName

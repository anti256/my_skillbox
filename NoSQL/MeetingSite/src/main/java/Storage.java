import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import static java.lang.System.out;

public class Storage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с RMap
    RMap<Integer, String> line;


    void init() {//инициализация и подключение
        Config config = new Config();//создание объекта конфигурации
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");//настройка объекта конфигурации ip-адресом
        try {
            redisson = Redisson.create(config);//подключение Redisson к Redis
            line = redisson.getMap("loveLine");//наполнение map из redis-набора loveLine
        } catch (RedisConnectionException Exc) {//обработка ошибки подключения к redis
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
    }

    Integer minCount(){//нахождение минимального индекса
        return (Integer) line.keySet().toArray()[0];
    }

    Integer maxCount(){//нахождение максимального индекса
        return (Integer) line.keySet().toArray()[line.size()-1];
    }

    void reCount(){//перепись ключей map с началом от нуля
        int min = minCount();//минимальный ключ
        int max = maxCount();//максимальный ключ
        //в цикле берем элемент, создаем такой же с нужным индексом, удаляем взятый
        for (int i = min; i < max + 1; i++) {
            int r = i - min + 1;
            line.put(r, line.get(i));
            line.remove(i);
        }
    }

}

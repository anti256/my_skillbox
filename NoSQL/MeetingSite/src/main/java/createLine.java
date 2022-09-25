import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

import static java.lang.System.out;

public class createLine {

    public static void create () throws IOException {
        //создание объекта конфигурации из файла yaml
        Config config = Config.fromYAML(new File("./NoSQL/MeetingSite/src/main/resources/singleNodeConfig.yaml"));
        try {
            RedissonClient redisson = Redisson.create(config);//подключение Redisson к Redis
            //создание map на основе redis-набора loveLine
            RMap <Integer, String> redisMap = redisson.getMap("loveLine");
            redisMap.clear();//очистка набора, если он был создан до этого
            for (int i = 1; i < 21; i++) {
                redisMap.fastPut(i, String.valueOf(i));//наполнение набора
            }
            redisson.shutdown();//остановка клиента для чистоты эксперимента
        } catch (RedisConnectionException Exc) {//обработка ошибки подключения к redis
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }

    }

}

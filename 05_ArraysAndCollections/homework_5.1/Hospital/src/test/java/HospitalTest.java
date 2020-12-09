import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Операции с массивами - получение среднего значения")
class HospitalTest {

    @BeforeAll
    static void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    @DisplayName("Генерируемый массив не содержит температуры за рамками заданных предельных значений")
    void testCollectDataContainsCorrectEntries() {
        int wrongEntriesCount = 0;
        float[] testHospitalData = Hospital.generatePatientsTemperatures(30);
        for (float entry : testHospitalData) {
            if (entry < 32f || entry > 40f)
                wrongEntriesCount++;
        }
        assertEquals(0, wrongEntriesCount, "Температуры выходят за предельные значения");
    }

    @Test
    @DisplayName("Массив температур содержит 30 значений температур")
    void testTemperatureArrayLenght(){
        assertEquals(30, Hospital.generatePatientsTemperatures(30).length);
    }

    @Test
    @DisplayName("Метод корректно считает среднюю температуру и количество здоровых пациентов")
    void testGetReport() {
        final float[] testHospitalData = new float[]{
                32.1F, 33.1F, 32.5F, 33.5F, 34.5F, 36.5F, 38.5F, 39.5F, 33.3F, 32.7F,
                36.9F, 36.5F, 34.3F, 37.5F, 32.5F, 32.5F, 32.4F, 34.5F, 35.4F, 32.5F,
                34.5F, 39.4F, 32.5F, 36.5F, 36.4F, 39.6F, 37.5F, 32.5F, 37.5F, 39.4F
        };

        String temperatures = String.join(" ",
                IntStream.range(0, testHospitalData.length)
                        .mapToObj(i -> String.valueOf(testHospitalData[i])).toArray(String[]::new));

        String expected = "Температуры пациентов: " + temperatures + System.lineSeparator() +
                "Средняя температура: 35.2" + System.lineSeparator() +
                "Количество здоровых: 5";

        assertEquals(expected, Hospital.getReport(testHospitalData)
                        .replaceAll("\r\n", "\n")
                        .replaceAll("\n", System.lineSeparator()),
                "Проверьте формат вывода, округление средней температуры и количество здоровых пациентов");
    }
}
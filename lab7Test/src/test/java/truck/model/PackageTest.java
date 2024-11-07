package truck.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageTest {

    private Package package1;

    @BeforeEach
    void setUp() {
        // Ініціалізуємо об'єкт Package перед кожним тестом
        package1 = new Package("Пластик", 500, 1.2);
    }

    @Test
    void testGetMaterial() {
        // Перевіряємо, чи повертає гетер правильний матеріал
        assertEquals("Пластик", package1.getMaterial(), "Матеріал упаковки має бути 'Пластик'");
    }

    @Test
    void testGetWeight() {
        // Перевіряємо, чи повертає гетер правильну вагу
        assertEquals(500, package1.getWeight(), "Вага упаковки має бути 500 грамів");
    }

    @Test
    void testGetVolume() {
        // Перевіряємо, чи повертає гетер правильний об'єм
        assertEquals(1.2, package1.getVolume(), "Об'єм упаковки має бути 1.2 літра");
    }

    @Test
    void testToString() {
        // Перевіряємо, чи правильно працює метод toString
        String expected = "Упаковка: матеріал = Пластик, вага = 500,00 г, об'єм = 1,20 л";
        assertEquals(expected, package1.toString(), "Метод toString має повертати правильний рядок");
    }
}

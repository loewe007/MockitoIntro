import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CarTest {
    @Mock
    private Car car;
    private String MANUFACTURER = "audi";
    private String NUMBER = "BL-12345";
    private String OWNER = "ass";

    @BeforeEach
    void init() {
        //car = mock(Car.class);
        car = new Car(MANUFACTURER, NUMBER, OWNER);
    }

    @Test
    void Car_Created_ListOunersMustHaveOneElemet() {
        //assertTrue(car.getOwners().contains(OWNER));
        assertArrayEquals(new String[]{OWNER}, car.getOwners().toArray());
    }

    @Test
    void Car_CreatedAndSetOwner_ListOwnersMustHaveTwoElemets() {
        String newOwner = "NewOwner";
        car.setOwner(newOwner);
        //assertEquals(2, car.getOwners().size());
        //assertTrue(car.getOwners().contains(newOwner));
        assertArrayEquals(new String[]{OWNER, newOwner}, car.getOwners().toArray());
    }

    @Test
    void Car_ManufacturerIsNull_IllegalArgumentException() {
        assertEquals("manufactrer can not by null",
                assertThrows(IllegalArgumentException.class, () -> {
                    new Car(null, NUMBER, OWNER);
                }).getMessage());
    }

    @Test
    void Car_NumberIsNull_IllegalArgumentException() {
        //assertEquals("number can not by null",
        //        assertThrows(IllegalArgumentException.class,() -> {new Car(MANUFACTURER, null, OWNER);}).getMessage());
        assertThatThrownBy(() -> {
            new Car(MANUFACTURER, null, OWNER);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("number can not by null");
    }

    @Test
    void Car_OwnerIsNull_IllegalArgumentException() {
        assertEquals("owner can not by null",
                assertThrows(IllegalArgumentException.class, () -> {
                    new Car(MANUFACTURER, NUMBER, null);
                }).getMessage());
    }

    @Test
    void getManufactrer_Created_MustBeCorrect() {
        assertEquals(MANUFACTURER, car.getManufactrer());
    }

    @Test
    void getNumber_Created_MustBeCorrect() {
        assertEquals(NUMBER, car.getNumber());
    }

    @Test
    void setManufactrer_ChangeManufactrer_NewManufactrer() {
        String manufactrer = "name";
        car.setManufactrer(manufactrer);
        System.out.println(String.format("name: %s", car));
        assertEquals(manufactrer, car.getManufactrer());
    }

    @Test
    void setNumber_ChangeNumber_NewNumber() {
        String number = "1111";
        car.setNumber(number);
        assertEquals(number, car.getNumber());
    }

    @Test
    void getOwner_Created_MustBeCorrect() {
        assertEquals(OWNER, car.getOwner());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "newOwner"})
    void setOwner_ChangeOwner_NewOwner(String newOwner) {
        car.setOwner(newOwner);
        //assertEquals(newOwner, car.getOwner());
        assertThat(newOwner).isEqualTo(car.getOwner());
    }

    @AfterEach
    void deInit() {
        car = null;
    }

    @Test
    void testReflectionPrivateMethod() {
        Class<? extends Car> c = car.getClass();

        //Field[] fields = c.getDeclaredFields();
        //Arrays.stream(fields).forEach(e -> System.out.println(e));

        //Method[] methods = c.getDeclaredMethods();
        //Arrays.stream(methods).forEach(e -> System.out.println(e));

        try {
            Method addOwner = c.getDeclaredMethod("addOwner", String.class);
            System.out.println(addOwner);
            addOwner.setAccessible(true);
            addOwner.invoke(car, "asdadf");
            c.getDeclaredField("owners");
            car.getOwners().stream().forEach(e -> System.out.println(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
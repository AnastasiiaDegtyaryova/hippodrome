import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

//Arrange, Act, Assert
class HorseTest {

    @Test
    void constructor_NullNameParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Name cannot be null.";
        double speed = 10;
        double distance = 5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
        }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n", "\r\n"})
    void constructor_BlankNameParamPassed_ThrowsIllegalArgumentException(String blankName) {
        String expectedMessage = "Name cannot be blank.";
        double speed = 10;
        double distance = 5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Speed cannot be negative.";
        String name = "TestHorse";
        double speed = -1;
        double distance = 5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Distance cannot be negative.";
        String name = "TestHorse";
        double speed = 10;
        double distance = -5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getName_ReturnsNamePassedToConstructor() {
        String expectedName = "TestHorse";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(expectedName, speed, distance);

        String actualName = horse.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void getSpeed_ReturnsSpeedPassedToConstructor() {
        String name = "TestHorse";
        double expectedSpeed = 1;
        double distance = 2;
        Horse horse = new Horse(name, expectedSpeed, distance);

        double actualSpeed = horse.getSpeed();

        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getDistance_ReturnsDistancePassedToConstructor() {
        String name = "TestHorse";
        double speed = 1;
        double expectedDistance = 10;
        Horse horse = new Horse(name, speed, expectedDistance);

        double actualDistance = horse.getDistance();

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void getDistance_ReturnsZeroWhenUsingTwoParameterConstructor() {
        String name = "TestHorse";
        double speed = 1;
        Horse horse = new Horse(name, speed);

        double actualDistance = horse.getDistance();

        assertEquals(0.0, actualDistance);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void move_UpdatesDistanceBasedOnSpeedAndRandomFactor(double fakeRandomValue) {
        String name = "TestHorse";
        double speed = 10;
        double distance = 1;
        double min = 0.2;
        double max = 0.9;
        double expectedDistance = distance + speed * fakeRandomValue;
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
            mockedHorse.verify(() -> Horse.getRandomDouble(min, max));
        }
    }
}


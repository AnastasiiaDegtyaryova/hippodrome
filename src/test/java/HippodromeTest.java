import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void constructor_NullListParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Horses cannot be null.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Horses cannot be empty.";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getHorses_ReturnsSameHorsesListAsPassedToConstructor() {
        List<Horse> horses = IntStream.range(0, 30)
                .mapToObj(i -> new Horse("Horse " + i, 10))
                .collect(Collectors.toList());

        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(horses, hippodrome.getHorses());
        assertEquals("Horse 10",hippodrome.getHorses().get(10).getName());
    }

    @Test
    void move_CallsMoveMethodOnAllHorses() {
        List<Horse> horses = IntStream.range(0, 50)
                .mapToObj(i -> mock(Horse.class))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        horses.forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinner_ReturnsHorseWithGreatestDistance() {
        Horse horse1 = new Horse("Horse 1", 10, 10);
        Horse horse2 = new Horse("Horse 2", 10, 15);
        Horse horse3 = new Horse("Horse 3", 10, 12);
        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse2, hippodrome.getWinner());
    }
}

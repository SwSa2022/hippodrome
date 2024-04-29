import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void constructor_isNull_IllegalArgumentExceptionThrown() {
        assertThrows(IllegalArgumentException.class,()-> new Hippodrome(null));
    }
    @Test
    void constructor_isEmpty_IllegalArgumentExceptionThrown() {
        List<Horse> emptyHorses = Collections.emptyList();
        assertThrows(IllegalArgumentException.class,()-> new Hippodrome(emptyHorses));
    }
    @Test
    void constructor_isNull_ErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> new Hippodrome(null));
        assertTrue(exception.getMessage().contains("Horses cannot be null."));
    }
    @Test
    void constructor_isEmpty_ErrorMessage() {
        List<Horse> emptyHorses = Collections.emptyList();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> new Hippodrome(emptyHorses));
        assertTrue(exception.getMessage().contains("Horses cannot be empty."));
    }

    private List<Horse> createListOf30DifferentHorses() {
        double j = Math.random() * 2.89;
        List<Horse> DifferentHorses30 = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            DifferentHorses30.add(new Horse("Horse " + i + " ", + j));
        }
        return DifferentHorses30;
    }
    @Test
    void getHorses_ReturnSameList() {
        List<Horse> horses = createListOf30DifferentHorses();
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
                // Get the horse with the largest distance value:
        Horse expectedWinner = horses.stream().max(Comparator.comparing(Horse::getDistance)).orElse(null);

        assertEquals(expectedWinner,winner);
    }

    private List<Horse> createMockListOfHorses(int numHorses) {
        List<Horse> horsesMockList = new ArrayList<>();
        for (int i = 0; i < numHorses; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            doNothing().when(horseMock).move();
            horsesMockList.add(horseMock);
        }
        return horsesMockList;
    }
    @Test
    void move_CallsMoveOnAllHorses() {
        List<Horse> horseMock = createMockListOfHorses(50);
        Hippodrome hippodrome = new Hippodrome(horseMock);
        hippodrome.move();
        for(Horse horse: horseMock){
            verify(horse).move();
        }
    }

    @Test
    void getWinner_ReturnHorseWithHighestDistance() {
        List<Horse> horses = createListOf30DifferentHorses();
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertEquals(horses,winner);
    }
}
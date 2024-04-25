import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

    public class HorseTest {
//        @Test
//        void constructor_nullName_IllegalArgumentExceptionThrown() {
//            try {
//                new Horse(null, 10.0, 100.0);
//                fail("Expected IllegalArgumentException was not thrown");
//            } catch (IllegalArgumentException e) {
//                // Проверяем, что исключение было выброшено
//                assertNotNull(e);
//            }
//        }

        @Test
        void constructor_nullName_IllegalArgumentExceptionThrown() {
            assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 100.0));
        }

        @Test
        void constructor_nullName_exceptionMessage() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 100.0));
            assertEquals("Name cannot be null.", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "\t", "\n"}) // Different types of whitespace
        void constructor_blankName_IllegalArgumentExceptionThrown(String name) {
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10.0, 100.0));
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "\t", "\n"}) // Different types of whitespace
        void constructor_blankName_exceptionMessage(String name) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10.0, 100.0));
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

        @Test
        void constructor_negativeSpeed_IllegalArgumentExceptionThrown() {
            assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", -10.0, 100.0));
        }

        @Test
        void constructor_negativeSpeed_exceptionMessage() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", -10.0, 100.0));
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }

        @Test
        void constructor_negativeDistance_IllegalArgumentExceptionThrown() {
            assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", 10.0, -100.0));
        }

        @Test
        void constructor_negativeDistance_exceptionMessage() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", 10.0, -100.0));
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }

        @Test
        void getName_returnsNamePassedToConstructor() {
            Horse horse = new Horse("Spirit", 10.0, 100.0);
            assertEquals("Spirit", horse.getName());
        }

        @Test
        void getSpeed_returnsSpeedPassedToConstructor() {
            Horse horse = new Horse("Spirit", 10.0, 100.0);
            assertEquals(10.0, horse.getSpeed());
        }

        @Test
        void getDistance_returnsDistancePassedToConstructor() {
            Horse horse = new Horse("Spirit", 10.0, 100.0);
            assertEquals(100.0, horse.getDistance());
        }

        @Test
        void getDistance_constructorWithTwoParams_returnsZero() {
            Horse horse = new Horse("Spirit", 10.0);
            assertEquals(0.0, horse.getDistance());
        }

        @Test
        void move_callsGetRandomDouble() {
            try (MockedStatic<Horse> mockedStaticHorse = mockStatic(Horse.class)) {
                mockedStaticHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
                Horse horse = new Horse("Spirit", 10.0, 100.0);
                horse.move();
                mockedStaticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            }
        }

        @Test
        void move_correctlyUpdatesDistance() {
            try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
                mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
                Horse horse = new Horse("Spirit", 10.0, 100.0);
                horse.move();
                assertEquals(105.0, horse.getDistance());
            }
        }
    }
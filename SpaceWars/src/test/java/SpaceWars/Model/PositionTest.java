package SpaceWars.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import SpaceWars.Model.Position;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {
    private Position position;
    private int moveSize;

    @BeforeEach
    void setUp() {
        position = new Position(10, 10);
        moveSize = 1;
    }

    @AfterEach
    void cleanUp() {
        position = null;
    }

    @Test
    void getUp() {
        assertEquals(10 + moveSize, position.getUp().getY());
    }

    @Test
    void getDown() {
        assertEquals(10 - moveSize, position.getDown().getY());
    }

    @Test
    void getX() {
        assertEquals(10 , position.getX());
    }

    @Test
    void getY() {
        assertEquals(10 , position.getY());
    }

    @Test
    public void equalsDifferentObjects() {

        Random random = new Random();
        int x = random.nextInt(100 - 10) + 10;
        int y = random.nextInt(50 - 10) + 10;
        Position first  = new Position(x, y);
        Position second = new Position(x, y);

        assertEquals(first, second);

    }

    @Test
    public void equalsSameObject() {

        Random random = new Random();
        int x = random.nextInt(100 - 10) + 10;
        int y = random.nextInt(50 - 10) + 10;
        Position first = new Position(x, y);

        assertEquals(first, first);

    }

    @Test
    public void equalsNullObject() {

        Random random = new Random();
        int x = random.nextInt(100 - 10) + 10;
        int y = random.nextInt(50 - 10) + 10;
        Position first = new Position(x, y);

        assertNotEquals(first, null);

    }
}
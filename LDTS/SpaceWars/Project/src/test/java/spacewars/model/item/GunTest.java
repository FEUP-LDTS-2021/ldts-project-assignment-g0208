package spacewars.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GunTest {
    private Gun gun;

    @BeforeEach
    void setUp() {
        gun = new Gun(1, 2);
    }

    @Test
    void getDamage() {
        assertEquals(gun.getDamage(), 1);
    }

    @Test
    void getSpeed() {
        assertEquals(gun.getSpeed(), 2);
    }


}

package spacewars.model.element;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spacewars.model.Position;
import spacewars.model.item.Gun;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    private Player player;
    private Gun playerGun;

    @BeforeEach
    void setUp() {
        playerGun = Mockito.mock(Gun.class);
        player = new Player(new Position(2, 6), Arrays.asList("#FFFFFF", "#000000"), playerGun, 10);
    }

    @Test
    void getGun() {
        assertEquals(player.getGun(), playerGun);
    }

    @Test
    void setGun() {
        Gun newGun = Mockito.mock(Gun.class);
        player.setGun(newGun);
        assertEquals(player.getGun(), newGun);
        assertNotEquals(player.getGun(), playerGun);
    }

    @Test
    void getEnergy() {
        assertEquals(player.getEnergy(), 10);
    }

    @Test
    void setEnergy() {
        player.setEnergy(20);
        assertEquals(player.getEnergy(), player.getEnergy());
        player.setEnergy(3);
        assertEquals(player.getEnergy(), 3);
    }

    @Test
    void getMaxEnergy() {
        assertEquals(player.getMaxEnergy(), player.getEnergy());
        player.setEnergy(3);
        assertEquals(player.getMaxEnergy(), 10);
    }

    @Test
    void changeEnergy() {
        player.changeEnergy(+3);
        assertEquals(player.getMaxEnergy(), player.getEnergy());
        player.changeEnergy(-2);
        assertEquals(player.getEnergy(), 8);
    }

    @Test
    void defaultColor() {
        player.setDefaultColor();
        assertEquals(player.getColor(), "#FFDB58");
    }

    @Test
    void getSpeed() {
        assertEquals(player.getSpeed(), 1);
    }

}
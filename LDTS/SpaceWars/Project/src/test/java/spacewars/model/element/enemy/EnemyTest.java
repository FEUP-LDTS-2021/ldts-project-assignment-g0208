package spacewars.model.element.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spacewars.model.item.Gun;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setUp(){
        this.enemy = Mockito.mock(Enemy.class);
        Gun enemyGun = Mockito.mock(Gun.class);

        Mockito.doCallRealMethod().when(this.enemy).updateColor();
        Mockito.doCallRealMethod().when(this.enemy).getColor();
        Mockito.doCallRealMethod().when(this.enemy).setColors(Mockito.anyList());
        this.enemy.setColors(Arrays.asList("#FF1122", "#2211FF"));

        Mockito.doCallRealMethod().when(this.enemy).setGun(enemyGun);
        this.enemy.setGun(enemyGun);

        Mockito.doCallRealMethod().when(this.enemy).setEnergy(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).getEnergy();

        Mockito.doCallRealMethod().when(this.enemy).setLastShot(Mockito.anyDouble());
        Mockito.doCallRealMethod().when(this.enemy).getLastShot();

        Mockito.doCallRealMethod().when(this.enemy).setSpeed(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).getSpeed();


    }

    @Test
    void setEnergy() {
        enemy.setEnergy(20);
        assertEquals(20,enemy.getEnergy());
    }

    @Test
    void setLastShot() {
        this.enemy.setLastShot(123);
        assertEquals(this.enemy.getLastShot(), 123);
    }

    @Test
    void energy() {
        int energy = 5;
        assertEquals(this.enemy.getEnergy(), 0);
        this.enemy.setEnergy(energy);
        assertEquals(this.enemy.getEnergy(), energy);
    }

    @Test
    void speed() {
        int speed = 7;
        assertEquals(this.enemy.getSpeed(), 0);
        this.enemy.setSpeed(speed);
        assertEquals(this.enemy.getSpeed(), speed);
    }

    @Test
    void updateColor() {
        int energy = 10;
        this.enemy.setEnergy(energy);
        this.enemy.updateColor();
        assertEquals(this.enemy.getColor(), "#FF1122");
        this.enemy.setEnergy(energy / 4);
        this.enemy.updateColor();
        assertEquals(this.enemy.getColor(), "#2211FF");
    }
}

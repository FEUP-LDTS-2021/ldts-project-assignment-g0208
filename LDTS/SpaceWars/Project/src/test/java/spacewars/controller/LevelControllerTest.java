package spacewars.controller;

import spacewars.Game;
import spacewars.gui.LanternaGUI;
import spacewars.model.Position;
import spacewars.model.level.Level;
import spacewars.model.element.Player;
import spacewars.model.element.bullet.Bullet;
import spacewars.model.element.bullet.BulletPool;
import spacewars.model.item.Gun;
import spacewars.state.PlayingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class LevelControllerTest {
    LanternaGUI gui;
    Level level;
    BulletPool bulletPool;
    LevelController levelController;

    @BeforeEach
    void setUp() throws IOException {
        this.gui = Mockito.mock(LanternaGUI.class);
        this.level = new Level();
        Player player = new Player(new Position(3,4),Arrays.asList(), new Gun(10,10),10);
        level.setPlayer(player);

        this.bulletPool = new BulletPool();
        Game game = Mockito.mock(Game.class);
        this.levelController = new LevelController(new PlayingState(game,gui,1), gui, level);
        levelController.setBulletPool(bulletPool);
    }

    @Test
    void removeBullets() {
        Bullet bullet = new Bullet(10,10,"",false);
        levelController.removeBullets(bullet);
        Bullet removedBullet = bulletPool.removeBullet();

        Assertions.assertEquals(removedBullet,bullet);

        List<Bullet> bullets = level.getBullets();
        boolean hasBUllet = bullets.contains(bullet);
        Assertions.assertFalse(hasBUllet);
    }

    @Test
    void shoot() {
        Bullet bullet = new Bullet(1,1,"#FFFFFF", true);

        bulletPool.insertBullet(bullet);
        Position position = new Position(4,6);
        levelController.shoot();
        Bullet bulletLevel= level.getBullets().get(0);

        Assertions.assertEquals(bullet, bulletLevel);
    }

    @Test
    void resetPlayer() {
        LevelController levelController = new LevelController(null,gui,level);
        PlayerController playerController = Mockito.mock(PlayerController.class);
        levelController.setPlayerController(playerController);

        levelController.resetPlayer();
        Mockito.verify(playerController,Mockito.times(1)).resetPlayer();
    }
}
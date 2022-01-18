package spacewars.controller;

import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.element.bullet.Bullet;
import spacewars.model.element.enemy.Enemy;
import spacewars.model.level.Level;
import spacewars.state.PlayingState;
import spacewars.model.element.bullet.BulletPool;
import spacewars.model.element.enemy.TFEnemy;
import spacewars.model.element.enemy.SDEnemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EnemyControllerTest {
    EnemyController enemyController;
    Level level;
    LevelController levelController;

    @BeforeEach
    void setUp() throws IOException {
        this.level = new Level();
        Game game = Mockito.mock(Game.class);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.levelController = new LevelController(new PlayingState(game,gui,1), gui, level);
        this.enemyController = new EnemyController(level, levelController);
    }
    @Test
    void step() {
    }

    @Test
    void shoot() {
        Enemy enemy = new TFEnemy();

        BulletPool bulletPool = new BulletPool();
        levelController.setBulletPool(bulletPool);

        Bullet bullet = new Bullet(1,1,"#FFFFFF", true);
        bulletPool.insertBullet(bullet);

        Position position = new Position(4,6);
        enemyController.shoot(enemy);
        Assertions.assertArrayEquals(new List[]{Arrays.asList((int)enemy.getGun().getSpeed(), false)}, new List[]{Arrays.asList((int)bullet.getSpeed(), bullet.isFiredByPlayer())});
        Bullet bulletLevel=  level.getBullets().get(0);
        Assertions.assertEquals(bullet, bulletLevel);
    }

    @Test
    void shouldShoot() {
        Position position = new Position(4,5);
        Player player = new Player(new Position(5,6),Arrays.asList(), null,10);
        level.setPlayer(player);

        Assertions.assertTrue(enemyController.shouldShoot(player.getPosition()));
        level.getPlayer().setPosition(new Position(20,20));
        Assertions.assertFalse(enemyController.shouldShoot(position));
    }

    @Test
    void verifyPosition() {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(11);
        Mockito.when(game.getWidth()).thenReturn(11);
        level.setEnemies(Collections.singletonList(new TFEnemy()));


        Position position = new Position(10,10);
        Assertions.assertTrue(enemyController.verifyPosition(game,position));

        position = new Position(30,30);
        Assertions.assertFalse(enemyController.verifyPosition(game,position));
    }

    @Test
    void bulletConfig() {
        Bullet bullet = new Bullet(10, 12, "", true);
        Enemy enemy = new SDEnemy();
        enemy.setPosition(new Position(0,0));

        enemyController.bulletConfig(enemy,bullet);

        Assertions.assertArrayEquals(new List[]{Arrays.asList(enemy.getGun().getSpeed(), enemy.getPosition().getX(), enemy.getPosition().getY(), enemy.getGun().getDamage(), false)}, new List[]{Arrays.asList((int)bullet.getSpeed(), (int)bullet.getX(), (int)bullet.getY(), bullet.getDamage(), bullet.isFiredByPlayer())});
    }
}
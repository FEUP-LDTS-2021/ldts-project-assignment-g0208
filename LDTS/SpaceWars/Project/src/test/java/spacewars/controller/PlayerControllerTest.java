package spacewars.controller;

import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.level.Level;
import spacewars.model.level.LevelLoader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class PlayerControllerTest {
    private PlayerController playerController;
    private Player player;

    @BeforeEach
    void setUp() throws IOException {
        Level level = new LevelLoader(1).createLevel(25, 25);
        this.player = level.getPlayer();
        this.playerController = new PlayerController(level.getPlayer());
    }

    @Test
    void movePlayerUp() {
        Position previousPosition = playerController.getPlayer().getPosition();
        Position position=playerController.movePlayerUp();
        playerController.movePlayer(position);
        Assertions.assertEquals(new Position(previousPosition.getX(), previousPosition.getY() - 1),player.getPosition());
    }

    @Test
    void movePlayerDown() {
        Position previousPosition = playerController.getPlayer().getPosition();
        Position position=playerController.movePlayerDown();
        playerController.movePlayer(position);
        Assertions.assertEquals(new Position(previousPosition.getX(), previousPosition.getY() + 1),player.getPosition());
    }

    @Test
    void movePlayer() {
        playerController.movePlayer(new Position(3,3));
        Assertions.assertEquals(new Position(3,3),player.getPosition());
    }

    @Test
    void doAction() {
        Assertions.assertArrayEquals(new List[]{Arrays.asList(playerController.movePlayerUp(),
                playerController.movePlayerDown())},
                new List[]{Arrays.asList(playerController.doAction(GUI.ACTION.UP),
                        playerController.doAction(GUI.ACTION.DOWN))});
    }

    @Test
    void changeEnergy() {
        int energy = player.getEnergy();
        playerController.changeEnergy(30);

        Assertions.assertEquals(Math.min(energy + 30, player.getMaxEnergy()), player.getEnergy());

        energy = player.getEnergy();
        playerController.changeEnergy(- 20);

        Assertions.assertEquals(energy - 20, player.getEnergy());
    }

    @Test
    void changeSpeed() {
        int speed = player.getSpeed();
        playerController.changeSpeed(30);

        Assertions.assertEquals(speed + 30, player.getSpeed());

        speed = player.getSpeed();
        playerController.changeSpeed(- 20);

        Assertions.assertEquals(speed - 20, player.getSpeed());
    }

    @Test
    void resetPlayer() {
        playerController.resetPlayer();

        Assertions.assertArrayEquals(new List[]{Arrays.asList(new Position(2, 2), player.getMaxEnergy())}, new List[]{Arrays.asList(player.getPosition(), player.getEnergy())});
    }
}
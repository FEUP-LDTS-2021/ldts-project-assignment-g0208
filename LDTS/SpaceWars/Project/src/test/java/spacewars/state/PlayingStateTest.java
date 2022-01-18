package spacewars.state;

import spacewars.Game;
import spacewars.controller.LevelController;
import spacewars.controller.PlayerController;
import spacewars.controller.PlayingController;
import spacewars.gui.GUI;
import spacewars.gui.KeyBoardObserver;
import spacewars.gui.LanternaGUI;
import spacewars.gui.MouseObserver;
import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.item.Gun;
import spacewars.model.level.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

class PlayingStateTest {
    private PlayingState gameState;
    private Game game;
    private GUI gui;
    private PlayingController playingController;
    private MouseObserver mouseObserver;
    private KeyBoardObserver keyBoardObserver;
    private LevelController levelController;

    @BeforeEach
    void setUp() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGUI.class);
        this.mouseObserver = Mockito.mock(MouseObserver.class);
        this.keyBoardObserver = Mockito.mock(KeyBoardObserver.class);
        this.playingController = Mockito.mock(PlayingController.class);
        this.levelController = Mockito.mock(LevelController.class);

        this.gameState = new PlayingState(game, gui, 1);
        this.gameState.setPlayingController(playingController);

        Mockito.when(game.getMouseObserver()).thenReturn(mouseObserver);
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver);

        Mockito.when(playingController.getLevelController()).thenReturn(levelController);

        PlayerController playerController=Mockito.mock(PlayerController.class);
        Player player = new Player(new Position(1,1), Arrays.asList("Color"),
                new Gun(1,1), 1);

        Mockito.when(levelController.getPlayerController()).thenReturn(playerController);
        Mockito.when(playerController.getPlayer()).thenReturn(player);
    }

    @Test
    void testStart() {
        gameState.start();

        Mockito.verify(mouseObserver, Mockito.times(1)).setListener(playingController);
        Mockito.verify(keyBoardObserver, Mockito.times(1)).setListener(playingController);
    }

    @Test
    void testStep() throws IOException {
        gameState.step(game, 10);

        Mockito.verify(playingController, Mockito.times(1)).step(game, 10);
    }

    @Test
    void testGetPlayingController() {
        PlayingController newPlayingController = gameState.getPlayingController();

        Assertions.assertEquals(newPlayingController, playingController);
    }

    @Test
    void testSetPlayingController() {
        PlayingController newPlayingController = new PlayingController(gameState, gui, Mockito.mock(Level.class));
        gameState.setPlayingController(newPlayingController);

        Assertions.assertEquals(gameState.getPlayingController(), newPlayingController);
    }

    @Test
    void testGetLevel() {
        Assertions.assertEquals(gameState.getLevel(), 1);
    }

    @Test
    void testSetLevel() {
        gameState.setLevel(2);
        Assertions.assertEquals(gameState.getLevel(), 2);
        gameState.setLevel(3);
        Assertions.assertEquals(gameState.getLevel(), 3);
    }
}
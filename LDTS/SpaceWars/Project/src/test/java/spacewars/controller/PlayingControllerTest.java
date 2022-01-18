package spacewars.controller;

import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.level.Level;
import spacewars.state.GameState;
import spacewars.state.PlayingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class PlayingControllerTest {
    PlayingController playingController;
    GameState playingState;

    @BeforeEach
    void setUp() throws IOException {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(1);
        Mockito.when(game.getWidth()).thenReturn(1);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.playingState = new PlayingState(game, gui,1);
        this.playingController = new PlayingController(playingState, gui, new Level());
    }

    @Test
    void setupModel() {
        Level level = new Level();
        playingController.setupModel(level);
        Level testLevel = playingController.getModel();

        Assertions.assertEquals(level,testLevel);
    }
}
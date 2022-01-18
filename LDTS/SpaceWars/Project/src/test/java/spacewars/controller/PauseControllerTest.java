package spacewars.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.element.button.Button;
import spacewars.model.item.command.MenuButtonCommand;
import spacewars.state.PauseState;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PauseControllerTest {

    PauseController pauseController;
    PauseState pauseState;

    @BeforeEach
    void setUp() throws IOException {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(1);
        Mockito.when(game.getWidth()).thenReturn(1);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.pauseState = new PauseState(game, gui, null);
        this.pauseController = new PauseController(pauseState, gui);
    }
    @Test
    void click() {
        List<Button> buttons = pauseState.getButtons();
        Button button = buttons.get(buttons.size()-1);
        MenuButtonCommand command = Mockito.mock(MenuButtonCommand.class);
        button.setCommand(command);

        pauseController.click(button.getPosition());
        Mockito.verify(command, Mockito.times(1)).execute();
    }

    @Test
    void move() {
        List<Button> buttons = pauseState.getButtons();
        Button button = buttons.get(buttons.size() - 1);
        pauseController.move(button.getPosition());
        Assertions.assertTrue(button.isHighlighted());
    }
}
package spacewars.state;

import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.item.command.MenuButtonCommand;
import spacewars.controller.PauseController;
import spacewars.model.element.button.Button;

import java.io.IOException;
import java.util.Arrays;

public class PauseState extends GameState {
    private PauseController pauseController;

    public PauseState(Game game, GUI gui, GameState previousState) throws IOException {
        super(game, Arrays.asList(
                new Button(new Position(Button.getButtonX(game.getWidth(), "RESUME"),7),"RESUME",
                        new MenuButtonCommand(previousState), Arrays.asList("#FFFFFF", "#FFFF00")),
                new Button(new Position(Button.getButtonX(game.getWidth(), "EXIT"),14), "EXIT",
                        new MenuButtonCommand(new MenuState(game,gui)), Arrays.asList("#FFFFFF", "#FFFF00"))
        ));

        pauseController = new PauseController(this, gui);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(pauseController);
        game.getKeyBoardObserver().setListener(pauseController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        this.pauseController.step();
    }

    public PauseController getPauseController() {
        return pauseController;
    }

    public void setPauseController(PauseController pauseController) {
        this.pauseController = pauseController;
    }
}

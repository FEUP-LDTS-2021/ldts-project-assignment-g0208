package spacewars.controller;

import spacewars.gui.GUI;
import spacewars.model.item.command.MenuButtonCommand;
import spacewars.viewer.state.StateViewer;
import spacewars.model.Position;
import spacewars.model.element.button.Button;
import spacewars.state.GameState;
import spacewars.state.listener.KeyBoardListener;
import spacewars.state.listener.MouseListener;
import spacewars.viewer.state.MenuViewer;

import java.io.IOException;

public class MenuController implements MouseListener, KeyBoardListener {
    private final GameState gameState;
    private final StateViewer menuViewer;

    public MenuController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.menuViewer = new MenuViewer(gui, gameState.getButtons());
    }

    public void step() throws IOException {
        menuViewer.draw();
    }

    private void changeState(Button button) {
        MenuButtonCommand buttonCommand = (MenuButtonCommand) button.getCommand();
        if (buttonCommand.getNextState()==null){
            gameState.changeState(null);
            return;
        }
        buttonCommand.execute();
    }

    @Override
    public void click(Position position) {
        Position clickedPos = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());
        for (Button button:gameState.getButtons()){
            Position buttonPos = button.getPosition();
            if (buttonPosition(clickedPos, buttonPos, button.getWidth(), button.getHeight())){
                button.getCommand().execute();
            }
        }
    }

    @Override
    public void move(Position position) {
        Position currentPosition = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());
        for(Button button: gameState.getButtons()) {
            Position buttonPos = button.getPosition();
            if(buttonPosition(currentPosition, buttonPos, button.getWidth(), button.getHeight())) {
                button.highlight();
                continue;
            }
            if(button.isHighlighted())
                button.toneDown();
        }
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT){
            gameState.changeState(null);
        }
    }
}

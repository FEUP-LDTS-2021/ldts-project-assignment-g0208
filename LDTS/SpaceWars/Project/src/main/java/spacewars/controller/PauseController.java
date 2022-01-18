package spacewars.controller;

import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.state.GameState;
import spacewars.state.listener.KeyBoardListener;
import spacewars.state.listener.MouseListener;
import spacewars.viewer.state.PauseViewer;
import spacewars.viewer.state.StateViewer;
import spacewars.model.element.button.Button;

import java.io.IOException;

public class PauseController implements KeyBoardListener, MouseListener {
    private final GameState gameState;
    private final StateViewer pauseViewer;

    public PauseController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.pauseViewer = new PauseViewer(gui, gameState.getButtons());
    }

    public void step() throws IOException {
        pauseViewer.draw();
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

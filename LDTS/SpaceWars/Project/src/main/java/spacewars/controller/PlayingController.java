package spacewars.controller;

import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.level.Level;
import spacewars.state.GameState;
import spacewars.state.MenuState;
import spacewars.state.PauseState;
import spacewars.state.PlayingState;
import spacewars.viewer.state.EndGameViewer;
import spacewars.viewer.state.PlayingViewer;
import spacewars.viewer.state.StateViewer;
import spacewars.model.element.button.Button;
import spacewars.state.listener.KeyBoardListener;
import spacewars.state.listener.MouseListener;
import spacewars.viewer.state.LoadingLevelViewer;

import java.io.IOException;
import java.util.ArrayList;

public class PlayingController extends GameController implements MouseListener, KeyBoardListener {
    private final GameState gameState;
    private LevelController levelController;
    private final GUI gui;
    private final StateViewer playingViewer;
    private long endTime;

    public PlayingController(GameState gameState, GUI gui, Level level) {
        super(level);
        this.gameState = gameState;
        this.gui = gui;
        this.levelController = new LevelController(gameState,gui, level);
        this.playingViewer = new PlayingViewer(gui, new ArrayList<>(), level.getPlayer());
        this.endTime = 0;
    }

    public void step(Game game, long time) throws IOException {
        if(this.endTime == 0) {
            levelController.step(game, time);
            playingViewer.draw();
            if (getModel().getEnemies().isEmpty()) {
                this.endTime = ((PlayingState) gameState).upLevel() ? 0 : time;
                if(this.endTime == 0) {
                    LoadingLevelViewer loadingLevelViewer = new LoadingLevelViewer(gui, null, ((PlayingState) gameState).getLevel());
                    loadingLevelViewer.draw();
                } else {
                    EndGameViewer endGameViewer = new EndGameViewer(gui, null, true);
                    endGameViewer.draw();
                }

            } else if (getModel().getPlayer().getEnergy() <= 0) {
                this.endTime = time;
                EndGameViewer endGameViewer = new EndGameViewer(gui, null, false);
                endGameViewer.draw();
            }
        }
        else{
            endGame(time);
        }

        checkButtons();

    }

    private void changeState(GameState gameState){
        this.gameState.changeState(gameState);
    }

    public void endGame(long time) throws IOException {

        if(time - this.endTime > 1500) {
            changeState(new MenuState(this.gameState.getGame(), gui));
        }
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if (action == GUI.ACTION.QUIT){
            try {
                changeState(new PauseState(gameState.getGame(), this.gui, this.gameState));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        else if(action == GUI.ACTION.FIRE) {
            levelController.shoot();
        }

        levelController.doAction(action);
    }

    @Override
    public void click(Position position) {
        Position clickedPos = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());

        for (Button button : gameState.getButtons()){
            if(button.isActive()) continue;

            Position buttonPos = button.getPosition();
            if (buttonPosition(clickedPos, buttonPos, button.getWidth(), button.getHeight())){
                button.getCommand().execute();
                button.activate();
                return;
            }
        }
    }

    @Override
    public void move(Position position) {}

    public void checkButtons() {
        for(Button button: gameState.getButtons()) {
            if(!button.isActive()) continue;

            if (!button.getCommand().execute()) button.deactivate();
        }
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public void setupModel(Level level) {
        this.levelController = new LevelController(gameState,gui, level);
        this.setModel(level);
    }
}

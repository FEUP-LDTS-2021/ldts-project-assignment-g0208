package spacewars.state;

import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.model.element.Player;
import spacewars.model.level.Level;
import spacewars.controller.PlayingController;
import spacewars.model.level.LevelLoader;
import spacewars.model.element.button.Button;

import java.io.IOException;
import java.util.Arrays;

public class PlayingState extends GameState {
    private int levelactual;
    private final int maxLevel;
    private PlayingController playingController;
    private Level levela;

    public PlayingState(Game game, GUI gui, int level) throws IOException {
        super(game, Arrays.asList());
        this.levelactual = level;
        this.maxLevel = 3;
        this.levela = new LevelLoader(levelactual).createLevel(game.getWidth(), game.getHeight());
        this.playingController = new PlayingController(this, gui, levela);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(playingController);
        game.getKeyBoardObserver().setListener(playingController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        playingController.step(game, time);
    }

    public boolean upLevel() throws IOException {
        if(++levelactual > maxLevel) return false;
        Player player=levela.getPlayer();
        this.levela = new LevelLoader(levelactual).createLevel(game.getWidth(), game.getHeight());
        this.levela.setPlayer(player);
        this.playingController.setupModel(levela);

        resetValues();

        return true;
    }

    private void resetValues() {
        playingController.getLevelController().resetPlayer();
        resetButtons();
    }

    private void resetButtons() {
        for (Button button : getButtons()){
            button.getCommand().undo();
            button.deactivate();
        }
    }


    public int getLevel() {
        return levelactual;
    }

    public void setLevel(int level) {
        this.levelactual = level;
    }

    public PlayingController getPlayingController() {
        return playingController;
    }

    public void setPlayingController(PlayingController playingController) {
        this.playingController = playingController;
    }

    public Level getLevelActual() {
        return levela;
    }

    public void setLevel(Level level) {
        this.levela = level;
    }
}

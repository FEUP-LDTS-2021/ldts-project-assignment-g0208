package spacewars.model.level;

import spacewars.model.element.Player;
import spacewars.model.element.enemy.Enemy;

import java.util.List;

public abstract class LevelBuilder {

    public Level createLevel(int width, int height){
        Level level = new Level();
        level.setPlayer(createPlayer());
        level.setEnemies(createEnemies(width, height));
        return level;
    }

    protected abstract Player createPlayer();

    protected abstract List<Enemy> createEnemies(int width, int height);

}

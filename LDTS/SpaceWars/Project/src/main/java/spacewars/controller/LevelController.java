package spacewars.controller;

import spacewars.Game;
import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.element.bullet.Bullet;
import spacewars.model.element.enemy.Enemy;
import spacewars.state.GameState;
import spacewars.model.level.Level;
import spacewars.model.element.bullet.BulletPool;
import spacewars.viewer.LevelViewer;

import java.io.IOException;
import java.util.List;

public class LevelController extends GameController {
    private final GameState gameState;
    private final LevelViewer LevelViewer;
    private final EnemyController enemyController;
    private PlayerController playerController;
    private BulletPool bulletPool;

    public LevelController(GameState gameState, GUI gui, Level level) {
        super(level);
        this.gameState = gameState;
        this.LevelViewer = new LevelViewer(gui, level);
        this.enemyController = new EnemyController(level, this);
        this.playerController = new PlayerController(level.getPlayer());
        this.bulletPool= new BulletPool();
    }

    @Override
    public void step(Game game, long time) throws IOException {
        updateBullets();
        enemyController.step(game, time);
        LevelViewer.draw();
    }

    private void updateBullets() {
        List<Bullet> bullets = getModel().getBullets();
        for (int i = 0; i < bullets.size(); i++){
            Bullet bullet=bullets.get(i);
            if(bullet.isFiredByPlayer()) {
                for (int j = 0; j < bullet.getSpeed(); j++) {
                    if (!bullet.updatePos() || !getModel().isEmpty(bullet.getPosition())) {
                        Enemy enemy = getModel().isEnemy(bullet.getPosition());

                        if (enemy != null) {
                            enemy.decreaseEnergy(bullet.getDamage());
                            if (enemy.getEnergy() <= 0) {
                                getModel().getEnemies().remove(enemy);
                            }
                        }
                        removeBullets(bullet);
                        i--;
                        break;
                    }
                }
            } else {
                for (int j = 0; j < bullet.getSpeed(); j++) {
                    if (!bullet.updatePos() || getModel().isPlayer(bullet.getPosition()) ) {
                        if(getModel().isPlayer(bullet.getPosition())) {
                            getModel().getPlayer().changeEnergy(-bullet.getDamage());
                        }
                        removeBullets(bullet);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    public void removeBullets(Bullet bullet) {
        bulletPool.insertBullet(bullet);
        getModel().removeBullet(bullet);
    }

    public Bullet getBullet() {
        return bulletPool.removeBullet();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }


    public void doAction(GUI.ACTION action) {
        Position nextPos = playerController.doAction(action);
        if(nextPos==null) return;
        if (outOfBounds(nextPos)) return;
        if(nextPos.getX()<0 || nextPos.getY()<0) return;

        checkOut(nextPos);
    }

    private boolean outOfBounds(Position pos) {
        Game game = gameState.getGame();
        return pos.getX()<0 || pos.getY()<0 || pos.getX()>=game.getWidth() || pos.getY()>=game.getHeight();
    }

    public void shoot(){
        Bullet bullet = bulletPool.removeBullet();
        playerController.bulletConfig(bullet);

        bullet.setDegree(0);

        getModel().insertBullet(bullet);
    }

    private void checkOut(Position position){

        boolean outOfBounds = outOfBounds(position);

        if(outOfBounds == false){
            playerController.movePlayer(position);
            return;
        }
    }

    public void resetPlayer() {
        playerController.resetPlayer();
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void setBulletPool(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
    }
}

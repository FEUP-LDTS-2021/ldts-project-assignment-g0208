package spacewars.controller;

import spacewars.Game;
import spacewars.model.Position;
import spacewars.model.element.bullet.Bullet;
import spacewars.model.element.enemy.Enemy;
import spacewars.model.level.Level;

import java.io.IOException;

public class EnemyController extends GameController{
    private final LevelController levelController;
    private long lastMovement;
    private long beginningGame;

    public EnemyController(Level level, LevelController levelController) {
        super(level);

        this.levelController = levelController;
        this.lastMovement = 0;
        this.beginningGame = 0;
    }

    @Override
    public void step(Game game, long time) throws IOException {
        if(this.beginningGame == 0) this.beginningGame = time;

        if (time - lastMovement > 250) {
            for (Enemy enemy : getModel().getEnemies()) {
                Position position;
                do {
                    position = enemy.getPosition().getRandomNeighbour(enemy.getSpeed());
                } while(!verifyPosition(game, position));
                moveEnemy(enemy, position);
                this.lastMovement = time;

                if((time - this.beginningGame > 1000) && (time - enemy.getLastShot() > 1250)) {
                    if (shouldShoot(enemy.getPosition())){
                        shoot(enemy);
                        enemy.setLastShot(time);
                    }
                }
            }
        }
    }

    public void shoot(Enemy enemy){
        Bullet bullet = levelController.getBullet();
        bulletConfig(enemy, bullet);

        bullet.setDegree(Math.PI);

        getModel().insertBullet(bullet);
    }

    public boolean shouldShoot(Position enemyPos) {
        Position playerPos = getModel().getPlayer().getPosition();
        return playerPos.getY() == enemyPos.getY();
    }

    private void moveEnemy(Enemy enemy, Position position) {
        if (getModel().isEmpty(position)) {
            enemy.setPosition(position);
        }
    }

    public boolean verifyPosition(Game game, Position position) {
        Level level = getModel();
        return level.isEmpty(position) && insideLevel(game, position);
    }

    public void bulletConfig(Enemy enemy, Bullet bullet){
        bullet.setSpeed(enemy.getGun().getSpeed());
        bullet.setX(enemy.getPosition().getX());
        bullet.setY(enemy.getPosition().getY());
        bullet.setDamage(enemy.getGun().getDamage());
        bullet.setFiredByPlayer(false);
    }

    private boolean insideLevel(Game game, Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x >= 5) && (x <= game.getWidth() - 1) && (y >= 1) && (y <= game.getHeight() - 1);
    }

}

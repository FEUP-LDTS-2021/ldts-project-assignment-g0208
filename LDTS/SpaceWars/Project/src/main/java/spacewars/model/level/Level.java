package spacewars.model.level;

import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.element.bullet.Bullet;
import spacewars.model.element.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {
    private List<Enemy> enemies;
    private Player player;
    private List<Bullet> bullets;

    public Level() {
        bullets = new ArrayList<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void insertBullet(Bullet bullet){
        bullets.add(bullet);
    }
    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    public boolean isEmpty(Position position) {
        return isEnemy(position) == null;
    }

    public Enemy isEnemy(Position position) {
        for(Enemy enemy : enemies) {
            if(enemy.getPosition().equals(position))
                return enemy;
        }

        return null;
    }

    public boolean isPlayer(Position position) {
        return position.equals(this.player.getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level that = (Level) o;
        return Objects.equals(enemies, that.enemies) && Objects.equals(player, that.player) &&  Objects.equals(bullets, that.bullets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enemies, player, bullets);
    }
}

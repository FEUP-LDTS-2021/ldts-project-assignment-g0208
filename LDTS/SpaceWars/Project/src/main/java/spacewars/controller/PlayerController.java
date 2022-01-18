package spacewars.controller;

import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.element.bullet.Bullet;

public class PlayerController {
    private final Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public Position movePlayerUp() {
        return this.player.getPosition().getUp(this.player.getSpeed());
    }
    public Position movePlayerDown() {
        return this.player.getPosition().getDown(this.player.getSpeed());
    }
    public void movePlayer(Position position) {
        player.setPosition(position);
    }

    public Position doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.UP) return movePlayerUp();
        if (action == GUI.ACTION.DOWN) return movePlayerDown();
        return null;
    }

    public void bulletConfig(Bullet bullet){
        bullet.setSpeed(player.getGun().getSpeed());
        bullet.setX(player.getPosition().getX());
        bullet.setY(player.getPosition().getY());
        bullet.setDamage(player.getGun().getDamage());
        bullet.setFiredByPlayer(true);
    }

    public void changeEnergy(int value) { player.changeEnergy(value);}

    public void changeSpeed(int value) { player.changeSpeed(value); }

    public Player getPlayer() {
        return player;
    }


    public void resetPlayer() {
        player.setPosition(new Position(2, 2));
        player.setEnergy(player.getMaxEnergy());
    }

    public void setDefaultColor() {
        player.setDefaultColor();
    }
}

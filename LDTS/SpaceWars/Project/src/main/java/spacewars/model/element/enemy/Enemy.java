package spacewars.model.element.enemy;

import spacewars.model.Position;
import spacewars.model.element.Element;
import spacewars.model.item.Gun;

import java.util.List;

public abstract class Enemy extends Element {
    protected int energy;
    protected int maxEnergy;
    protected int bounty;
    protected List<String> colors;
    protected int speed;
    protected Gun gun;
    protected double lastShot;

    public Enemy(List<String> colors) {
        super(new Position(5, 5), colors.get(colors.size() - 1));
        this.energy = 0;
        this.maxEnergy = 0;
        this.colors = colors;
        this.speed = 0;
        this.lastShot = 0;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        if(this.energy > this.maxEnergy) this.maxEnergy = this.energy;
        this.energy = energy;
    }

    public void decreaseEnergy(int damage) {
        this.energy -= damage;
        if(energy > 0) updateColor();
    }

    public void updateColor() {
        int index = (this.energy >= (this.maxEnergy / 2)) ? 0 : 1;
        this.color = this.colors.get(index);
    }

    public int getSpeed() {
        return speed;
    }

    public double getLastShot() {
        return lastShot;
    }

    public void setLastShot(double lastShot) {
        this.lastShot = lastShot;
    }

    public Gun getGun() {
        return gun;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}

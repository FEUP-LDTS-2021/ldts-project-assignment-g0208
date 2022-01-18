package spacewars.model.element;

import spacewars.model.Position;
import spacewars.model.item.Gun;

import java.util.List;

public class Player extends Element {
    private final List<String> colors;
    private final String defaultColor;
    private Gun gun;
    private final int maxEnergy;
    private int energy;
    private int speed;

    public Player(Position position, List<String> colors, Gun gun, int energy) {
        super(position, "#FFDB58");
        this.colors = colors;
        this.gun = gun;
        this.defaultColor = "#FFDB58";
        this.maxEnergy = energy;
        this.energy = energy;
        this.speed = 1;
    }

    public Gun getGun() {
        return gun;
    }
    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = Math.min(energy, this.maxEnergy);
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }
    public void changeEnergy(int value) { setEnergy(this.energy + value); }

    public void setDefaultColor(){
        this.color = defaultColor;
    }

    public int getSpeed() {
        return speed;
    }
    public void changeSpeed(int value) {
        this.speed += value;
    }

}

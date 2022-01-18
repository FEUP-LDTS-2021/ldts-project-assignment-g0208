package spacewars.model.item;

public class Gun {
    private int damage;
    private int speed;


    public Gun(int damage, int speed) {
        this.speed = speed;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gun gun = (Gun) o;
        return this.damage == gun.damage && this.speed == gun.speed;
    }
}

package spacewars.model.element;

import spacewars.model.Position;

public abstract class Element {
    protected Position position;
    protected String color;

    public Element(Position position, String color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}

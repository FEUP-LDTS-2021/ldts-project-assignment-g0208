package SpaceWars.Model.menu;

import SpaceWars.Model.Position;

import java.util.List;

public class Button {
    private List<String> buttonText;
    private Position upperLeft, upperRight;
    private final String backgroundColor;
    private final String backgroundDark;
    private final String textColor;
    private boolean active;
    /*private ButtonCommand action;*/

    public Button(List<String> buttonText, Position upperLeft, Position upperRight, String backgroundColor, String backgroundDark, String textColor) {
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.backgroundColor = backgroundColor;
        this.backgroundDark = backgroundDark;
        this.textColor = textColor;
        this.active = false;
    }

    public List<String> getButtonText() {
        return buttonText;
    }

    public Position getUpperLeft() {
        return upperLeft;
    }

    public Position getUpperRight() {
        return upperRight;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundDark() {
        return backgroundDark;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Button Command action) {
        this.action = action;
    }
}

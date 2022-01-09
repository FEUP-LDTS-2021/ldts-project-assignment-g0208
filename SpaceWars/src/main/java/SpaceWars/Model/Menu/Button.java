package SpaceWars.Model.Menu;

import SpaceWars.Model.Position;
import SpaceWars.Controller.Command.ButtonCommand.ButtonCommand;

import java.util.List;

public class Button {
    private List<String> buttonText;
    private Position upperLeft, lowerRight;
    private final String backgroundColor;
    private final String backgroundDark;
    private final String textColor;
    private boolean active;
    private ButtonCommand action;

    public Button(List<String> buttonText, Position lowerRight, Position upperLeft, String backgroundColor, String backgroundDark, String textColor) {
        this.buttonText = buttonText;
        this.lowerRight = lowerRight;
        this.upperLeft = upperLeft;
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
    public Position getLowerRight() {
        return lowerRight;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundDark() {
        return backgroundDark;
    }

    public String getTextColor() {
        return textColor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAction(ButtonCommand action) {
        this.action = action;
    }

    public ButtonCommand getAction() {
        return action;
    }
}

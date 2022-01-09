package SpaceWars.Model.Menu;


import SpaceWars.Controller.Command.buttonCommand.ButtonCommand;
import SpaceWars.Model.Position;

import java.util.List;

public class Button {
    private List<String> buttonText;
    private Position upperLeft, lowerRight;
    private final String backgroundColor;
    private final String backgroundColorDark;
    private final String textColor;
    private boolean active;
    private ButtonCommand action;

    public Button(List<String> buttonText, Position upperLeft, Position lowerRight, String backgroundColor, String backgroundColorDark, String textColor) {
        this.buttonText = buttonText;
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
        this.backgroundColor = backgroundColor;
        this.backgroundColorDark = backgroundColorDark;
        this.textColor = textColor;
        this.active = false;
    }

    public List<String> getButtonText(){return buttonText;}
    public Position getUpperLeft() {
        return upperLeft;
    }

    public Position getLowerRight() {
        return lowerRight;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundColorDark() {
        return backgroundColorDark;
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

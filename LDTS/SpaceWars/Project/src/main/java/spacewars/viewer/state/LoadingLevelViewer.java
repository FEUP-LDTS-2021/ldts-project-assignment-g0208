package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.element.button.Button;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoadingLevelViewer extends StateViewer {
    private final int level;

    public LoadingLevelViewer(GUI gui, List<Button> buttons, int level) {
        super(gui, buttons);
        this.level = level;
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        loading();
        gui.refresh();
    }

    private void drawText(Position position, String text, String backColor, String textColor) {
        gui.drawTitle(position, text, backColor, textColor);
    }

    private void loading() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);

            drawText(new Position(2, 8), "LOADING NEXT BATTLE", "#000000", "#FFFFFF");

            TimeUnit.MILLISECONDS.sleep(100);

            String text1 = "THE BATTLE WILL START ";
            String text2 = "LEVEL " + level + "...";
            for(int i = 0; i < text1.length(); i++) {
                drawText(new Position(2 + i, 15), text1.charAt(i) + "", "#000000", "#FFFFFF");
                gui.refresh();
                TimeUnit.MILLISECONDS.sleep(35);
            }

            for(int i = 0; i < text2.length(); i++) {
                drawText(new Position(2 + i, 17), text2.charAt(i) + "", "#000000", "#FFFFFF");
                gui.refresh();
                TimeUnit.MILLISECONDS.sleep(35);
            }

            TimeUnit.MILLISECONDS.sleep(2000);

        } catch (Exception e) {
            System.out.println("ERROR"); //TODO
        }
    }
}
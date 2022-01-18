package spacewars.gui;

import spacewars.state.listener.KeyBoardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoardObserver extends KeyAdapter {
    private KeyBoardListener listener;

    public KeyBoardObserver() {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                listener.keyPressed(GUI.ACTION.QUIT);
                break;
            case KeyEvent.VK_W:
                listener.keyPressed(GUI.ACTION.UP);
                break;
            case KeyEvent.VK_S:
                listener.keyPressed(GUI.ACTION.DOWN);
                break;

            case KeyEvent.VK_SPACE:
                listener.keyPressed(GUI.ACTION.FIRE);
                break;
        }
    }

    public void setListener(KeyBoardListener listener) {
        this.listener = listener;
    }
}

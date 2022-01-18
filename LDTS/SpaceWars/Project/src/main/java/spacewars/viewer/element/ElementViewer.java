package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.model.element.Element;

public interface ElementViewer<T extends Element> {
    void drawElement(T element, GUI gui);
}



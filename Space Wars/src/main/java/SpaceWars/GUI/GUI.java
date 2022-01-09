package SpaceWars.GUI;

import java.io.IOException;
import java.util.List;

public interface GUI {

    void background();
    void refresh() throws IOException;
    void close() throws IOException;


}

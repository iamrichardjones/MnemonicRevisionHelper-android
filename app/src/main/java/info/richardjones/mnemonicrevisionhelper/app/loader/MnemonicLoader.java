package info.richardjones.mnemonicrevisionhelper.app.loader;

import java.io.IOException;
import java.util.List;

public interface MnemonicLoader<T> {

    String SLOGAN_ORIGIN_NAME = "Slogan";
    String SONG_TITLE_ORIGIN_NAME  = "Song Title";

    void load(List<T> list) throws IOException;
}

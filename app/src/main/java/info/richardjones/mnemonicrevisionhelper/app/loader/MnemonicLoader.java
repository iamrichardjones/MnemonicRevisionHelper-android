package info.richardjones.mnemonicrevisionhelper.app.loader;

import java.io.IOException;
import java.util.List;

public interface MnemonicLoader<T> {

    void load(List<T> list) throws IOException;
}

package info.richardjones.mnemonicrevisionhelper.app.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class FileMnemonicMapLoader implements MnemonicLoader<MatchingMnemonic> {

    private static final String DELIMETER = "\\|";
    private final BufferedReader[] readers;

    public FileMnemonicMapLoader(File... dataFile) throws FileNotFoundException {
        this(getBufferedReaders(dataFile));
    }

    private static BufferedReader[] getBufferedReaders(File[] dataFiles) throws FileNotFoundException {
        List<BufferedReader> readers = new ArrayList();
        for (File dataFile : dataFiles) {
            readers.add(new BufferedReader(new FileReader(dataFile)));
        }
        return readers.toArray(new BufferedReader[readers.size()]);
    }

    public FileMnemonicMapLoader(BufferedReader... readers) {
        this.readers = readers;
    }

    @Override
    public void load(List<MatchingMnemonic> list) throws IOException {
        for (BufferedReader reader : readers) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.trim().split(DELIMETER);
                if (split.length == 4) {
                    list.add(new MatchingMnemonic(split[3], new MatchingMnemonicDetail(split[2], split[1], split[0])));
                }
            }
//            reader.close();
        }
    }
}

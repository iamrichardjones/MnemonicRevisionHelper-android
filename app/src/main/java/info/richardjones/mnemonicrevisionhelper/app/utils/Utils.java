package info.richardjones.mnemonicrevisionhelper.app.utils;

public class Utils {

    public static final String DEBUG_SET = "-D";

    public static final String NULL_STRING = "NULL";
    public static final String EMPTY_STRING = "";
    public static final String UNKNOWN_STRING = "UNKNOWN";

    public static final Integer DEFAULT_COLUMN_LENGTH = 30;

    public static String fixedLengthString(String string, int length) {
        if (string.length() > length) {
            return string;
        }
        return String.format("%1$-"+length+ "s", string);
    }
}

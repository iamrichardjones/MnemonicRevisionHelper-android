package info.richardjones.mnemonicrevisionhelper.app.loader;


public class MatchingMnemonicDetail {

    private final String expandedMnemonic;
    private final String origin;
    private final String category;

    public MatchingMnemonicDetail(String expandedMnemonic, String origin, String category) {
        this.expandedMnemonic = expandedMnemonic;
        this.origin = origin;
        this.category = category;
    }

    public String getExpandedMnemonic() {
        return expandedMnemonic;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchingMnemonicDetail that = (MatchingMnemonicDetail) o;

        if (!expandedMnemonic.equals(that.expandedMnemonic)) return false;
        if (!origin.equals(that.origin)) return false;
        if (!category.equals(that.category)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expandedMnemonic.hashCode();
        result = 31 * result + origin.hashCode();
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" origin: ").append(origin);
        builder.append(" category: ").append(category);
        builder.append(" expandedMnemonic: ").append(expandedMnemonic);
        return builder.toString();
    }
}

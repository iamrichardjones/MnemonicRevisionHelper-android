package info.richardjones.mnemonicrevisionhelper.app;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import info.richardjones.mnemonicrevisionhelper.app.loader.MatchingMnemonic;
import info.richardjones.mnemonicrevisionhelper.app.loader.MnemonicLoader;


import java.util.*;

public class MnemonicFilter extends Filter {

    private final ArrayAdapter adapter;
    private List<MatchingMnemonic> originalData;
    private List<MatchingMnemonic> filteredData;

    public MnemonicFilter(ArrayAdapter adapter, List<MatchingMnemonic> values){
        this.adapter = adapter;
        this.originalData = values;
        this.filteredData = values;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        filteredData = (List<MatchingMnemonic>) results.values;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint.toString().equals("") || constraint.length() == 1) {
            results.values = originalData;
            results.count = originalData.size();
            return results;
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(adapter.getContext());
        Boolean incSlogans = sharedPref.getBoolean(SettingsActivity.USE_SLOGAN_PREFERENCES, true);
        Boolean incSongTitles = sharedPref.getBoolean(SettingsActivity.USE_SONG_TITLE_PREFERENCES, true);

        Boolean matchCompletely = sharedPref.getBoolean(SettingsActivity.USE_COMPLLETE_SEARCH_PREFERENCES, true);


        List<MatchingMnemonic> filteredArrayNames = new ArrayList<MatchingMnemonic>();

        char[] charArray = constraint.toString().toUpperCase().toCharArray();
        Arrays.sort(charArray);
        String searchText = new String(charArray);

        Set<String> strArray = deduplicateTextToSet(searchText);

        for (MatchingMnemonic dataNames: originalData) {
            if (dataNames.getDetail().getCategory().equals(MnemonicLoader.SLOGAN_ORIGIN_NAME) && !incSlogans) {
                continue;
            }
            if (dataNames.getDetail().getCategory().equals(MnemonicLoader.SONG_TITLE_ORIGIN_NAME) && !incSongTitles) {
                continue;
            }

            if (matchCompletely) {
                if (dataNames.getOrderedMnemonic().equals(searchText)) {
                    filteredArrayNames.add(dataNames);
                }
            }
            else {
                String mnemonic = dataNames.getMnemonic();
                if (searchText.length() > mnemonic.length()) {
                    continue;
                }
                int matchCnt = 0;
                String dedupedMnemonic = deduplicateCharactersFromString(mnemonic.toUpperCase());
                for (String s : strArray) {
                   if (dedupedMnemonic.contains(s)) {
                       matchCnt++;
                       continue;
                   }
                }
                if (matchCnt == deduplicateCharactersFromString(searchText).length()) {
                    filteredArrayNames.add(dataNames);
                }
            }
        }

        results.count = filteredArrayNames.size();
        results.values = filteredArrayNames;

        return results;
    }

    private String deduplicateCharactersFromString(String text) {
        StringBuilder sb = new StringBuilder();
        for (String s : deduplicateTextToSet(text)) {
            sb.append(s);
        }
        return sb.toString();
    }

    private Set<String> deduplicateTextToSet(String text) {
        Set<String> dedupedSet = new HashSet<String>();
        for(char c : text.toCharArray()) {
            dedupedSet.add(c + "");
        }
        return dedupedSet;
    }


    public List<MatchingMnemonic> getFilteredData() {
        return filteredData;
    }
}

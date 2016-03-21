package info.richardjones.mnemonicrevisionhelper.app;

import android.widget.ArrayAdapter;
import android.widget.Filter;
import info.richardjones.mnemonicrevisionhelper.app.loader.MatchingMnemonic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<MatchingMnemonic> filteredArrayNames = new ArrayList<MatchingMnemonic>();

        char[] charArray = constraint.toString().toUpperCase().toCharArray();
        Arrays.sort(charArray);
        String searchText = new String(charArray);

        for (MatchingMnemonic dataNames: originalData) {
            if (dataNames.getOrderedMnemonic().equals(searchText))  {
                filteredArrayNames.add(dataNames);
            }
        }

        results.count = filteredArrayNames.size();
        results.values = filteredArrayNames;

        return results;
    }

    public List<MatchingMnemonic> getFilteredData() {
        return filteredData;
    }
}

package info.richardjones.mnemonicrevisionhelper.app;

import info.richardjones.mnemonicrevisionhelper.app.MnemonicFilter;
import info.richardjones.mnemonicrevisionhelper.app.R;
import info.richardjones.mnemonicrevisionhelper.app.loader.MatchingMnemonic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.List;

public class MnemonicResultsArrayAdapter extends ArrayAdapter<MatchingMnemonic> implements Filterable {

    private final Context context;
    private final LayoutInflater inflater;
    private final MnemonicFilter filter;
//    private List<MatchingMnemonic> originalData;
//    private List<MatchingMnemonic> filteredData;

    public MnemonicResultsArrayAdapter(Context context, List<MatchingMnemonic> values) {
        super(context, -1, values);
        this.context = context;
//        this.originalData = values;
//        this.filteredData = values;
        inflater = LayoutInflater.from(context);
        filter = new MnemonicFilter(this, values);

    }

    public int getCount() {
        return filter.getFilteredData().size();
    }

    public MatchingMnemonic getItem(int position) {
        return filter.getFilteredData().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView answerTV = (TextView) rowView.findViewById(R.id.firstLine);
        TextView originTV = (TextView) rowView.findViewById(R.id.secondLine);
        TextView typeTV = (TextView) rowView.findViewById(R.id.thirdLine);

        MatchingMnemonic matchingMnemonic = filter.getFilteredData().get(position);
        if (matchingMnemonic != null) {
            answerTV.setText(matchingMnemonic.getDetail().getExpandedMnemonic());
            originTV.setText("Origin: " + matchingMnemonic.getDetail().getOrigin());
            typeTV.setText("Type: " + matchingMnemonic.getDetail().getCategory());
        }
        return rowView;
    }

    @Override
    public Filter getFilter() {
        return filter;
//        Filter filter = new Filter() {
//
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                if (results.count == 0) {
//                    notifyDataSetInvalidated();
//                } else {
//                    filteredData = (List<MatchingMnemonic>) results.values;
//                    notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//                if (constraint.toString().equals("") || constraint.length() == 1) {
//                    results.values = originalData;
//                    results.count = originalData.size();
//                    return results;
//                }
//
//                List<MatchingMnemonic> filteredArrayNames = new ArrayList<MatchingMnemonic>();
//
//                for (int i = 0; i < originalData.size(); i++) {
//                    MatchingMnemonic dataNames = originalData.get(i);
//
//                    char[] charArray = constraint.toString().toUpperCase().toCharArray();
//                    Arrays.sort(charArray);
//
//                    if (dataNames.getOrderedMnemonic().toUpperCase().equals(new String(charArray).toUpperCase()))  {
//                        filteredArrayNames.add(dataNames);
//                    }
//                }
//
//                results.count = filteredArrayNames.size();
//                results.values = filteredArrayNames;
//
//                return results;
//            }
//        };
//
//        return filter;
    }
}
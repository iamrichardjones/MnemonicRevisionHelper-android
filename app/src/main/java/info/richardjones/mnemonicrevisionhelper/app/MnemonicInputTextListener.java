package info.richardjones.mnemonicrevisionhelper.app;

import android.text.Editable;
import android.text.TextWatcher;

public class MnemonicInputTextListener implements TextWatcher {
    private final MnemonicResultsArrayAdapter adapter;

    public MnemonicInputTextListener(MnemonicResultsArrayAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}

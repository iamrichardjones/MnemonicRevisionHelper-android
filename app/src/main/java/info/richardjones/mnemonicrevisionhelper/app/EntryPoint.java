package info.richardjones.mnemonicrevisionhelper.app;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import info.richardjones.mnemonicrevisionhelper.app.loader.*;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layoutMain = new LinearLayout(this);
        layoutMain.setOrientation(LinearLayout.VERTICAL);
        setContentView(layoutMain);

        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layoutTop = (LinearLayout) inflate.inflate(R.layout.mnemonic_input, null);
        ListView layoutBottom = (ListView) inflate.inflate(R.layout.results_list, null);

        layoutMain.addView(layoutTop);
        layoutMain.addView(layoutBottom);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final MnemonicResultsArrayAdapter adapter = new MnemonicResultsArrayAdapter(this, loadData());
        listview.setAdapter(adapter);

        final EditText userInput = (EditText) findViewById(R.id.mnemonic_input);
        userInput.addTextChangedListener(new MnemonicInputTextListener(adapter));
    }

    private List<MatchingMnemonic> loadData() {
        List<MatchingMnemonic> res = new ArrayList();
        HardCodedMnemonicSloganMapLoader sloganMapLoader = new HardCodedMnemonicSloganMapLoader();
        sloganMapLoader.load(res);
        HardCodedMnemonicSongMap1Loader songMap1Loader = new HardCodedMnemonicSongMap1Loader();
        songMap1Loader.load(res);
        HardCodedMnemonicSongMap2Loader songMap2Loader = new HardCodedMnemonicSongMap2Loader();
        songMap2Loader.load(res);
        HardCodedMnemonicSongMap3Loader songMap3Loader = new HardCodedMnemonicSongMap3Loader();
        songMap3Loader.load(res);
        HardCodedMnemonicSongMap4Loader songMap4Loader = new HardCodedMnemonicSongMap4Loader();
        songMap4Loader.load(res);

        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_point, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutBoxDialogFragment();
            newFragment.show(getSupportFragmentManager(), "about");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

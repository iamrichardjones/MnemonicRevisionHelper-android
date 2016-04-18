package info.richardjones.mnemonicrevisionhelper.app;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import info.richardjones.mnemonicrevisionhelper.app.loader.*;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class EntryPoint extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_page);

        mAdView = (AdView) findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final MnemonicResultsArrayAdapter adapter = new MnemonicResultsArrayAdapter(this, loadData());
        listview.setAdapter(adapter);

        final EditText userInput = (EditText) findViewById(R.id.mnemonic_input);
        userInput.addTextChangedListener(new MnemonicInputTextListener(adapter));
    }

    private List<MatchingMnemonic> loadData() {
        List<MatchingMnemonic> res = new ArrayList();
        new HardCodedMnemonicSloganMapLoader().load(res);
        new HardCodedMnemonicSongMap1Loader().load(res);
        new HardCodedMnemonicSongMap2Loader().load(res);
        new HardCodedMnemonicSongMap3Loader().load(res);
        new HardCodedMnemonicSongMap4Loader().load(res);

        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry_point, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutBoxDialogFragment();
            newFragment.show(getSupportFragmentManager(), "ABOUT_KEY");
            return true;
        }

        if (id == R.id.action_setting) {
            Intent intent = new Intent(EntryPoint.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
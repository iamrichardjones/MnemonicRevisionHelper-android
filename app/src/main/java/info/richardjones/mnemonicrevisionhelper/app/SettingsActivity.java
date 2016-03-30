package info.richardjones.mnemonicrevisionhelper.app;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class SettingsActivity extends AppCompatActivity {

    public static final String USE_SLOGAN_PREFERENCES = "use_slogan_preference";
    public static final String USE_SONG_TITLE_PREFERENCES = "use_song_titles_preference";
    public static final String USE_COMPLLETE_SEARCH_PREFERENCES = "use_complete_search_preference";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry_point, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutBoxDialogFragment();
            newFragment.show(getSupportFragmentManager(), "ABOUT_KEY");
            return true;
        }

        if (id == R.id.action_entry_point) {
            Intent intent = new Intent(SettingsActivity.this, EntryPoint.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addListenerOnButton() {
        final Switch switchButton = (Switch) findViewById(R.id.switch_id);

        switchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(switchButton.getText());
                result.append("\nline2");

                Toast.makeText(SettingsActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();

            }

        });
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }

}
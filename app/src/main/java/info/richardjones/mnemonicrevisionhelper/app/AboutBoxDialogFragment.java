package info.richardjones.mnemonicrevisionhelper.app;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutBoxDialogFragment extends DialogFragment {

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:s");

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setMessage(R.string.dialog_about)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //close
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

package me.openphoto.android.app.ui.widget;

import java.io.Serializable;

import me.openphoto.android.app.R;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Basic Yes/No dialog fragment
 * 
 * @author Eugene Popovich
 */
public class YesNoDialogFragment extends DialogFragment
{
    public static interface YesNoButtonPressedHandler extends Serializable
    {
        void yesButtonPressed(DialogInterface dialog);

        void noButtonPressed(DialogInterface dialog);
    }

    public static YesNoDialogFragment newInstance(
            int message,
            YesNoButtonPressedHandler handler)
    {
        YesNoDialogFragment frag = new YesNoDialogFragment();
        Bundle args = new Bundle();
        args.putInt("message", message);
        args.putSerializable("handler", handler);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        int message = getArguments().getInt("message");
        final YesNoButtonPressedHandler handler = (YesNoButtonPressedHandler) getArguments()
                .getSerializable("handler");
        return new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int whichButton)
                            {
                                if (handler != null)
                                {
                                    handler.yesButtonPressed(dialog);
                                }
                            }
                        }
                )
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int whichButton)
                            {
                                if (handler != null)
                                {
                                    handler.noButtonPressed(dialog);
                                }
                            }
                        }
                )
                .create();
    }
}

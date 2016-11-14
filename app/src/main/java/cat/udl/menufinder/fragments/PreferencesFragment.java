package cat.udl.menufinder.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;

import static cat.udl.menufinder.enums.UserType.CLIENT;

public class PreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (((MasterActivity) getActivity()).getMasterApplication().getUserType() == CLIENT)
            addPreferencesFromResource(R.xml.settings_client);
        else addPreferencesFromResource(R.xml.settings_restaurant);
    }
}

package cat.udl.menufinder.application;

import android.support.v4.app.Fragment;

public class MasterFragment extends Fragment {

    public MasterApplication getMasterApplication() {
        return ((MasterActivity) getActivity()).getMasterApplication();
    }

    public void showToast(String text) {
        ((MasterActivity) getActivity()).showToast(text);
    }

    public void showToast(int id) {
        ((MasterActivity) getActivity()).showToast(id);
    }
}

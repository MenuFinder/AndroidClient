package cat.udl.menufinder.application;

import android.app.Fragment;

public class MasterFragment extends Fragment {

    public void showToast(String text) {
        ((MasterActivity) getActivity()).showToast(text);
    }

    public void showToast(int id) {
        ((MasterActivity) getActivity()).showToast(id);
    }
}

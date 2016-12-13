package cat.udl.menufinder.application;

import android.app.Fragment;

import cat.udl.menufinder.database.DBManager;

public class MasterFragment extends Fragment {

    public MasterApplication getMasterApplication() {
        return ((MasterActivity) getActivity()).getMasterApplication();
    }

    public DBManager getDbManager() {
        return getMasterApplication().getDbManager();
    }

    public void showToast(String text) {
        ((MasterActivity) getActivity()).showToast(text);
    }

    public void showToast(int id) {
        ((MasterActivity) getActivity()).showToast(id);
    }
}

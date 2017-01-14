package cat.udl.menufinder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.RegisterActivity;
import cat.udl.menufinder.application.MasterFragment;

public class ImportantInformationFragment extends MasterFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_important_information, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spanned html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = Html.fromHtml(getString(R.string.important_information_text), Html.FROM_HTML_MODE_LEGACY);
        } else {
            html = Html.fromHtml(getString(R.string.important_information_text));
        }
        ((TextView) view.findViewById(android.R.id.text1)).setText(html);
        view.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        startActivity(new Intent(getActivity(), RegisterActivity.class));
        getActivity().finish();
    }
}

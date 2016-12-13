package cat.udl.menufinder.utils;

import android.content.Intent;

public class Utils {

    public static Intent getShareIntent(String shareText) {
        Intent shareIntent;
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/*");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
        return shareIntent;
    }
}

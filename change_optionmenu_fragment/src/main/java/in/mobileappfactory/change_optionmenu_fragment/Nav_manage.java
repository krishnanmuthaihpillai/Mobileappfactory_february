package in.mobileappfactory.change_optionmenu_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tis44 on 25/2/16.
 */

public class Nav_manage extends Fragment {
    public static Fragment newInstance(Context context) {
        Nav_manage f = new Nav_manage();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.f_nav_manage, null);
        return root;
    }
}

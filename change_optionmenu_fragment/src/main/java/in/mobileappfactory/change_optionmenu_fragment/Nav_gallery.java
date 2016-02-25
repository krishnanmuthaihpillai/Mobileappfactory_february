package in.mobileappfactory.change_optionmenu_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by tis44 on 25/2/16.
 */
public class Nav_gallery extends Fragment {
    public static Fragment newInstance(Context context) {
        Nav_gallery f = new Nav_gallery();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.f_nav_gallery, null);
//        setHasOptionsMenu(false);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings1:
                // Not implemented here
                make_toast("1action_settings1");
                return false;
            case R.id.action_settings2:
                make_toast("1action_settings2");
                // Do Fragment menu item stuff here
                return true;
            case R.id.action_settings3:
                make_toast("1action_settings3");
                // Do Fragment menu item stuff here
                return true;
            case R.id.action_settings4:
                make_toast("1action_settings4");
                // Do Fragment menu item stuff here
                return true;
            case R.id.action_settings5:
                make_toast("1action_settings5");
                // Do Fragment menu item stuff here
                return true;
            case R.id.action_settings6:
                make_toast("1action_settings6");
                // Do Fragment menu item stuff here
                return true;
            default:
                break;
        }

        return false;
    }

    private void make_toast(String str) {
        Toast.makeText(getContext(),""+str, Toast.LENGTH_LONG).show();
    }
}


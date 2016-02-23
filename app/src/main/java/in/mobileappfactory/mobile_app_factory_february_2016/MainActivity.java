package in.mobileappfactory.mobile_app_factory_february_2016;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MainActivity extends AppCompatActivity {
    Button get_button;
    TextView tv;
    ListView ls;
    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        String[] contact_array = get_avail_contacts();
    }

    private void init() {
        get_button = (Button) findViewById(R.id.get_button);
        tv = (TextView) findViewById(R.id.textView);
        ls = (ListView) findViewById(R.id.listView);
        get_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "dddddddd", Toast.LENGTH_LONG).show();
                showContacts();
            }


        });
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
            al=get_avail_contacts();
//            String []dsf = new String[al.size()];
//            al.toArray(dsf);
            System.out.println("" + al);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }


//    private void displayContacts() {
//        get_avail_contacts();
//
//    }

    public ArrayList<ArrayList<String>> get_avail_contacts() {
        ArrayList<ArrayList<String>> nodes = new ArrayList<ArrayList<String>>();
        StringBuffer sb = new StringBuffer();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                ArrayList<String> comb_ary_list = new ArrayList<String>();
                String phone = null;
                String name=null;
                 name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    ArrayList<String> name_ary_list = new ArrayList<String>();
                    ArrayList<String> number_ary_list = new ArrayList<String>();
                    name_ary_list.add(name);
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))}, null);
                    while (pCur.moveToNext()) {
                        phone = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // remove unwanted symbols from numbers
                        String ph_number=get_number(phone);
                        number_ary_list.add(ph_number);
                    }
                    pCur.close();
                    // Remove duplicates from array list
                    number_ary_list = new ArrayList<String>(new LinkedHashSet<String>(number_ary_list));
                    //add name array to combine array
                    comb_ary_list.addAll(name_ary_list);
                    //add number array to combine array
                    comb_ary_list.addAll(number_ary_list);
                    name_ary_list.clear();
                    number_ary_list.clear();
                }
                //finally add combined array list with main array list
               if(!comb_ary_list.isEmpty()){
                   nodes.add(comb_ary_list);
               }
            }
            cur.close();
//            System.out.println("" + nodes);
        }
        return nodes;
    }

    private String get_number(String phone) {
        String numberRefined = phone.replaceAll("[^\\d+]", "");
        numberRefined.replaceAll("\\s+","");
		return 	numberRefined;
    }

}

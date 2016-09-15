package com.carouselview;

import android.Manifest;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.carousel.CarouselView;
import com.carouselview.panel.ImagePanel;
import com.carouselview.panel.ListLayoutPanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by binary on 3/8/16.
 */
public class CarouselFragment extends Fragment implements ListLayoutPanel.OnScrollListener , View.OnClickListener {

    public static CarouselFragment newInstance() {
        Bundle args = new Bundle();

        CarouselFragment fragment = new CarouselFragment();
        fragment.setArguments(args);
        return fragment;
    }

    List<View> result;

    private CarouselView mCarouselView;

  //
    private ArrayList<ContactData> marray;
    // Identifier for the permission request
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// In an actual app, you'd want to request a permission when the user performs an action
        // that requires that permission.
        getPermissionToReadUserContacts();

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View  mRoot =  inflater.inflate(R.layout.fragment_carousel, null, false);
        mRoot.findViewById(R.id.letterA1).setOnClickListener(this);
        mRoot.findViewById(R.id.letterB2).setOnClickListener(this);
        mRoot.findViewById(R.id.letterC3).setOnClickListener(this);
        mRoot.findViewById(R.id.letterD4).setOnClickListener(this);
        mRoot.findViewById(R.id.letterE5).setOnClickListener(this);
        mRoot.findViewById(R.id.letterF6).setOnClickListener(this);
        mRoot.findViewById(R.id.letterG7).setOnClickListener(this);
        mRoot.findViewById(R.id.letterH8).setOnClickListener(this);
        mRoot.findViewById(R.id.letterI9).setOnClickListener(this);
        mRoot.findViewById(R.id.letterJ10).setOnClickListener(this);
        mRoot.findViewById(R.id.letterK11).setOnClickListener(this);
        mRoot.findViewById(R.id.letterL12).setOnClickListener(this);
        mRoot.findViewById(R.id.letterM13).setOnClickListener(this);
        mRoot.findViewById(R.id.letterN14).setOnClickListener(this);
        mRoot.findViewById(R.id.letterO15).setOnClickListener(this);
        mRoot.findViewById(R.id.letterP16).setOnClickListener(this);
        mRoot.findViewById(R.id.letterQ17).setOnClickListener(this);
        mRoot.findViewById(R.id.letterR18).setOnClickListener(this);
        mRoot.findViewById(R.id.letterS19).setOnClickListener(this);
        mRoot.findViewById(R.id.letterT20).setOnClickListener(this);
        mRoot.findViewById(R.id.letterU21).setOnClickListener(this);
        mRoot.findViewById(R.id.letterV22).setOnClickListener(this);
        mRoot.findViewById(R.id.letterW23).setOnClickListener(this);
        mRoot.findViewById(R.id.letterX24).setOnClickListener(this);
        mRoot.findViewById(R.id.letterY25).setOnClickListener(this);
        mRoot.findViewById(R.id.letterZ26).setOnClickListener(this);
readContacts();
        initStubItems();



        return mRoot;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCarouselView = (CarouselView) view.findViewById(R.id.carouselView);
        for (View stubItem : result) {
            mCarouselView.addView(stubItem);
        }

        mCarouselView.notifyDataSetChanged();
       // mCarouselView.setCarouselDrawingPanelsEnabled(true);

        view.findViewById(R.id.prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mCarouselView.getSelectedItemPosition() == 0 ? mCarouselView.getCount() - 1 : mCarouselView.getSelectedItemPosition() - 1;
                mCarouselView.scrollToChild(position);
                mCarouselView.invalidate();


            }
        });

        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mCarouselView.getSelectedItemPosition() == mCarouselView.getCount() - 1 ? 0 : mCarouselView.getSelectedItemPosition() + 1;
                mCarouselView.scrollToChild(position);
                mCarouselView.invalidate();
            }
        });
    }


    // Stub items
    private List<View> initStubItems() {
         result = new ArrayList<>();

        Iterator<ContactData> iter = marray.iterator();

        while (iter.hasNext()){

            ContactData tek;
            tek = iter.next();

            ImagePanel b = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
             b = new ImagePanel(getContext());
            }

            b.setImageResId(tek.geturi());
            result.add(b);

        }
       /* b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.natasha);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);


        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);

        b = new ImagePanel(getContext());
        b.setImageResId(R.drawable.tor);

        result.add(b);
*/


        return result;
    }

    @Override
    public void onScroll() {
        mCarouselView.invalidate();
    }

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public Uri getPhotoUri( String contactId) {
        try {
            Cursor cur = this.getContext().getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=" + contactId + " AND "
                            + ContactsContract.Data.MIMETYPE + "='"
                            + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'", null,
                    null);
            if (cur != null) {
                if (!cur.moveToFirst()) {
                    return null; // no photo
                }
            } else {
                return null; // error in cursor process
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long
                .parseLong(contactId));
        Uri returValue =  Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

        return  returValue;
    }

    public void readContacts(){

        marray = new ArrayList<>();

        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {




                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {



                    ContactData cd;
                    cd = new ContactData();
                    marray.add(cd);
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    cd.seturi(getPhotoUri(id));

                    System.out.println("name : " + name + ", ID : " + id);
                    cd.setName(name);///////////
                    // get the phone number
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phone = pCur.getString(
                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        System.out.println("phone" + phone);
                        cd.setNumber(phone);

                        if(!validateSelectedPhoneIsDomestic(phone)){
                            Toast.makeText(getActivity(),
                                    getResources().getString(R.string.not_valid_mobile_phone_number), Toast.LENGTH_LONG).show();
                            return;
                        }

                    }
                    pCur.close();

/*
                    // get email and type

                    Cursor emailCur = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCur.moveToNext()) {
                        // This would allow you get several email addresses
                        // if the email addresses were stored in an array
                        String email = emailCur.getString(
                                emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        String emailType = emailCur.getString(
                                emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));

                        System.out.println("Email " + email + " Email Type : " + emailType);
                    }
                    emailCur.close();*/
/*
                    // Get note.......
                    String noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] noteWhereParams = new String[]{id,
                            ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
                    Cursor noteCur = cr.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
                    if (noteCur.moveToFirst()) {
                        String note = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
                        System.out.println("Note " + note);
                    }
                    noteCur.close();*/

                    //Get Postal Address....
/*
                    String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] addrWhereParams = new String[]{id,
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};
                    Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI,
                            null, null, null, null);
                    while(addrCur.moveToNext()) {
                        String poBox = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                        String street = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                        String city = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                        String state = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                        String postalCode = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                        String country = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                        String type = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));

                        // Do something with these....

                    }
                    addrCur.close();*/

                    // Get Instant Messenger.........
                  /*  String imWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] imWhereParams = new String[]{id,
                            ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE};
                    Cursor imCur = cr.query(ContactsContract.Data.CONTENT_URI,
                            null, imWhere, imWhereParams, null);
                    if (imCur.moveToFirst()) {
                        String imName = imCur.getString(
                                imCur.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA));
                        String imType;
                        imType = imCur.getString(
                                imCur.getColumnIndex(ContactsContract.CommonDataKinds.Im.TYPE));
                    }
                    imCur.close();*/

                    // Get Organizations.........
/*
                    String orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
                    String[] orgWhereParams = new String[]{id,
                            ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
                    Cursor orgCur = cr.query(ContactsContract.Data.CONTENT_URI,
                            null, orgWhere, orgWhereParams, null);
                    if (orgCur.moveToFirst()) {
                        String orgName = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
                        String title = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));
                    }
                    orgCur.close();*/
                }
            }
        }
    }



    private ArrayList<String> getValidPhoneNumbers(Cursor cursorPhone){
        ArrayList<String> list = new ArrayList<String>();
        while(cursorPhone.moveToNext()){
            String mobileNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if(validateSelectedPhoneIsDomestic(mobileNumber)) {
                list.add (mobileNumber);
            }
        }
        return list;
    }
    private boolean validateSelectedPhoneIsDomestic(String phone){
        phone = phone.trim().replace(" ","").replace("(","").replace(")","").replace("-","");
        if(phone.startsWith("+381")){
            return true;
        }

        for(int i = 0; i <=9; i++){
            if(phone.startsWith("06"+i)){
                return true;
            }
        }

        return false;
    }










    @Override
    public void onClick(View v) {
        int position;
        switch (v.getId()){
            case R.id.letterA1:  position = mCarouselView.getSelectedItemPosition() == 0 ? mCarouselView.getCount() - 1 : mCarouselView.getSelectedItemPosition() - 1;
                mCarouselView.scrollToChild(position);//ovde samo pitam da li ima ime sa imenom koje pocinje na A i uzmem njegovu poziciju u idem na tu poziciju
                mCarouselView.invalidate(); break;
            case R.id.letterB2:  position = mCarouselView.getSelectedItemPosition() == 0 ? mCarouselView.getCount() - 1 : mCarouselView.getSelectedItemPosition() - 1;
                mCarouselView.scrollToChild(position);
                mCarouselView.invalidate();break;
            case R.id.letterC3: break;
            case R.id.letterD4: break;
            case R.id.letterE5: break;
            case R.id.letterF6: break;
            case R.id.letterG7: break;
            case R.id.letterH8: break;
            case R.id.letterI9: break;
            case R.id.letterJ10: break;
            case R.id.letterK11: break;
            case R.id.letterL12: break;
            case R.id.letterM13: break;
            case R.id.letterN14: break;
            case R.id.letterO15: break;
            case R.id.letterP16: break;
            case R.id.letterQ17: break;
            case R.id.letterR18: break;
            case R.id.letterS19: break;
            case R.id.letterT20: break;
            case R.id.letterU21: break;
            case R.id.letterV22: break;
            case R.id.letterW23: break;
            case R.id.letterX24: break;
            case R.id.letterY25: break;
            case R.id.letterZ26: break;

        }

    }
}



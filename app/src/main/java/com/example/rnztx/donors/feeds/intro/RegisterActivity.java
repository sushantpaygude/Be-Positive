package com.example.rnztx.donors.feeds.intro;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rnztx.donors.R;
import com.example.rnztx.donors.models.utils.Utilities;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * Created by sushant on 7/3/16.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

    private static String LOG_TAG = RegisterActivity.class.getSimpleName();
    private TextView tv1;
    private TextView tv2;
    private TextView txtName,txtEmail,txtWelcome,txtBloodgroup,txtPincode;
    private  EditText txtAddress,txtPhone;
    private Spinner bgSpinner,pcSpinner;
    private ImageView imgAvatar;
    protected Button signout,retrieve,register;
    public boolean STATUS_REG,STATUS_PHONE,STATUS_ADDRESS;
    private LinearLayout linearLayout;
    String fname, femail, faddress, hash ,fpincode, fphone, fbloodgroup, fpincodefull;
    int hashcode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Be Positive");
        tv1 = (TextView) findViewById(R.id.txtName1);
        tv2 = (TextView) findViewById(R.id.txtEmail1);
        imgAvatar = (ImageView) findViewById(R.id.imgProfilePic1);
        signout=(Button)findViewById(R.id.sign_out);
      //  retrieve=(Button)findViewById(R.id.btnretrieve);
        register=(Button)findViewById(R.id.btnregister);
        signout.setOnClickListener(this);
        //retrieve.setOnClickListener(this);
        register.setOnClickListener(this);


        txtName = (TextView) findViewById(R.id.txtName1);
        txtEmail = (TextView) findViewById(R.id.txtEmail1);
        txtWelcome=(TextView)findViewById(R.id.welcometxt);
        //txtRetrieve = (TextView) findViewById(R.id.retrievetxt);
        txtAddress=(EditText)findViewById(R.id.txt_Address);
        txtPhone=(EditText)findViewById(R.id.txt_Phone);

        bgSpinner=(Spinner)findViewById(R.id.bglist);
        pcSpinner=(Spinner)findViewById(R.id.pinlist);

        txtBloodgroup=(TextView)findViewById(R.id.txtBloodGroup);       //ADD
        txtPincode=(TextView)findViewById(R.id.txtPincode);             //ADD

//        set user info
        txtName.setText(Utilities.getUserDisplayName());
        txtEmail.setText(Utilities.getUserEmail());
        Picasso.with(this).load(Utilities.getUserPhotoUrl()).into(imgAvatar);
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        List<String> list=new ArrayList<String>();
        list.add("A+");
        list.add("A-");
        list.add("B+");
        list.add("B-");
        list.add("AB+");
        list.add("AB-");
        list.add("O+");
        list.add("O-");


        ArrayAdapter<String> bgadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        bgadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bgSpinner.setAdapter(bgadapter);

        List<String> list1=new ArrayList<>();


        list1.add("411001 (Pune H.O)");
        list1.add("411002 (Pune City H.O)");
        list1.add("411003 (Khadaki)");
        list1.add("411004 (Deccan Gymkhana)");
        list1.add("411005 (Shivajinagar H.O)");
        list1.add("411006 (Yerawada)");
        list1.add("411007 (Ganeshkind)");
        list1.add("411008 (N.C.L)");
        list1.add("411009 (Parvati)");
        list1.add("411010 (S.S.C Board)");
        list1.add("411011 (Kasba Peth)");
        list1.add("411012 (Dapodi)");
        list1.add("411013 (Hadapsar)");
        list1.add("411014 (Dunkirk Line)");
        list1.add("411015 (Dighi Camp)");
        list1.add("411016 (Model Colony)");
        list1.add("411017 (Pimpri Colony)");
        list1.add("411018 (Pimpri Penicillin Factory)");
        list1.add("411019 (Chinchwad E.)");
        list1.add("411020 (Range Hill)");
        list1.add("411021 (Armament)");
        list1.add("411022 (S.R.P.F)");
        list1.add("411023 (N.D.A)");
        list1.add("411024 (Khadakwasla)");
        list1.add("411025 (I.A.T Pune)");
        list1.add("411026 (Bhosari)");
        list1.add("411027 (Aundh)");
        list1.add("411028 (Hadapsar)");
        list1.add("411029 (Kothrud)");
        list1.add("411030 (S.P College)");
        list1.add("411031 (C.M.E)");
        list1.add("411032 (I.A.F Station)");
        list1.add("411033 (Chinchwad Gaon)");
        list1.add("411034 (Kasarwadi)");
        list1.add("411035 (Akurdi)");
        list1.add("411036 (Mundhwa)");
        list1.add("411037 (Market Yard)");
        list1.add("411038 (Ex. Service Man Colony)");
        list1.add("411039 (Bhosari Gaon)");
        list1.add("411040 (Wanawadi)");
        list1.add("411041 (Wadgaon Budruk)");
        list1.add("411042 (Swargate)");
        list1.add("411043 (Dhankawadi)");
        list1.add("411044 (Pimpri Chinchwad)");
        list1.add("411045 (Pashan)");
        list1.add("411046 (Katraj)");
        list1.add("411047 (Lohgaon)");
        list1.add("411048 (N.I.B.M)");
        list1.add("411051 (Anandnagar)");
        list1.add("411052 (Navasahyadri)");
        list1.add("411053 (Shivaji HSG Society)");


        ArrayAdapter<String> pcadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
        pcSpinner.setAdapter(pcadapter);
//        pcSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/Bariol_Regular.otf");    //ADD below 10 lines
        txtName.setTypeface(typeface);
        txtEmail.setTypeface(typeface);
        txtWelcome.setTypeface(typeface);
        register.setTypeface(typeface);
        signout.setTypeface(typeface);
        txtAddress.setTypeface(typeface);
        txtPhone.setTypeface(typeface);
        txtBloodgroup.setTypeface(typeface);
        txtPincode.setTypeface(typeface);

    }

    public void onClick(View v)
    {
      switch (v.getId()){
          case R.id.btnregister:
                storedata();
                if(STATUS_PHONE==true && STATUS_ADDRESS==true)
                {
//                    Intent i=new Intent(this,MainActivity.class);
//                    startActivity(i);
                    Log.e(LOG_TAG,faddress+" "+fbloodgroup);
                }
              break;
      }
    }

    protected void onStart() {
        super.onStart();

    }





    public void storedata() {
        Firebase ref = new Firebase("https://donordb.firebaseIO.com//web/data");

        fname = txtName.getText().toString().trim();
//        femail = txtEmail.getText().toString().trim();
        faddress = txtAddress.getText().toString().trim();
        fphone = txtPhone.getText().toString().trim();
        fbloodgroup = bgSpinner.getSelectedItem().toString();
        fpincodefull = pcSpinner.getSelectedItem().toString();
        fpincode = fpincodefull.substring(0, 6);

        STATUS_PHONE = isValidMobile(fphone);
        STATUS_ADDRESS=isValidAddress(faddress);

    }



    private boolean isValidMobile(String phone)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length()==10)
            {
                check = true;

            }
            else
            {
                check = false;
                txtPhone.setError("Please Enter a Valid Number");
            }
        }
        else
        {
            check=false;
        }
        return check;
    }



    private boolean isValidAddress(String address)
    {

        boolean check=false;
        int len;
        len=address.length();
        Log.e("Len :",""+len);
        if(len>0)
            {
                check=true;
            }

            else
            {
                check=false;
                txtAddress.setError("Please Enter a Valid Address");
            }

        return check;
    }


}


package com.tuandat.danhba;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tuandat.danhba.adapter.ContactAdapter;
import com.tuandat.danhba.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contacts;
    ContactAdapter contactAdapter;
    EditText edtName, edtPhoneNumber;
    RadioButton rbtnMale, rbtnFemale;
    ListView lsvContact;
    Button btnCall, btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>();
        rbtnFemale = (RadioButton) findViewById(R.id.rbtnFemale);
        rbtnMale = (RadioButton) findViewById(R.id.rbtnMale);
        lsvContact = (ListView) findViewById(R.id.lsvContact);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);

        contactAdapter = new ContactAdapter(this, R.layout.item_contact_listview, contacts);
        lsvContact.setAdapter(contactAdapter);
        lsvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogConfirm(i);
            }
        });

    }

    public void btnAddContact_Click(View view) {
        Contact contact = new Contact();
        String name = edtName.getText().toString().trim();
        String phone = edtPhoneNumber.getText().toString().trim();
        Boolean isMale = rbtnMale.isChecked();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
            contacts.add(new Contact(isMale, name, phone));
            edtName.setText("");
            edtPhoneNumber.setText("");
            rbtnMale.setChecked(true);
            contactAdapter.notifyDataSetChanged();
        } else
            Toast.makeText(this, "Please enter Name or Phone Number!", Toast.LENGTH_SHORT).show();

    }



    private void showDialogConfirm(final int i) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        btnCall = (Button) dialog.findViewById(R.id.btnCall);
        btnMessage = (Button) dialog.findViewById(R.id.btnMessage);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCall(i);
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessage(i);
            }
        });
        dialog.show();
    }

    private void intentMessage(int i) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contacts.get(i).getPhone()));
        startActivity(intent);
    }

    private void intentCall(int i) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + contacts.get(i).getPhone()));
        startActivity(intent);
    }
}

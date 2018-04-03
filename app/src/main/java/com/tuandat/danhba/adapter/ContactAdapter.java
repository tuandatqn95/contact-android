package com.tuandat.danhba.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuandat.danhba.R;
import com.tuandat.danhba.model.Contact;

import java.util.List;

/**
 * Created by tuand on 3/25/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrayContact;


    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvPhoneNumber = (TextView) convertView.findViewById(R.id.tvPhoneNumber);
            viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrayContact.get(position);
if(contact.isMale())
    viewHolder.imgAvatar.setBackgroundResource(R.drawable.ic_male);
else
    viewHolder.imgAvatar.setBackgroundResource(R.drawable.ic_female);
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvPhoneNumber.setText(contact.getPhone());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvPhoneNumber;
        ImageView imgAvatar;

    }
}

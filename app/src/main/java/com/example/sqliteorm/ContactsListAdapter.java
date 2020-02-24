package com.example.sqliteorm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    private Context context;
    private List<Contact> contacts = new ArrayList<>();

    public void setContacts(@NonNull List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public ContactsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact currentContact = contacts.get(position);
        if (currentContact != null) {
            holder.contactLastNameView.setText(currentContact.getLastName());
            holder.contactFirstNameView.setText(currentContact.getFirstName());
            holder.contactMiddleNameView.setText(currentContact.getMiddleName());
            holder.contactAgeView.setText(Integer.toString(currentContact.getAge()));
        } else {
            holder.contactLastNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactFirstNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactMiddleNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactAgeView.setText(context.getResources().getString(R.string.no_information));
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        final TextView contactLastNameView;
        final TextView contactFirstNameView;
        final TextView contactMiddleNameView;
        final TextView contactAgeView;


        public ContactViewHolder(View itemView) {
            super(itemView);
            contactLastNameView = itemView.findViewById(R.id.last_name_text_view);
            contactFirstNameView = itemView.findViewById(R.id.first_name_text_view);
            contactMiddleNameView = itemView.findViewById(R.id.middle_name_text_view);
            contactAgeView = itemView.findViewById(R.id.age_text_view);
        }
    }
}





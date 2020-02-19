
/* 

package com.example.sqliteorm; 

import android.content.Context; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.TextView; 

import androidx.recyclerview.widget.RecyclerView; 

import java.util.List; 

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> { 
private final LayoutInflater inflater; 
private Contact[] contacts = new Contact[0]; 

class ContactViewHolder extends RecyclerView.ViewHolder { 
private final TextView contactLastNameView; 
private final TextView contactFirstNameView; 
private final TextView contactMiddleNameView; 
private final TextView contactAgeView; 

private ContactViewHolder(View itemView) { 
super(itemView); 
contactLastNameView = itemView.findViewById(R.id.last_name_text_view); 
contactFirstNameView = itemView.findViewById(R.id.first_name_text_view); 
contactMiddleNameView = itemView.findViewById(R.id.middle_name_text_view); 
contactAgeView = itemView.findViewById(R.id.age_text_view); 
} 
} 

ContactsListAdapter(Context context) { 
inflater = LayoutInflater.from(context); 
} 

@Override 
public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { 
View itemView = inflater.inflate(R.layout.recycler_view, parent, false); 
return new ContactViewHolder(itemView); 
} 

@Override 
public void onBindViewHolder(ContactViewHolder holder, int position) { 
if (contacts.length == 0) { 
holder.contactLastNameView.setText(R.string.no_information); 
holder.contactFirstNameView.setText(R.string.no_information); 
holder.contactMiddleNameView.setText(R.string.no_information); 
holder.contactAgeView.setText(R.string.no_information); 
} else { 
Contact currentContact = contacts[position]; 
holder.contactLastNameView.setText(currentContact.getLastName()); 
holder.contactFirstNameView.setText(currentContact.getFirstName()); 
holder.contactMiddleNameView.setText(currentContact.getMiddleName()); 
holder.contactAgeView.setText(Integer.toString(currentContact.getAge())); 
} 
} 

void setContacts(Contact[] contacts) { 
this.contacts = contacts; 
notifyDataSetChanged(); 
} 

@Override 
public int getItemCount() { 
return contacts.length; 
} 
} 
*/

package com.example.sqliteorm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<Contact> contacts;

    ContactsListAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_view, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (contacts != null) {
            Contact currentContact = contacts.get(position);
            holder.contactLastNameView.setText(currentContact.getLastName());
            holder.contactFirstNameView.setText(currentContact.getFirstName());
            holder.contactMiddleNameView.setText(currentContact.getMiddleName());

            holder.contactAgeView.setText(Integer.toString(currentContact.getAge()));
        } else {
            holder.contactLastNameView.setText(R.string.no_information);
            holder.contactFirstNameView.setText(R.string.no_information);
            holder.contactMiddleNameView.setText(R.string.no_information);
            holder.contactAgeView.setText(R.string.no_information);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    void updateData(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactLastNameView;
        private final TextView contactFirstNameView;
        private final TextView contactMiddleNameView;
        private final TextView contactAgeView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactLastNameView = itemView.findViewById(R.id.last_name_text_view);
            contactFirstNameView = itemView.findViewById(R.id.first_name_text_view);
            contactMiddleNameView = itemView.findViewById(R.id.middle_name_text_view);
            contactAgeView = itemView.findViewById(R.id.age_text_view);
        }
    }
}











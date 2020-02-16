
package com.example.sqliteorm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeopleListAdapter extends RecyclerView.Adapter<PeopleListAdapter.PersonViewHolder> {
    private final LayoutInflater inflater;
    private List<Person> people;

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private final TextView personLastNameView;
        private final TextView personFirstNameView;
        private final TextView personMiddleNameView;
        private final TextView personAgeView;

        private PersonViewHolder(View itemView) {
            super(itemView);
            personLastNameView = itemView.findViewById(R.id.last_name_text_view);
            personFirstNameView = itemView.findViewById(R.id.first_name_text_view);
            personMiddleNameView = itemView.findViewById(R.id.middle_name_text_view);
            personAgeView = itemView.findViewById(R.id.age_text_view);
        }
    }

    PeopleListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_view, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        if (people != null) {
            Person currentPerson = people.get(position);
            holder.personLastNameView.setText(currentPerson.getLastName());
            holder.personFirstNameView.setText(currentPerson.getFirstName());
            holder.personMiddleNameView.setText(currentPerson.getMiddleName());
            holder.personAgeView.setText(Integer.toString(currentPerson.getAge()));
        } else {
            holder.personLastNameView.setText(R.string.no_information);
            holder.personFirstNameView.setText(R.string.no_information);
            holder.personMiddleNameView.setText(R.string.no_information);
            holder.personAgeView.setText(R.string.no_information);
        }
    }

    void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (people != null)
            return people.size();
        else return 0;
    }
}
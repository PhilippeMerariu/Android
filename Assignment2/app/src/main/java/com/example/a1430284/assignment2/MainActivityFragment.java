package com.example.a1430284.assignment2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a1430284.assignment2.Model.DatabaseException;
import com.example.a1430284.assignment2.Model.Note;
import com.example.a1430284.assignment2.Model.NoteDatabaseHandler;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Spinner sortCategory;
    private ListView listNotes;
    private ArrayAdapter<Note> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //UI Components
        sortCategory = (Spinner) root.findViewById(R.id.sort_Spinner);
        listNotes = (ListView) root.findViewById(R.id.noteList_ListView);

        adapter = new NoteDataAdapter(this.getContext());

        NoteDatabaseHandler dbh = new NoteDatabaseHandler(getContext());
        final List<Note> data;
        try  {
            data = dbh.getContactTable().readAll();
            adapter.addAll(data);

//            sortButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Collections.sort(data, new Comparator<Contact>() {
//                        @Override
//                        public int compare(Contact c1, Contact c2) {
//                            int r = c1.getLastName().compareTo(c2.getLastName());
//                            if(r == 0)
//                                return c1.getFirstName().compareTo(c2.getFirstName());
//                            return r;
//                        }
//                    });
//                    adapter.clear();
//                    adapter.addAll(data);  // automatically notifies the observers
//                }
//            });

//            shuffleButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Collections.shuffle(data);
//                    adapter.clear();
//                    adapter.addAll(data);  // automatically notifies the observers
//                }
//            });

            // change all user's lastnames to Foo...
//            fooButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    for(Contact c : data)
//                        c.setLastName("Foo");
//                    adapter.notifyDataSetChanged(); // without this, the list doesn't reflect the data change right away
//                }
//            });

        }
        catch (DatabaseException e) {
            e.printStackTrace();
        }

        listNotes.setAdapter(adapter);



        return root;
    }

    private class NoteDataAdapter extends ArrayAdapter<Note> {
        public NoteDataAdapter(Context context) {
            super(context, -1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // inflate or reuse previously inflated UI
            View root;
            if(convertView != null)
                root = convertView;
            else {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                root = inflater.inflate(R.layout.list_item_contact, parent, false);
            }

            // Update UI components
            Note note = getItem(position);

            TextView firstName = (TextView) root.findViewById(R.id.firstName_TextView);
            TextView lastName = (TextView) root.findViewById(R.id.lastName_TextView);

            firstName.setText(note.getTitle());
            lastName.setText(note.getTitle());

            return root;
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId(); // use Contact IDs
        }
    }
}

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

        final List<Note> data = NoteData.getData();
        adapter.addAll(data);

        listNotes.setAdapter(adapter);


//        NoteDatabaseHandler dbh = new NoteDatabaseHandler(getContext());
//        NoteTable noteTable = dbh.getContactTable();
//        try {
//            noteTable.create(NoteData.getNoteById(0));
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//        }

        return root;
    }

    private class NoteDataAdapter extends ArrayAdapter<Note> {
        public NoteDataAdapter(Context context) {
            super(context, -1);
        }
    }
}

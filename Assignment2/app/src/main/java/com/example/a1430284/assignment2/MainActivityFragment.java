package com.example.a1430284.assignment2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Spinner sortCategory;
    ListView listNotes;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //UI Components
        sortCategory = (Spinner) root.findViewById(R.id.sort_Spinner);
        listNotes = (ListView) root.findViewById(R.id.noteList_ListView);


        return root;
    }
}

package project.akbaralzaini.laudryku.userInterface;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.akbaralzaini.laudryku.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifikasiFragment extends Fragment {


    public NotifikasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifikasi, container, false);
    }

}

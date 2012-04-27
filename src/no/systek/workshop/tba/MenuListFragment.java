package no.systek.workshop.tba;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MenuListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(ArrayAdapter.createFromResource(getActivity()
                .getApplicationContext(), R.array.tut_titles,
                R.layout.list_item));
    }
}

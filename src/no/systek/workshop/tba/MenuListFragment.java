package no.systek.workshop.tba;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MenuListFragment extends ListFragment {

    private OnSelectedListener tutSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void updateListData(ListAdapter data) {
        setListAdapter(data);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        tutSelectedListener.onMenuItemSelected(Long.toString(id), position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            tutSelectedListener = (OnSelectedListener) activity;
        } catch (ClassCastException e) {
            Log.e("TAG", "Bad class", e);
            throw new ClassCastException(activity.toString()
                    + " must implement OnTutSelectedListener");
        }
    }

}

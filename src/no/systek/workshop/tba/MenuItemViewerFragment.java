package no.systek.workshop.tba;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class MenuItemViewerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent launchingIntent = getActivity().getIntent();
        WebView viewer = (WebView) inflater.inflate(R.layout.menu_item_view, container, false);
        viewer.loadUrl("http://www.vg.no");
        return viewer;
    }

}

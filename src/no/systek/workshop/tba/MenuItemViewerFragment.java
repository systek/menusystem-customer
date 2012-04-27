package no.systek.workshop.tba;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MenuItemViewerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent launchingIntent = getActivity().getIntent();
        String title = launchingIntent.getStringExtra("name");
        String description = launchingIntent.getStringExtra("description");
        Bitmap image = launchingIntent.getExtras().getParcelable("image");
        String buyUrl = launchingIntent.getStringExtra("buyUrl");
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.menu_item_view, container, false);
        if (title != null) {
            ((TextView) layout.findViewById(R.id.title)).setText(title);
        } else {
            ((TextView) layout.findViewById(R.id.title)).setText("JALLLL");
        }
        if (description != null) {
            ((TextView) layout.findViewById(R.id.description)).setText(description);
        }
        if (image != null) {
            ((ImageView) layout.findViewById(R.id.previewImage)).setImageBitmap(image);
        }
        Button b = (Button) layout.findViewById(R.id.buyButton);
        try {
            b.setOnClickListener(new BuyClickListener(new URL(buyUrl)));
        } catch (MalformedURLException e) {
            Log.e("Error", "error", e);
        }
        return layout;
    }

}

package no.systek.workshop.tba;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class ImageArrayAdapter extends ArrayAdapter {

    private Map<String, ?>[] objects;

    public ImageArrayAdapter(Context applicationContext, int list_item, Map<String, ?>[] objects) {
        super(applicationContext, list_item, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = LayoutInflater.from(getContext());
        View view = vi.inflate(R.layout.list_item, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        if (textView != null) {
            textView.setText((CharSequence) objects[position].get("name"));
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        if (imageView != null && objects[position].containsKey("image")) {
            imageView.setImageBitmap((Bitmap) objects[position].get("image"));
        }
        return view;
    }
}

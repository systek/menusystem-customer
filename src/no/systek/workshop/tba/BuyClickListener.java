package no.systek.workshop.tba;

import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class BuyClickListener implements View.OnClickListener {

    private URL url;

    public BuyClickListener(URL url) {
        this.url = url;
    }

    @Override
    public void onClick(final View view) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URLConnection c = url.openConnection();
                    c.connect();
                    c.getContent();
                } catch (IOException e) {
                    Log.e("ERROR", "ERROR", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast toast = Toast.makeText(view.getContext(), "Kjøpt", 2000);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }.execute(null, null);
    }
}

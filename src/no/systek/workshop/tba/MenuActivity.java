package no.systek.workshop.tba;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends Activity implements OnSelectedListener {

    private String serverUrl;

    private String tableId;

    private Map[] menuItems;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverUrl = getIntent().getStringExtra("url");
        tableId = getIntent().getStringExtra("table");
        setContentView(R.layout.menu_fragments);
        try {
            new GetMenuTask(this).execute(new URL(serverUrl + "/menu/json/Drikke"));
        } catch (MalformedURLException e) {
            Log.e("ERRORO", "error", e);
        }

    }

    @Override
    public void onMenuItemSelected(String menuItemId, int position) {
        Intent showContent = new Intent(getApplicationContext(),
                MenuItemViewerActivity.class);
        showContent.putExtra("ID", menuItemId);
        showContent.putExtra("name", (CharSequence) menuItems[position].get("name"));
        showContent.putExtra("description", (CharSequence) menuItems[position].get("description"));
        showContent.putExtra("image", (Parcelable) menuItems[position].get("image"));
        showContent.putExtra("buyUrl", serverUrl + "/buy/buy_item/" + tableId + "?item=" + menuItems[position].get("id"));
        startActivity(showContent);
    }

    private void updateElements(List<?> categories) {
        menuItems = categories.toArray(new Map[0]);
        ArrayAdapter simpleAdapter = new ImageArrayAdapter(getApplicationContext(), R.layout.list_item, menuItems);
        ((MenuListFragment) getFragmentManager().findFragmentById(R.id.menu_list_fragment)).updateListData(simpleAdapter);
    }


    private class GetMenuTask extends AsyncTask<URL, Void, JSONArray> {

        private MenuActivity menuActivity;

        public GetMenuTask(MenuActivity menuActivity) {
            this.menuActivity = menuActivity;
        }


        protected JSONArray doInBackground(URL... urls) {
            for (URL url : urls) {
                try {
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    BufferedReader bos = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder b = new StringBuilder();
                    String c;
                    while ((c = bos.readLine()) != null) {
                        b.append(c);
                    }

                    return new JSONArray(b.toString());
                } catch (Exception e) {
                    Log.e("JSON", "Error", e);
                    throw new RuntimeException(e);
                }
            }
            throw new RuntimeException("Development.. Use just one url.");
        }

        protected void onPostExecute(JSONArray result) {
            Toast.makeText(getApplicationContext(), "Meny lastet", 2);

            List<Map<String, ?>> categories = new ArrayList<Map<String, ?>>();
            for (int i = 0; i < result.length(); i++) {
                try {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", ((JSONObject) result.get(i)).getString("name"));
                    map.put("image", decodeImage(((JSONObject) result.get(i)).getString("imageUrl")));
                    map.put("description", ((JSONObject) result.get(i)).getString("description"));
                    map.put("id", ((JSONObject) result.get(i)).getString("id"));
                    categories.add(map);
                } catch (JSONException e) {
                    Log.e("JSON", "Error", e);
                }
            }
            menuActivity.updateElements(categories);

        }

        private Bitmap decodeImage(String imageUrl) {
            if (imageUrl == null) {
                return null;
            }
            byte[] img = Base64.decode(imageUrl.replace("data:image/png;base64,", ""), Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(img, 0, img.length);
        }

    }


}

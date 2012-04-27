package no.systek.workshop.tba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MenuActivity extends Activity implements OnSelectedListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_fragments);
    }

    @Override
    public void onMenuItemSelected(String menuItemId) {
        Intent showContent = new Intent(getApplicationContext(),
                MenuItemViewerActivity.class);
        showContent.putExtra("ID", menuItemId);
        startActivity(showContent);
    }

}

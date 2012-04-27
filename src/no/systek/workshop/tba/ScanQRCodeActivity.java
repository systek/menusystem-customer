package no.systek.workshop.tba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import org.json.JSONException;
import org.json.JSONObject;

public class ScanQRCodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        findViewById(R.id.scanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                //intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
        findViewById(R.id.shortCutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMenuActivity("http://ybzm.showoff.io/menu-server", "1");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String contents = data.getStringExtra("SCAN_RESULT");
        try {
            JSONObject jsonObject = new JSONObject(contents);
            startMenuActivity(jsonObject.getString("url"), jsonObject.getString("id"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void startMenuActivity(String url, String table) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("table", table);
        startActivity(intent);
    }
}

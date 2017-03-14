package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ZXingDemoActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

  public void startZXing( View v )
  {
    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
    intent.putExtra("SAVE_HISTORY", false);
    startActivityForResult(intent, 0);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 0) {
      if (resultCode == RESULT_OK) {
	String contents = data.getStringExtra("SCAN_RESULT");
	((TextView)findViewById( R.id.tv0 )).setText( contents );
      }
      else if (resultCode == RESULT_CANCELED) {
	Toast.makeText( this, "Canceled", Toast.LENGTH_SHORT).show();
      }
    }
  }
  
}

package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;

public class IntentCameraDemo extends Activity
{

    private Bitmap bitmap;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

    	
    	if(resultCode != 0) {
    		Bundle extras = intent.getExtras();
    		bitmap = (Bitmap)extras.get("data");
    		Toast.makeText(getApplicationContext(), "Capture effectuée !", Toast.LENGTH_SHORT).show();

    		ImageView image_view = (ImageView)findViewById(R.id.image_view);
    		image_view.setImageBitmap(bitmap);
    	}
    	else {
    		Toast.makeText(getApplicationContext(), "Annulation", Toast.LENGTH_SHORT).show();
    	}
	
    }

    public void takePhoto(View view) {

	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	startActivityForResult(intent, 0);

    }

}

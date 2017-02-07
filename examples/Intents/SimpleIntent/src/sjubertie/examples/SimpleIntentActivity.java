package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class SimpleIntentActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void startAnotherActivity(View view)
    {
	    Log.i("SimpleIntentActivity", "button pressed");
	    startActivity(new Intent(this, SubActivity.class));
    }
}

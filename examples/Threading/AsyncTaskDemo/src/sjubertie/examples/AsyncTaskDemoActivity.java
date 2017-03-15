package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;


public class AsyncTaskDemoActivity extends Activity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick( View view )
  {
    view.setEnabled(false);
    Log.i("AsyncTaskTest", "onClick");
    new AsyncTaskTest().execute();
    Log.i("AsyncTaskTest", "execute");
  }

  public void toast( View view )
  {
    Toast.makeText( this, "toast", Toast.LENGTH_SHORT).show();
  }
    
  private class AsyncTaskTest extends AsyncTask<Void, Integer, Void>
  {
    AsyncTaskTest()
    {
      ( ( TextView )findViewById( R.id.tv0 ) ).setText("AsyncTask started...");	    
    }
    protected Void doInBackground(Void... params)
    {
      Log.i("AsyncTaskTest", "doInBackground enter");
      try {
	for( int i = 1 ; i < 6 ; ++i ) {
	  Thread.sleep(1000);
	  publishProgress(i);
	}
      }
      catch(Exception e) {
	Log.i("AsyncTaskTest", "InterruptedException" + e );
      }
      Log.i("AsyncTaskTest", "doInBackground exit");
      return null;
    }

    protected void onProgressUpdate( Integer... progress )
    {
      ( ( TextView )findViewById( R.id.tv0 ) ).setText("Progress = " + progress[0] );
    }
	
    protected void onPostExecute( Void result )
    {
      Log.i("AsyncTaskTest", "onPostExecute");
      ( ( TextView )findViewById( R.id.tv0 ) ).setText("AsyncTask terminated");
      findViewById( R.id.bt0 ).setEnabled( true );
    }
  }
}

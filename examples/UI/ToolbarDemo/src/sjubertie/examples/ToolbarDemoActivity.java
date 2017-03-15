package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.util.Log;


public class ToolbarDemoActivity extends Activity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Toolbar toolbar = (Toolbar)findViewById( R.id.toolbar );
    // Remplacement de l'ActionBar (Android 3.0 et >)
    // par la Toolbar (Android 5.0 et >).
    setActionBar( toolbar );
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Chargement du menu.
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.toolbar_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Gestion des évènements sur les items du menu.
    switch( item.getItemId() )
    {
    case R.id.item1:
      Toast.makeText( this, "Item1 clicked!", Toast.LENGTH_SHORT ).show();
      return true;
    case R.id.item2:
      Toast.makeText( this, "Item2 clicked!", Toast.LENGTH_SHORT ).show();
      return true;
    default:
      return super.onOptionsItemSelected( item );
    }
  }

}

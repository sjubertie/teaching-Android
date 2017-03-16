package sjubertie.examples;


import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import android.database.Cursor;

import java.util.List;

import android.util.Log;


public class SQLiteDemo extends Activity
{
  private DBOpenHelper dbopenhelper;

  private Cursor cursor;
  private SimpleCursorAdapter sca;
  
  private EditText et0;

  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Création/Ouverture de la base de données.
    dbopenhelper = new DBOpenHelper(this);

    et0 = (EditText)findViewById(R.id.et0);

    ListView lv0 = ( ListView )findViewById( R.id.lv0 );

    cursor = dbopenhelper.getCursor();

    String[] columns = {"value"};

    int[] views = {R.id.text1};
    
    sca = new SimpleCursorAdapter(this,
				  R.layout.simple_list_item_1,
				  cursor,
				  new String[] {"value"},
				  new int[] { R.id.text1 },
				  CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
				 );
    
    lv0.setAdapter( sca );
  }

  public void insertIntoDB( View v )
  {
    // Insertion dans la base de données.
    if( et0.getText().length() != 0 )
    {
      dbopenhelper.insertValue( et0.getText().toString() );
      cursor.requery();
      sca.notifyDataSetChanged();
    }
  }
}

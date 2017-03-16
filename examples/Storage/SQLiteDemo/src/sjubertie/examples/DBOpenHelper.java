package sjubertie.examples;

import android.app.Activity;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.widget.SimpleCursorAdapter;

import android.content.Context;
import android.content.ContentValues;

import android.util.Log;

import java.util.List;
import java.util.ArrayList;


public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB";             // Nom de la base.
    private static final String DB_TABLE_NAME = "valeurs";  // Nom de la table.

    private SQLiteDatabase db;                              // Base de données

    private Context context;
    
    DBOpenHelper(Context context) {
	// Appel au constructeur qui s'occupe de créer ou ouvrir la base.
	super(context, DB_NAME, null, 2);
	this.context = context;
	// Récupération de la base de données.
	db = getWritableDatabase();
    }

    /**
     * Méthode appelée si la base n'existe pas.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
	db.execSQL("create table " + DB_TABLE_NAME+ " (_id integer primary key autoincrement, value text not null);");
    }

    /**
     * Méthode pour passer d'une version de SQLite à une nouvelle version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

    }

    /**
     * Insertion d'une chaîne dans la table.
     */
    public void insertValue(String value) {
	// La chaîne n'est pas directement ajoutée dans la base.
	// Il faut passer par la création d'une structure intermédiaire ContenValues.
	ContentValues content = new ContentValues();
	// Insertion de la chaîne dans l'instance de ContentValues.
	content.put("value", value);

	// Insertion dans la base de l'instance de ContentValues contenant la chapine.
	db.insert(DB_TABLE_NAME, null, content);
    }

    /**
     * Récupération des chaînes de la table.
     */
    public List<String> getValues() {
	List<String> list = new ArrayList<String>();
	String[] columns = {"value"};
	// Exécution de la requête pour obtenir les chaînes et récupération d'un curseur sur ces données.
	Cursor cursor = db.query(DB_TABLE_NAME, columns, null, null, null, null, null);
	// Curseur placé en début des chaînes récupérées.
	cursor.moveToFirst();
	while (!cursor.isAfterLast()) {
	    // Récupération d'une chaîne et insertion dans une liste.
	    list.add(cursor.getString(0));
	    // Passage à l'entrée suivante.
	    cursor.moveToNext();
	}
	// Fermeture du curseur.
	cursor.close();

	return list;
    }

    public Cursor getCursor()
    {
      String[] columns = {"_id","value"};

      Cursor cursor = db.query(DB_TABLE_NAME, columns, null, null, null, null, null);
      //cursor.moveToFirst();
      
      Log.d("SQLiteDemo", "cursor " + cursor);

      return cursor;
    }
    
    public SimpleCursorAdapter getAdapter()
    {
	String[] columns = {"value"};
	int[] views = { R.id.text1 };
	Cursor cursor = db.query(DB_TABLE_NAME, columns, null, null, null, null, null);
	//cursor.moveToFirst();

	return new SimpleCursorAdapter(context,
				       R.layout.simple_list_item_1,
				       cursor,
				       columns,
				       views);

    }
    
}

package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class JNISimpleDemoActivity extends Activity
{
  // Prototype de la méthode native.
  private static native String hello();

  static
  {
    // Chargement de la bibliothèque dynamique libhello.so.
    System.loadLibrary( "hello" );
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    // Appel de la fonction native hello.
    ( (TextView)findViewById( R.id.tv0 ) ).setText( hello() );
  }
}

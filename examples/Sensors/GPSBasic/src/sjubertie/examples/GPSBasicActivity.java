package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;

import android.location.LocationManager;
import android.location.LocationListener;
import android.location.Location;

import android.widget.TextView;


public class GPSBasicActivity extends Activity implements LocationListener
{
  LocationManager locationManager;

  private TextView longitude;
  private TextView latitude;
  private TextView altitude;
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    longitude = (TextView)findViewById(R.id.longitude);
    latitude = (TextView)findViewById(R.id.latitude);
    altitude = (TextView)findViewById(R.id.altitude);
  }

  
  @Override
  protected void onPause()
  {
    super.onPause();
    locationManager.removeUpdates(this);
  }

  @Override
  protected void onResume()
  {
    super.onResume();
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
  }

  public void onProviderDisabled(String s)
  {}

  public void onProviderEnabled(String s)
  {}

  public void onStatusChanged(String provider, int status, Bundle extras)
  {}

  public void onLocationChanged(Location location)
  {
    longitude.setText("longitude=" + location.getLongitude());
    latitude.setText("latitude=" + location.getLatitude());
    altitude.setText("altitude=" + location.getAltitude());
  }

}

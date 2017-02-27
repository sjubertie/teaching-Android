package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;

import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;


public class AccelerometerDemoActivity extends Activity implements SensorEventListener
{
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView valueX;
    private TextView valueY;
    private TextView valueZ;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        TextView name = (TextView)findViewById(R.id.name);
				name.setText(accelerometer.getName());

				valueX = (TextView)findViewById(R.id.valueX);
				valueY = (TextView)findViewById(R.id.valueY);
				valueZ = (TextView)findViewById(R.id.valueZ);
    }
    
    /**
     * Désactivation de l'accéléromêtre lors de la mise en pause de l'application.
     * (pour des raisons d'autonomie)
     */
    @Override
    protected void onPause()
    {
			super.onPause();
			sensorManager.unregisterListener(this);
    }

    // Activation de l'accéléromêtre lors de la reprise de l'application
    @Override
    protected void onResume()
    {
			super.onResume();
			sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    // SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {}

    // SensorEventListener
    public void onSensorChanged(SensorEvent event)
    {
			if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
	    {
				valueX.setText("x=" + event.values[0]);
				valueY.setText("y=" + event.values[1]);
				valueZ.setText("z=" + event.values[2]);
	    }
    }

}

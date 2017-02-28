package sjubertie.examples;

import android.app.Activity;
import android.os.Bundle;

import android.hardware.SensorManager;
import android.hardware.Sensor;

import java.util.List;

import android.content.Context;

import android.widget.TextView;


public class SensorListActivity extends Activity
{
		private SensorManager sensor_manager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        List<Sensor> device_sensors = sensor_manager.getSensorList(Sensor.TYPE_ALL);
        
        TextView tv_devices = (TextView)findViewById( R.id.tv_devices );
        
        for( Sensor sensor: device_sensors)
        {
        		tv_devices.setText( sensor.getName() + "\n" + tv_devices.getText() );
        }
    }
}

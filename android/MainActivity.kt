package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener{
    private var sensorManager: SensorManager? = null

    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    private var gpsTracker: GpsTracker? = null
//    private var gpsTracker: GpsKalman? = null
    private var tvLatitude: TextView? = null
    private  var tvLongitude:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData()
        resetSteps()

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val stepSensor : Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }

        tvLatitude = findViewById<View>(R.id.latitude) as TextView
        tvLongitude = findViewById<View>(R.id.longitude) as TextView

        try {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    101
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


        gpsTracker = GpsTracker(this@MainActivity, tvLatitude, tvLongitude)
//        gpsTracker = GpsKalman(this@MainActivity, tvLatitude, tvLongitude)

    }


    override fun onResume() {
        super.onResume()
        running = true

        buttonStop.setEnabled(false)

        buttonStart.setOnClickListener{
            buttonStop.setEnabled(true)
            buttonStart.setEnabled(false)
            val activity = Entity("Example Caption")
            gpsTracker!!.displayLocation()

            gpsTracker!!.setActivity(activity)

            Toast.makeText(this, "Location started!", Toast.LENGTH_SHORT).show()

        }

        buttonStop.setOnClickListener{
            buttonStop.setEnabled(false)
            buttonStart.setEnabled(true)
            gpsTracker!!.removeActivity()

            Toast.makeText(this, "Stopped!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            totalSteps = event!!.values[0]
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            tv_stepsTaken.text = ("$currentSteps")

            progress_circular.apply {
                setProgressWithAnimation(currentSteps.toFloat())
            }
        }
    }


    private fun resetSteps() {
        tv_stepsTaken.setOnClickListener{
            Toast.makeText(this, "Lon tap to reset steps", Toast.LENGTH_SHORT).show()
        }

        tv_stepsTaken.setOnLongClickListener{
            previousTotalSteps = totalSteps
            tv_stepsTaken.text = 0.toString()
            progress_circular.setProgressWithAnimation(0F)
            saveData()
            true
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        Log.d("MainActivity", "$savedNumber")
        previousTotalSteps = savedNumber
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}
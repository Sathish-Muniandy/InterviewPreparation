package com.example.interviewpreparation.Map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.interviewpreparation.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private val locationRequestCode = 1000
    private var wayLatitude = 0.0
    private var wayLongitude = 0.0
    lateinit var map : GoogleMap
    var hasGps = false
    var hasNetwork = false

    var LatLngOne = LatLng(13.0694, 80.1948)
    var LatLngTwo = LatLng(13.0500, 80.2121)
    var LatLngthree = LatLng(13.0569, 80.2425)

    private var currentLocation: Location? = null
    lateinit var locationManager: LocationManager
    var locationByGps = Location("dummygpsprovider")
    var locationByNetwork = Location("dummynetworkprovider")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MapActivity)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)



        /* Toast.makeText(this@MapActivity, "call", Toast.LENGTH_SHORT).show()
         Handler(Looper.getMainLooper()).postDelayed({
             //Do something after 100ms
             getCurrentLocation()
         }, 3000)*/
    }

    override fun onResume() {
        super.onResume()
      //  Toast.makeText(this@MapActivity, "onresume", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1) {
            val hasGps1 = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val hasNetwork1 = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if(hasGps1 && hasNetwork1) {
                /*hasGps = hasGps1
                hasNetwork = hasNetwork1
                Toast.makeText(this@MapActivity, "success", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    //Do something after 100ms
                    checkGpsEnable()
                }, 5000)*/
                Handler(Looper.getMainLooper()).postDelayed({
                    //Do something after 100ms
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                    }
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location? ->
                            if(location!=null) {
                                val latitude = location.latitude
                                val longitude = location.longitude
                                val currentLatLng = LatLng(latitude,longitude)

                                map.addMarker(MarkerOptions().position(currentLatLng).title("Your Location"))
                                map.addPolyline(PolylineOptions().add(currentLatLng,
                                    LatLngOne,LatLngTwo,LatLngthree,currentLatLng)
                                    .width(5F)
                                    .color(Color.RED)
                                    .geodesic(true))
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,13F))

                                Toast.makeText(this@MapActivity, "Lat:"+latitude.toString()+" Long:"+
                                        longitude.toString(), Toast.LENGTH_SHORT).show()
                            }
                            // Got last known location. In some rare situations this can be null.
                        }


                }, 5000)
            }
           // Toast.makeText(this@MapActivity, "onActivityResult", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkGpsEnable() {
        if(!hasGps) {
            if(isLocationPermissionGranted()) {
                val intent1 = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent1,1);
            }else {
                isLocationPermissionGranted()
            }
        }else {
            gpsAndNetworkListener()
        }
    }

    override fun onMapReady(gMap: GoogleMap?) {
       // Toast.makeText(this@MapActivity, "mapready", Toast.LENGTH_SHORT).show()
        map = gMap!!
        checkGpsEnable()
    }

    fun gpsAndNetworkListener() {


        if (hasGps) {
            val gpsLocationListener: LocationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    locationByGps= location
                }
                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }


            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                gpsLocationListener
            )
        }
        if (hasNetwork) {
            val networkLocationListener: LocationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    locationByNetwork= location
                }
                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                networkLocationListener
            )
        }

        val lastKnownLocationByGps =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        lastKnownLocationByGps?.let {
            locationByGps = lastKnownLocationByGps
        }

        val lastKnownLocationByNetwork =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        lastKnownLocationByNetwork?.let {
            locationByNetwork = lastKnownLocationByNetwork
        }

        if (locationByGps != null && locationByNetwork != null) {
            if (locationByGps.accuracy > locationByNetwork!!.accuracy) {
                currentLocation = locationByGps
                addMarkerAndPolyLine(currentLocation!!)
//                latitude = currentLocation.latitude
//                longitude = currentLocation.longitude
            } else {
                currentLocation = locationByNetwork
                addMarkerAndPolyLine(currentLocation!!)
//                latitude = currentLocation.latitude
//                longitude = currentLocation.longitude
            }
        }

    }

    fun addMarkerAndPolyLine(location : Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        val currentLatLng = LatLng(latitude,longitude)

        map.addMarker(MarkerOptions().position(currentLatLng).title("Your Location"))
        map.addPolyline(PolylineOptions().add(currentLatLng,
            LatLngOne,LatLngTwo,LatLngthree,currentLatLng)
            .width(5F)
            .color(Color.RED)
            .geodesic(true))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,13F))

        Toast.makeText(this@MapActivity, "Lat:"+latitude.toString()+" Long:"+
                longitude.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION),
                100)
            false
        } else {
            true
        }
    }










    fun getCurrentLocation() {
        Toast.makeText(this@MapActivity, "call delay", Toast.LENGTH_SHORT).show()
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), locationRequestCode)
        } else {
            // already permission granted
           /* fusedLocationClient.lastLocation.addOnSuccessListener {
                if(it!=null) {
                    val latitude = it.latitude
                    val longitude = it.longitude
                    Toast.makeText(this@MapActivity, "Lat:"+latitude.toString()+" Long:"+
                            longitude.toString(), Toast.LENGTH_SHORT).show()
                }
            }*/

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    if(location!=null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        val currentLatLng = LatLng(latitude,longitude)

                        map.addMarker(MarkerOptions().position(currentLatLng).title("Your Location"))
                        map.addPolyline(PolylineOptions().add(currentLatLng,
                        LatLngOne,LatLngTwo,LatLngthree,currentLatLng)
                            .width(5F)
                            .color(Color.RED)
                            .geodesic(true))
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,13F))

                        Toast.makeText(this@MapActivity, "Lat:"+latitude.toString()+" Long:"+
                                longitude.toString(), Toast.LENGTH_SHORT).show()
                    }
                    // Got last known location. In some rare situations this can be null.
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    getCurrentLocation()
                }
                else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            100 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    checkGpsEnable()
                }
                else {
                  //  checkGpsEnable()
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
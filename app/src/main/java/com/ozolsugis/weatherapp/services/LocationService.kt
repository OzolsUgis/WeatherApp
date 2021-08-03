package com.ozolsugis.weatherapp.services


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.ozolsugis.weatherapp.util.Constants.LOCATION_PERMISSION_REQUEST_CODE
import com.ozolsugis.weatherapp.util.Constants.LOCATION_REQUEST_FASTEST_INTERVAL
import com.ozolsugis.weatherapp.util.Constants.LOCATION_REQUEST_INTERVAL
import com.ozolsugis.weatherapp.util.Constants.LOCATION_REQUEST_NUM_UPDATES
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber


@AndroidEntryPoint
class LocationService(
    private val context: Context,
    private val activity: Activity
    ) : LifecycleService(),
    EasyPermissions.PermissionCallbacks
{

    var fusedLocationProviderClient = FusedLocationProviderClient(context)

    var latitude : Double = 0.0
    var longitude : Double = 0.0


    // MissingPermission because requestLocationUpdates needs to check for permissions
    // but we already did that in  getLocationUpdate fun first if, where we initiated
    // check permissions function
    @SuppressLint("MissingPermission")
    fun getLocationUpdate(){
        val locationRequest = LocationRequest.create().apply {
            interval = LOCATION_REQUEST_INTERVAL
            fastestInterval = LOCATION_REQUEST_FASTEST_INTERVAL
            numUpdates = LOCATION_REQUEST_NUM_UPDATES
            priority = PRIORITY_HIGH_ACCURACY
        }
        if(checkPermissions()){
            Timber.d("Permission check passed")
            if (isLocationEnabled()){
                Timber.d("Is location enabled check passed")
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }else{
                Timber.d("Is location enabled check failed")
                Toast.makeText(this, "Please turn on your location provider.", Toast.LENGTH_SHORT).show()
            }
        }else{
            Timber.d("Permission check failed")
            requestPermissions()
        }
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            result.locations.let { locationResults->
                for (location in locationResults){
                    latitude = location.latitude
                    longitude = location.longitude
                    Timber.d("New Location = ${location.latitude} , ${location.longitude}")
                }
            }
        }
    }


    private fun checkPermissions() : Boolean{
        val fineLocationCheck = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val coarseLocationCheck = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val permissionGranted = PackageManager.PERMISSION_GRANTED

        return fineLocationCheck == permissionGranted || coarseLocationCheck == permissionGranted
    }

    private fun isLocationEnabled() : Boolean{
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        return isGpsProviderEnabled || isNetworkProviderEnabled
    }

    private fun requestPermissions(){
        EasyPermissions.requestPermissions(
            activity,
            "You need to accept location permission to use this app.",
            LOCATION_PERMISSION_REQUEST_CODE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>){}

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

}
package com.skott.softsquared.outsourcing_simulation.src.main.auth_location

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telephony.CarrierConfigManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.AuthMyLocationLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import java.security.Permission


class AuthLocationActivity :
    BaseActivity<AuthMyLocationLayoutBinding>(AuthMyLocationLayoutBinding::inflate),OnMapReadyCallback {
    private val context = this
    private val LOCATION_PERMISSION_REQUEST_CODE =1
    private lateinit var locationManager:LocationManager
    private lateinit var locationListener:LocationListener
    private lateinit var mapView: MapFragment
    private lateinit var currentPosition:LatLng
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener= LocationListener { location ->
            location.let{
                currentPosition = LatLng(it.latitude,it.longitude)
            }
        }

        locationSource =
            FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
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
        mapView = (context.supportFragmentManager.findFragmentById(R.id.nave_map) as MapFragment?)
            ?: MapFragment.newInstance().also {
                context.supportFragmentManager.beginTransaction().add(R.id.nave_map, it).commit()
            }
        mapView.getMapAsync(this)
    }
    override fun onMapReady(naverMap: NaverMap) {
        naverMap.mapType = NaverMap.MapType.Basic
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING,true)
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BICYCLE,false)
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_MOUNTAIN,false)
        naverMap.setContentPadding(0,55,0,300)
        naverMap.locationTrackingMode=LocationTrackingMode.None
        naverMap.locationSource=locationSource
        val cameraUpdate= CameraUpdate.scrollTo(currentPosition).animate(CameraAnimation.Fly,1000)
        naverMap.moveCamera(cameraUpdate)
        val marker = Marker()
        marker.position = currentPosition
        this.naverMap=naverMap
        marker.iconTintColor=context.getColor(R.color.carrot_market_default_color)
        marker.map = this.naverMap

    }
}
package com.example.loginfinal.DriverUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginfinal.auth.authViewModel
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState

@OptIn(MapboxExperimental::class)
@Composable
fun MapBoxScreen(modifier: Modifier=Modifier, navController: NavController, authViewModel: authViewModel) {

Column(modifier = Modifier
    .fillMaxWidth()
    .height(600.dp)) {
    MapboxMap(
        Modifier.fillMaxSize(),
        mapViewportState = MapViewportState().apply {
            setCameraOptions {
                zoom(2.0)
                center(Point.fromLngLat(-98.0, 39.5))
                pitch(0.0)
                bearing(0.0)
            }
        },
    )

}
    Button(onClick = { /*TODO*/ }, modifier.padding(start = 120.dp, top =600.dp).size(150.dp)) {
        Text(text = "Request")

    }


}

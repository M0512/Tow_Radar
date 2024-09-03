package com.example.loginfinal.DriverUi



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginfinal.R
import com.example.loginfinal.auth.authViewModel

@Composable
fun Driver(modifier: Modifier=Modifier, navController: NavController, authViewModel: authViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#eeeefb"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(245.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor("#b6c2fe")),
                    RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
                )

        ) {
            Row(
                modifier = Modifier
                    .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(start = 14.dp)
                        .weight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Hello",
                        color = Color.White,
                        fontSize = 18.sp,
                    )

                    Text(
                        text = "Driver Name",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 14.dp)
                    )
                }
                Image(painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable { })

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(500.dp)
                .height(400.dp)
                .padding(top = 20.dp, start = 24.dp, end = 24.dp)
                .shadow(3.dp, RoundedCornerShape(20.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {


        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(300.dp)
                .height(100.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#b6c2fe"))),
        ) {

        }


    }
}











package com.example.loginfinal.CustomerUi

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginfinal.R
import com.example.loginfinal.auth.AuthState
import com.example.loginfinal.auth.authViewModel

@Composable
fun Dashboard(modifier: Modifier=Modifier, navController: NavController, authViewModel: authViewModel,userEmail: String?) {
    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else-> Unit
        }
    }
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
                        text = userEmail?: "Customer Name",
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
                .width(200.dp)
                .padding(top = 20.dp, start = 24.dp, end = 24.dp)
                .shadow(3.dp, RoundedCornerShape(20.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
                    .height(100.dp)
                    .clickable { navController.navigate("mapbox") }
                    .width(100.dp)
                    .background(
                        Color(android.graphics.Color.parseColor("#b6c2fe")),
                        shape = RoundedCornerShape(30.dp),

                        ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    painter = painterResource(id = R.drawable.request),
                    contentDescription = "reqest",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 8.dp)

                )
                Text(
                    text = "REQUEST",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color(android.graphics.Color.parseColor("#5e3bee"))
                )


            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
                    .height(100.dp)
                    .width(100.dp)
                    .clickable { navController.navigate("insurence")  }
                    .background(
                        Color(android.graphics.Color.parseColor("#b6c2fe")),
                        shape = RoundedCornerShape(30.dp),

                        ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    painter = painterResource(id = R.drawable.insurence),
                    contentDescription = "Insurence",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 8.dp, bottom = 4.dp,)
                )

                Text(
                    text = "INSURENCE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color(android.graphics.Color.parseColor("#5e3bee"))
                )


            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
                    .height(100.dp)
                    .clickable { navController.navigate("service") }
                    .width(100.dp)
                    .background(
                        Color(android.graphics.Color.parseColor("#b6c2fe")),
                        shape = RoundedCornerShape(30.dp),

                        ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    painter = painterResource(id = R.drawable.service),
                    contentDescription = "Service",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 8.dp,)
                )

                Text(
                    modifier = Modifier.padding(top = 0.dp),
                    text = "SERVICE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color(android.graphics.Color.parseColor("#5e3bee"))
                )


            }

        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                authViewModel.signout()
            },
            modifier = Modifier
                .width(300.dp)
                .height(100.dp),
            colors = ButtonDefaults.buttonColors(Color(android.graphics.Color.parseColor("#b6c2fe"))),
        ) {
            Text(text = "SIGNOUT")

        }


    }


}











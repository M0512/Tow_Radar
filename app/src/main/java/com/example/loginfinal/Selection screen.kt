package com.example.loginfinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginfinal.auth.authViewModel

@Composable
fun UserType(modifier: Modifier=Modifier,navController: NavController,authViewModel: authViewModel  ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#eeeefb")))
            .clickable {



            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(60.dp))


        Image(painter = painterResource(id = R.drawable.splash),
            contentDescription ="spalsh" )
        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "WELCOME TO",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding())
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "TOW RADAR",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding())
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Please select your designation to get started.",
            fontStyle= FontStyle.Italic,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(40.dp))
Row (
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
){
    Column(
        modifier = Modifier
            .padding(start = 10.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
            .height(100.dp)
            .width(100.dp)
            .clickable { navController.navigate("loginDriver") }
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
            text = "DRIVER",
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
            .clickable { navController.navigate("login") }
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
            text = "CUSTOMER",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color(android.graphics.Color.parseColor("#5e3bee"))
        )




    }
}

}}
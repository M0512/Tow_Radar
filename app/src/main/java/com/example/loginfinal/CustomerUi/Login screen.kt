package com.example.loginfinal.CustomerUi

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginfinal.R
import com.example.loginfinal.auth.AuthState
import com.example.loginfinal.auth.authViewModel

@Composable
fun LoginScreen(modifier: Modifier=Modifier, navController: NavController, authViewModel: authViewModel){

    var password by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    val authState = authViewModel.authState.observeAsState()
    val context= LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("dash")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }
    Column(modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.support), contentDescription ="login image",
            modifier = Modifier.size(300.dp))
        Text(text = "Welcome Back", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Login to Your Account")

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = email
            , onValueChange = {email=it}, label ={
            Text(text = "Email Address")
        })
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = password, onValueChange = {
            password=it
        }, label ={
            Text(text = "Password")
        })
        Spacer(modifier.height(20.dp))

        Button(onClick = {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.login(email, password)
            } else {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        },
            enabled = authState.value!= AuthState.Loading,
            modifier = Modifier
            .height(50.dp)
            .width(100.dp)) {
            Text(text = "LOGIN", textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Forgot Password?")

        }
        Spacer(modifier = Modifier.height(4.dp))
        Row (horizontalArrangement = Arrangement.Center){
            Text(text = "Not Registered?", fontWeight = FontWeight.Bold)
            TextButton(onClick = { navController.navigate("signup")}) {
                Text(text = "Sign UP")
            }
        }



    }

}

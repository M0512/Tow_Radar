package com.example.loginfinal.CustomerUi

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginfinal.auth.AuthState
import com.example.loginfinal.auth.authViewModel


@Composable
fun Signup(modifier: Modifier=Modifier, navController: NavController, authViewModel: authViewModel) {

    var name by remember {mutableStateOf("") }
    var surname by remember {mutableStateOf("") }
    var email by remember {mutableStateOf("") }
    var password by remember {mutableStateOf("") }
    var confirmPassword by remember {mutableStateOf("") }
    var showError by remember {mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val authState = authViewModel.authState.observeAsState()
    val context= LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated-> navController.navigate("dash")
            is AuthState.Error-> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else-> Unit
        }

    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "Welcome On Board", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Enter Your Details Below")
        OutlinedTextField(value = name, onValueChange = {name=it}, label = {
            Text(text = "Name")
        })
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = surname, onValueChange = {surname=it}, label = {
            Text(text = "Surname")
        })
        Spacer(modifier = Modifier.height(4.dp))


        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = email, onValueChange = {email=it}, label = {
            Text(text = "Email Address")
        }, )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = password, onValueChange = {password=it}, label = {
            Text(text = "Password")
        })
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(value = confirmPassword, onValueChange = {confirmPassword=it}, label = {
            Text(text = "Confirm Password")
        })
        Spacer(modifier = Modifier.height(14.dp))
        Button(

            onClick = {
                authViewModel.signin(email,password,name,surname,confirmPassword)
                      }, enabled = authState.value!= AuthState.Loading,
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
        ) {
            Text(
                text = "REGISTER", textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }
    }
}
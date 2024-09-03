package com.example.loginfinal.auth

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginfinal.CustomerUi.Dashboard
import com.example.loginfinal.CustomerUi.LoginScreen
import com.example.loginfinal.CustomerUi.Signup
import com.example.loginfinal.CustomerUi.insurence
import com.example.loginfinal.CustomerUi.service
import com.example.loginfinal.DriverUi.Driver
import com.example.loginfinal.DriverUi.LoginDriver
import com.example.loginfinal.DriverUi.MapBoxScreen
import com.example.loginfinal.DriverUi.SignupDriver
import com.example.loginfinal.UserType


@Composable
    fun MyNavigation(modifier: Modifier= Modifier,authViewModel: authViewModel){
        val navController= rememberNavController()
    NavHost(navController = navController, startDestination ="selection", builder = {
        composable("selection"){
            UserType(modifier,navController,authViewModel)
        }
        composable("login"){
            LoginScreen(modifier,navController,authViewModel)
        }
        composable("signup"){
            Signup(modifier,navController,authViewModel)
        }
        composable("dash"){
            val userEmail = authViewModel.getCurrentUserEmail()
            Dashboard(modifier,navController,authViewModel,userEmail)
        }
        composable("dashDriver"){
            Driver(modifier,navController,authViewModel)
        }
        composable("signUpDriver"){
            SignupDriver(modifier,navController,authViewModel)

        }
        composable("loginDriver"){
            LoginDriver(modifier,navController,authViewModel)

        }
        composable("mapbox"){
            MapBoxScreen(modifier,navController,authViewModel)

        }
        composable("insurence") {
            insurence(modifier, navController, authViewModel) { insuredDate, dueDate ->
                Log.d("InsuranceTest", "Insured Date: $insuredDate, Due Date: $dueDate")
            }

        }
        composable("service") {
            service(modifier, navController, authViewModel) { insuredDate, dueDate ->
                Log.d("InsuranceTest", "Insured Date: $insuredDate, Due Date: $dueDate")
            }

        }

    } )
        }


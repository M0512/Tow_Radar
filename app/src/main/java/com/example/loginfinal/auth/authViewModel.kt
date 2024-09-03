package com.example.loginfinal.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class authViewModel: ViewModel() {
    private var firebaseAuth: FirebaseAuth= FirebaseAuth.getInstance()
    val auth: FirebaseAuth= FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = null
    private val _authState = MutableLiveData<AuthState>()
    val authState:LiveData<AuthState> = _authState

    fun getCurrentUserEmail(): String?{
        return firebaseAuth.currentUser?.email

    }

    fun checkStatus(){
        if(auth.currentUser==null){
            _authState.value=AuthState.Unauthenticated
        }else{
            _authState.value=AuthState.Authenticated
        }
    }
    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } /*else {
                    val exception = task.exception
                    if (exception is FirebaseAuthInvalidUserException) {
                        _authState.value = AuthState.Error("User not found")
                    } else if (exception is FirebaseAuthInvalidCredentialsException) {
                        _authState.value = AuthState.Error("Invalid password")
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                    }
                }*/
            }
    }
    fun updateUserProfile(name: String, surname: String) {
        currentUser?.updateProfile(
            UserProfileChangeRequest.Builder()
            .setDisplayName("$name $surname")
            .build())
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated

                } else {
                    _authState.value= AuthState.Error(task.exception?.message?:"Something went wrong")

                }
            }
    }
    fun signin(email: String,password: String,confirmPassword: String,name: String, surname: String,){
        if (email.isEmpty()|| password.isEmpty() || name.isEmpty()||surname.isEmpty()){
            _authState.value=AuthState.Error("All fields are Required")
            return
        }
        if (password != confirmPassword) {
            _authState.value = AuthState.Error("Passwords do not match")
            return
        }
        _authState.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    currentUser = firebaseAuth.currentUser
                    updateUserProfile(name, surname)
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value= AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }
    fun signout(){
        auth.signOut()
        _authState.value=AuthState.Unauthenticated
    }
    fun getUserEmail(): String? {
        return firebaseAuth.currentUser?.email
    }

}

sealed class AuthState{
    object Authenticated: AuthState()
    object Unauthenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String) : AuthState()
}
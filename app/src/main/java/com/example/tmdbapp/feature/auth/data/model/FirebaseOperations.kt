package com.example.tmdbapp.feature.auth.data.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume


class FirebaseOperations @Inject constructor() {


    private val auth = Firebase.auth

    suspend fun registerUser(userName: String, mail: String, password: String): Result<Boolean> {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        continuation.resume(Result.success(true))


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        continuation.resume(Result.success(false))

                    }
                }
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun loginUser(mail: String, password: String): Result<Boolean> {
        return suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "singInUserWithEmail:success")
                        continuation.resume(Result.success(true))

                    } else {
                        Log.w(TAG, "singInUserWithEmail:failure", task.exception)
                        continuation.resume(Result.success(false))

                    }

                }
        }


    }
}
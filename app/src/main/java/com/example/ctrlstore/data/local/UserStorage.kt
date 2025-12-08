package com.example.ctrlstore.data.local

import android.content.Context
import android.util.Log
import com.google.gson.Gson

data class StoredUser(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val token: String? = null
)

object UserStorage {
    private const val TAG = "UserStorage"
    private const val PREFS_NAME = "ctrlstore_prefs"
    private const val KEY_USER = "user"
    private const val KEY_LOGGED_OUT = "logged_out" // nueva flag
    private val gson = Gson()

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, user: StoredUser) {
        try {
            val json = gson.toJson(user)
            Log.d(TAG, "saveUser() -> JSON: $json")
            prefs(context).edit().putString(KEY_USER, json).apply()
            prefs(context).edit().putBoolean(KEY_LOGGED_OUT, false).apply()
        } catch (e: Exception) {
            Log.e(TAG, "saveUser() -> error: ${e.message}", e)
        }
    }

    fun getUser(context: Context): StoredUser? {
        val json = prefs(context).getString(KEY_USER, null) ?: return null
        return try {
            gson.fromJson(json, StoredUser::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "getUser() -> parse error: ${e.message}", e)
            null
        }
    }

    fun clearUser(context: Context) {
        prefs(context).edit().remove(KEY_USER).apply()
        prefs(context).edit().putBoolean(KEY_LOGGED_OUT, false).apply()
        Log.d(TAG, "clearUser() -> user removed and logged_out set to false")
    }

    fun setLoggedOut(context: Context, value: Boolean) {
        prefs(context).edit().putBoolean(KEY_LOGGED_OUT, value).apply()
        Log.d(TAG, "setLoggedOut($value)")
    }

    fun isLoggedOut(context: Context): Boolean {
        return prefs(context).getBoolean(KEY_LOGGED_OUT, false)
    }

    fun isUserLoggedIn(context: Context): Boolean {
        // true only if user exists and logged_out flag is false
        val user = getUser(context)
        val loggedOut = isLoggedOut(context)
        val result = user != null && !loggedOut
        Log.d(TAG, "isUserLoggedIn() -> userExists=${user != null}, loggedOut=$loggedOut => $result")
        return result
    }
}
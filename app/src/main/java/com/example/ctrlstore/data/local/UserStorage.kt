package com.example.ctrlstore.data.local

import android.content.Context
import com.google.gson.Gson

data class StoredUser(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val token: String? = null
)

object UserStorage {
    private const val PREFS_NAME = "ctrlstore_prefs"
    private const val KEY_USER = "user"
    private val gson = Gson()

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, user: StoredUser) {
        val json = gson.toJson(user)
        prefs(context).edit().putString(KEY_USER, json).apply()
    }

    fun getUser(context: Context): StoredUser? {
        val json = prefs(context).getString(KEY_USER, null) ?: return null
        return try {
            gson.fromJson(json, StoredUser::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun clearUser(context: Context) {
        prefs(context).edit().remove(KEY_USER).apply()
    }

    fun isUserLoggedIn(context: Context): Boolean {
        return getUser(context) != null
    }
}
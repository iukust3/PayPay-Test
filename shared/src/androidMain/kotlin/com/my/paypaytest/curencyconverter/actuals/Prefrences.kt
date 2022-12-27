package com.my.paypaytest.curencyconverter.actuals

import android.util.Log
import com.my.paypaytest.curencyconverter.KMMContext

actual fun KMMContext.putInt(
    key: String,
    value: Int
) {
    getSpEditor().putInt(key, value).apply();
}

actual fun KMMContext.getInt(
    key: String,
    default: Int
): Int {
    return getSp().getInt(key, default);
}

actual fun KMMContext.putString(
    key: String,
    value: String
) {
    Log.e("TAg","Put String $key  value $value");
    getSpEditor().putString(key, value).apply();
}

actual fun KMMContext.getString(key: String): String? {
    return getSp().getString(key, "");
}

actual fun KMMContext.putBool(
    key: String,
    value: Boolean
) {
    getSpEditor().putBoolean(key, value).apply();
}

actual fun KMMContext.getBool(
    key: String,
    default: Boolean
): Boolean {
    return getSp().getBoolean(key, default);
}

private fun KMMContext.getSp() = getSharedPreferences("CurrencyApp", 0)

private fun KMMContext.getSpEditor() = getSp().edit()
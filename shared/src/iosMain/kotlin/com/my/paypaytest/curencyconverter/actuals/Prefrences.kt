package com.my.paypaytest.curencyconverter.actuals

import com.my.paypaytest.curencyconverter.KMMContext
import platform.Foundation.NSUserDefaults

actual fun KMMContext.putInt(
    key: String,
    value: Int
) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(),key);
}

actual fun KMMContext.getInt(
    key: String,
    default: Int
): Int {
   return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt();

}

actual fun KMMContext.getString(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key);

}

actual fun KMMContext.putBool(
    key: String,
    value: Boolean
) {
     NSUserDefaults.standardUserDefaults.setBool(value,key);

}

actual fun KMMContext.getBool(
    key: String,
    default: Boolean
): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(key);

}

actual fun KMMContext.putString(
    key: String,
    value: String
) {
    return NSUserDefaults.standardUserDefaults.setObject(value,key);

}
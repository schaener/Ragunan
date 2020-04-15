package ragunan.javafirst.Utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.NumberFormat;
import java.util.Locale;

public class ScriptHelper {
    Locale localeID = new Locale("in", "ID");
    public NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    public boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

package ragunan.javafirst.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class ScriptHelper {
    Locale localeID = new Locale("in", "ID");
    public NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

}

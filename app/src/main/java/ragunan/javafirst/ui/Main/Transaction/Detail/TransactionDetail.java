package ragunan.javafirst.ui.Main.Transaction.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.ScriptHelper;
import ragunan.javafirst.Utils.SharedPrefManager;
import ragunan.javafirst.ui.Main.Home.Home;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static ragunan.javafirst.Utils.SharedPrefManager.SP_MONEY;

public class TransactionDetail extends AppCompatActivity {
RelativeLayout payNow;
SharedPrefManager sharedPrefManager;
TextView money;
    SharedPreferences shared;
    ScriptHelper helper = new ScriptHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        sharedPrefManager = new SharedPrefManager(TransactionDetail.this);
        shared = getSharedPreferences("spTMII", MODE_PRIVATE);
money = findViewById(R.id.TVmoney);
money.setText(helper.formatRupiah.format(Integer.valueOf(shared.getString( SP_MONEY , ""))));
        payNow = findViewById(R.id.payNow);
         payNow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if ((Integer.valueOf(shared.getString( SP_MONEY , ""))<120000)){
                     Toast.makeText(getApplicationContext(),"Anda Tidak Memiliki Cukup Saldo," +
                                     " Silakan Untuk Top Up Terlebih Dahulu",
                             Toast.LENGTH_SHORT).show();
                 }
                 else {
                     int result = (Integer.valueOf(shared.getString( SP_MONEY , "")))-120000;
                     sharedPrefManager.saveSPString(SharedPrefManager.SP_MONEY , String.valueOf(result));
                     Intent i = new Intent(getApplicationContext(), Home.class);
                     i.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(i);
                 }

             }
         });
    }
}

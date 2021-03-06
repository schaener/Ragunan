package ragunan.javafirst.ui.Main.Topup.Intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.GlobalVariabels;
import ragunan.javafirst.Utils.ScriptHelper;
import ragunan.javafirst.Utils.SharedPrefManager;
import ragunan.javafirst.ui.Main.Home.Home;
import ragunan.javafirst.ui.Main.Topup.Detail.DetailTopup;
import ragunan.javafirst.ui.Main.Topup.ListBank.Intro.IntroBankList;

import static ragunan.javafirst.Utils.SharedPrefManager.SP_MONEY;

public class IntroTopup extends AppCompatActivity {
CardView chooseMethod;
TextView bankName,money;
EditText nominalTopup;
ImageView topup;
    SharedPrefManager sharedPrefManager;
    SharedPreferences shared;
    ScriptHelper helper = new ScriptHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_topup);
        shared = getSharedPreferences("spTMII", MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(this);

        chooseMethod = findViewById(R.id.chooseMethod);
        bankName = findViewById(R.id.bankName);
        nominalTopup = findViewById(R.id.nominalTopup);
        topup = findViewById(R.id.bTopupRL);
        money = findViewById(R.id.TVmoney);
        money.setText(helper.formatRupiah.format(Integer.valueOf(shared.getString( SP_MONEY , ""))));
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GlobalVariabels.nominalTopupstr.isEmpty()){
                    nominalTopup.setError("Anda Belum Mengisi Jumlah Top Up !");
                }
                else if (GlobalVariabels.bankNamestr.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Anda Belum Memilih Metode Pembayaran !"
                            ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), DetailTopup.class);
                    startActivity(i);

                }
               }
        });
        getNominalTopUp(nominalTopup);
        getBankName(bankName);
        chooseMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), IntroBankList.class);
                startActivity(i);
            }
        });

    }
    public void getBankName(TextView bankName){
        if (!GlobalVariabels.bankNamestr.isEmpty())
        {
            bankName.setText(GlobalVariabels.bankNamestr);

        }
        }

public void getNominalTopUp(EditText nominalTopup){
        if (!GlobalVariabels.nominalTopupstr.isEmpty()){
            nominalTopup.setText(GlobalVariabels.nominalTopupstr);
        }
        else {
            nominalTopup.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    GlobalVariabels.nominalTopupstr = String.valueOf(charSequence);
                   }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }
   }
}

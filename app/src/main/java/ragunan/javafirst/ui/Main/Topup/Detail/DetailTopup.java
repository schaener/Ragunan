package ragunan.javafirst.ui.Main.Topup.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.GlobalVariabels;
import ragunan.javafirst.Utils.ScriptHelper;
import ragunan.javafirst.Utils.SharedPrefManager;
import ragunan.javafirst.ui.Main.Home.Home;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static ragunan.javafirst.Utils.SharedPrefManager.SP_MONEY;

public class DetailTopup extends AppCompatActivity {
TextView nominalTopup,bankName,bankAccount,money;
CardView alreadyTF;
ScriptHelper helper = new ScriptHelper();
    SharedPrefManager sharedPrefManager;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topup);
        sharedPrefManager = new SharedPrefManager(this);
        shared = getSharedPreferences("spTMII", MODE_PRIVATE);

        nominalTopup = findViewById(R.id.nominalTopup);
        bankName = findViewById(R.id.bankName);
        bankAccount = findViewById(R.id.bankAccount);
        money = findViewById(R.id.TVmoney);
        alreadyTF = findViewById(R.id.bAlreadyTf);
        nominalTopup.setText(helper.formatRupiah.format(Integer.valueOf(GlobalVariabels.nominalTopupstr)));
        bankAccount.setText(GlobalVariabels.bankAccountstr);
        bankName.setText("Bank "+GlobalVariabels.bankNamestr+" ("+GlobalVariabels.bankCodestr+")");
        money.setText(helper.formatRupiah.format(Integer.valueOf(shared.getString( SP_MONEY , ""))));
        alreadyTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = (Integer.valueOf(shared.getString( SP_MONEY , ""))
                        +(Integer.valueOf(GlobalVariabels.nominalTopupstr)));
                sharedPrefManager.saveSPString(SharedPrefManager.SP_MONEY , String.valueOf(result));

                Intent i = new Intent(getApplicationContext(), Home.class);
                i.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(i);
            }
        });
    }
}

package ragunan.javafirst.ui.Main.Topup.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.GlobalVariabels;
import ragunan.javafirst.Utils.ScriptHelper;
import ragunan.javafirst.ui.Main.Home.Home;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DetailTopup extends AppCompatActivity {
TextView nominalTopup,bankName,bankAccount;
RelativeLayout alreadyTF;
ScriptHelper helper = new ScriptHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topup);
        nominalTopup = findViewById(R.id.nominalTopup);
        bankName = findViewById(R.id.bankName);
        bankAccount = findViewById(R.id.bankAccount);
        alreadyTF = findViewById(R.id.bAlreadyTf);
        nominalTopup.setText(helper.formatRupiah.format(Integer.valueOf(GlobalVariabels.nominalTopupstr)));
        bankAccount.setText(GlobalVariabels.bankAccountstr);
        bankName.setText("Bank "+GlobalVariabels.bankNamestr+" ("+GlobalVariabels.bankCodestr+")");
        alreadyTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                i.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(i);
            }
        });
    }
}

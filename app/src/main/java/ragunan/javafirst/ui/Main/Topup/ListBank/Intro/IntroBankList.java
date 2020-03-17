package ragunan.javafirst.ui.Main.Topup.ListBank.Intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ragunan.javafirst.R;
import ragunan.javafirst.ui.Main.Topup.ListBank.Adapter.ListBankAdapter;
import ragunan.javafirst.ui.Main.Topup.ListBank.Model.ListBankModel;

public class IntroBankList extends AppCompatActivity {
    RecyclerView rvBank;
   ListBankAdapter madapter;
    List<ListBankModel> listBankModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_bank_list);
       rvBank= findViewById(R.id.rvBank);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvBank.setLayoutManager(llm);
        madapter = new ListBankAdapter(listBankModels,IntroBankList.this);
      rvBank.setItemAnimator(new DefaultItemAnimator());
       rvBank.setAdapter(madapter);
       BankData();
    }
    public void BankData(){
        ListBankModel  listBankModel = new ListBankModel("0065","BNI","070-00-0185454-3"
        );
        listBankModels.add(listBankModel);
    listBankModel = new ListBankModel("0008","MANDIRI","070-00-0185454-5"
                );
       listBankModels.add(listBankModel);
        listBankModel = new ListBankModel("0054","BCA","070-00-0185454-3"
        );
        listBankModels.add(listBankModel);
        listBankModel = new ListBankModel("0029","BTPN","070-00-0185454-2"
        );
        listBankModels.add(listBankModel);
        listBankModel = new ListBankModel("0012","BRI","070-00-0185454-1"
        );
        listBankModels.add(listBankModel);
        listBankModel = new ListBankModel("0027","BTN","070-00-0185454-9"
        );
        listBankModels.add(listBankModel);

        madapter.notifyDataSetChanged();

    }
}

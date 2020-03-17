package ragunan.javafirst.ui.Main.Topup.ListBank.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ragunan.javafirst.R;
import ragunan.javafirst.Utils.GlobalVariabels;
import ragunan.javafirst.ui.Main.Topup.Intro.IntroTopup;
import ragunan.javafirst.ui.Main.Topup.ListBank.Model.ListBankModel;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class ListBankAdapter extends RecyclerView.Adapter<ListBankAdapter.ViewHolder> {
    private List<ListBankModel> listBankModels;
    private Context context;

    public ListBankAdapter(List<ListBankModel> listBankModels, Context context) {
        this.listBankModels = listBankModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ListBankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ListBankAdapter.ViewHolder holder, int position) {
        final ListBankModel model = listBankModels.get(position);
        holder.bankName.setText(model.getBankName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariabels.bankNamestr = holder.bankName.getText().toString();
                GlobalVariabels.bankAccountstr = model.getBankAccount();
                GlobalVariabels.bankCodestr = model.getBankCode();
                Intent i = new Intent(context, IntroTopup.class);
                i.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBankModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bankName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           bankName = itemView.findViewById(R.id.bankName);
        }
    }
}

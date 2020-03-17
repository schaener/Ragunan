package ragunan.javafirst.ui.Main.Topup.ListBank.Model;

public class ListBankModel {
   String bankCode,bankName,bankAccount;

    public ListBankModel(String bankCode, String bankName, String bankAccount) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}

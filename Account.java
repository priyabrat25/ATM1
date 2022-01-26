package com.atm_system;

        import java.util.ArrayList;

public class Account {

    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank theBank){

        this.name=name;
        this.holder=holder;

        this.uuid=theBank.getNewAccountUUID();
        this.transactions= new ArrayList<Transaction>();


    }

    public String getUUID(){
        return this.uuid;
    }

    public String getSummaryLine(){

        double balance = this.getBalance();

        if (balance >= 0){
            return String.format("%s : ₹%.02f : %s", this.uuid, balance, this.name);
        }else {
            return String.format("%s : (₹%.02f) : %s", this.uuid, balance, this.name);

        }
    }
    public double getBalance(){
        double balance =0;
        for (Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory(){

        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t=this.transactions.size()-1; t >=0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo){

        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
Transaction class
package com.atm_system;

        import java.util.Date;

public class Transaction {

    private double amount;
    private Date timestamp;
    private String memo;
    private Account inAccount;

    public Transaction(double amount, Account inAccount){

        this.amount= amount;
        this.inAccount= inAccount;
        this.timestamp= new Date();
        this.memo="";


    }

    public Transaction(double amount, String memo,Account inAccount){

        this(amount,inAccount);
        this.memo=memo;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getSummaryLine(){

        if (this.amount >= 0){
            return String.format("%s : ₹%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        }else {
            return String.format("%s : (₹%.02f) : %s", this.timestamp.toString(), -this.amount, this.memo);
        }
    }

}


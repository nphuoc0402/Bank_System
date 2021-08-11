package Model;

public class Transfer {
    private int id;
    private int idSender;
    private int idReceiver;
    private int amount;
    private int transaction_fee = 5;
    private int total_amount;

    public Transfer() {
    }

    public Transfer(int id, int idSender, int idReceiver, int amount, int transaction_fee, int total_amount) {
        this.id = id;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.amount = amount;
        this.transaction_fee = transaction_fee;
        this.total_amount = total_amount;
    }

    public Transfer(int idSender, int idReceiver, int amount, int transaction_fee, int total_amount) {
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.amount = amount;
        this.transaction_fee = transaction_fee;
        this.total_amount = total_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTransaction_fee() {
        return transaction_fee;
    }

    public void setTransaction_fee(int transaction_fee) {
        this.transaction_fee = transaction_fee;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }
}

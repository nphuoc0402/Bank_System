package Model;

public class ListRevenue {
    private int id;
    private int idSender;
    private String name_Sender;
    private int idReceiver;
    private String name_Receiver;
    private int amount;
    private int transaction_fee = 5;
    private int total_amount;

    public ListRevenue() {
    }

    public ListRevenue(int id, int idSender, String name_Sender, int idReceiver, String name_Receiver, int amount, int transaction_fee, int total_amount) {
        this.id = id;
        this.idSender = idSender;
        this.name_Sender = name_Sender;
        this.idReceiver = idReceiver;
        this.name_Receiver = name_Receiver;
        this.amount = amount;
        this.transaction_fee = transaction_fee;
        this.total_amount = total_amount;
    }

    public ListRevenue(int idSender, String name_Sender, int idReceiver, String name_Receiver, int amount, int transaction_fee, int total_amount) {
        this.idSender = idSender;
        this.name_Sender = name_Sender;
        this.idReceiver = idReceiver;
        this.name_Receiver = name_Receiver;
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

    public String getName_Sender() {
        return name_Sender;
    }

    public void setName_Sender(String name_Sender) {
        this.name_Sender = name_Sender;
    }

    public int getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(int idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getName_Receiver() {
        return name_Receiver;
    }

    public void setName_Receiver(String name_Receiver) {
        this.name_Receiver = name_Receiver;
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

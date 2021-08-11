package Service;

import Model.Customer;
import Model.Transfer;
import Utils.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO implements ITransferDAO {
    public TransferDAO(){
    }

    Connection connection = MySQLUtils.getConnection();

    private final String UPDATE_CUSTOMER_SALARY_RECEIVER = "UPDATE customer SET salary = salary + ? WHERE id = ?;";
    private final String UPDATE_CUSTOMER_SALARY_SENDER = "UPDATE customer SET salary = salary - ? WHERE id = ?;";


    @Override
    public void insertTransfer(Transfer transfer) throws SQLException {
        String Insert_Transfer_SQL = "INSERT INTO transfers (idSender,idReceiver,amount,fee_percent,total_amount) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Transfer_SQL);
            preparedStatement.setInt(1,transfer.getIdSender());
            preparedStatement.setInt(2,transfer.getIdReceiver());
            preparedStatement.setInt(3,transfer.getAmount());
            preparedStatement.setInt(4,transfer.getTransaction_fee());
            preparedStatement.setInt(5,transfer.getTotal_amount());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public List<Transfer> selectAllTransfer() {
        String Select_All_SQL = "SELECT id,idSender,idReceiver,amount,fee_percent,total_amount FROM transfers;";
        List<Transfer> transferList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int idSender = rs.getInt("idSender");
                int idReceiver = rs.getInt("idReceiver");
                int amount = rs.getInt("amount");
                int fee_percent = rs.getInt("fee_percent");
                int fee_amount = rs.getInt("total_amount");
                Transfer transfer = new Transfer(id,idSender,idReceiver,amount,fee_percent,fee_amount);
                transferList.add(transfer) ;
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return transferList;
    }

    @Override
    public boolean update(Transfer transfer) throws SQLException {
        boolean update = false;
        String Update_Transfer_SQL = "UPDATE transfers SET idSender = ?, idReceiver = ?, amount = ?, fee_percent = ? WHERE id = ?;";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Transfer_SQL);
            preparedStatement.setInt(1,transfer.getIdSender());
            preparedStatement.setInt(2,transfer.getIdReceiver());
            preparedStatement.setInt(3,transfer.getAmount());
            preparedStatement.setInt(4,transfer.getTransaction_fee());
            preparedStatement.setInt(5,transfer.getId());
            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            update = true;
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return  update;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

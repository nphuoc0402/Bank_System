package Service;

import Model.Customer;
import Model.Transfer;

import java.sql.SQLException;
import java.util.List;

public interface ITransferDAO {
    public void insertTransfer(Transfer transfer) throws SQLException;
    public List<Transfer> selectAllTransfer() throws SQLException;
    public boolean update(Transfer transfer) throws SQLException;
}

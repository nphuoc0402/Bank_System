package Service;

import Model.Customer;
import com.mysql.cj.result.SqlDateValueFactory;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;
    public Customer selectCustomerById(int id) throws SQLException;
    public List<Customer> selectAllCustomer();
    public List<Customer> selectSearchCustomer(String name);
    public boolean isUpdateCustomer(Customer customer) throws SQLException;
    public boolean isUpdateTransfer(Customer customer, Customer customer2, int balance, int feeTransaction) throws SQLException;
    public boolean isDeleteCustomer(int id) throws  SQLException;
    public boolean WithdrawBalance(Customer customer) throws SQLException;
    public boolean DepositBalance(Customer customer) throws SQLException;

}

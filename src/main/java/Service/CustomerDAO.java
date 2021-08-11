package Service;

import Model.Customer;
import Utils.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    public CustomerDAO() {

    }

    Connection connection = MySQLUtils.getConnection();

    private final String UPDATE_TRANSFER_SALARY_SENDER = "UPDATE customers SET salary = salary - (? + ?) WHERE id = ?;";
    private final String UPDATE_TRANSFER_SALARY_RECEIVER = "UPDATE customers SET salary = salary + ? WHERE id = ?;";
    private final String UPDATE_CUSTOMER_SALARY_RECEIVER = "UPDATE customer SET salary = salary + ? WHERE id = ?;";

    @Override
    public void insertCustomer(Customer customer) {
        String SQL_INSERT_CUSTOMER = "INSERT INTO customers (name,phone,email) VALUES (?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Customer selectCustomerById(int id) {
        Customer customer = null;
        String SQL_SELECT_CUSTOMER_BY_ID = "SELECT name,phone,email,salary FROM customers WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int salary = rs.getInt("salary");
                customer = new Customer(id,name, phone, email, salary);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        String SQL_SELECTALL_CUSTOMER = "SELECT id,name,phone,email,salary FROM customers;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECTALL_CUSTOMER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int salary = rs.getInt("salary");
                customerList.add(new Customer(id, name, phone, email, salary));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customerList;
    }

    @Override
    public List<Customer> selectSearchCustomer(String nameSearch) {
        String SELECT_SEARCH_CUSTOMER  = "SELECT id,name,phone,email,salary FROM customers  WHERE name LIKE ?";

        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCH_CUSTOMER);){
            preparedStatement.setString(1, "%" +  nameSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt ("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int salary = rs.getInt("salary");
                customers.add(new Customer(id,name,phone,email,salary));
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return customers;
    }

    @Override
    public boolean isUpdateCustomer(Customer customer) throws SQLException {
        boolean update = false;
        String UPDATE_CUSTOMER_SQL = "UPDATE customers SET name = ?,phone = ?, email = ?, salary = ? WHERE id = ?;";
        try  {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setInt(4, customer.getSalary());
            preparedStatement.setInt(5, customer.getId());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            update = preparedStatement.executeUpdate() > 0;

        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return update;
    }
    @Override
    public boolean isUpdateTransfer(Customer customer, Customer customer2, int balance, int feeTransaction) throws SQLException {

        boolean rowUpdate = false;
        try  {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRANSFER_SALARY_SENDER);
            PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_TRANSFER_SALARY_RECEIVER);

            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2,feeTransaction);
            preparedStatement.setInt(3, customer.getId());

            preparedStatement.executeUpdate();

            preparedStatement2.setInt(1, balance);
            preparedStatement2.setInt(2, customer2.getId());

            preparedStatement2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
//            rowUpdate = preparedStatement.executeUpdate() > 0;
            rowUpdate = true;
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return  rowUpdate;
    }

    @Override
    public boolean WithdrawBalance(Customer customer) throws SQLException {
        boolean Update;
        String UPDATE_CUSTOMER_SALARY_WITHDRAW = "UPDATE customers SET salary = salary - ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SALARY_WITHDRAW);) {
            preparedStatement.setInt(1, customer.getSalary());
            preparedStatement.setInt(2, customer.getId());
            Update = preparedStatement.executeUpdate() > 0;
        }
        return  Update;
    }

    @Override
    public boolean DepositBalance(Customer customer) throws SQLException {
        boolean Update;
        String UPDATE_CUSTOMER_SALARY_DEPOSIT = "UPDATE customers SET salary = salary + ? WHERE id = ?;";
        try(
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SALARY_DEPOSIT);) {
            preparedStatement.setInt(1, customer.getSalary());
            preparedStatement.setInt(2, customer.getId());
            Update = preparedStatement.executeUpdate() > 0;
        }
        return  Update;
    }

    @Override
    public boolean isDeleteCustomer(int id) throws SQLException {
        boolean delete = false;
        String SQL_DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?;";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CUSTOMER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            ;
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
        return delete;
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

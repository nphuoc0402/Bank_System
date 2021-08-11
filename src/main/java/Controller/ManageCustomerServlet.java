package Controller;

import Model.Customer;
import Model.Transfer;
import Service.CustomerDAO;
import Service.TransferDAO;
import Utils.CheckTools;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ManageCustomerServlet", urlPatterns = "/ManagerCustomer")
public class ManageCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private TransferDAO transferDAO;

    public void init() {
        customerDAO = new CustomerDAO();
        transferDAO = new TransferDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "deposit":
                    deposit(request,response);
                    break;
                case "withdraw":
                    withdraw(request,response);
                    break;
                default:
                    showCustomer(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "withdraw":
                    showWithdrawForm(request,response);
                    break;
                case "deposit":
                    showDepositForm(request,response);
                    break;
                case "revenue":
                    listRevenue(request,response);
                    break;
                default:
                    showCustomer(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void listRevenue(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Transfer> transferList = transferDAO.selectAllTransfer();
        List<Customer> customers = customerDAO.selectAllCustomer();
        int total = transferDAO.selectTotal();
        request.setAttribute("total",total);
        request.setAttribute("transfers",transferList);
        request.setAttribute("customers",customers);
        RequestDispatcher dis = request.getRequestDispatcher("manager/revenue.jsp");
        dis.forward(request,response);
    }

    public void showCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Customer> customers = customerDAO.selectAllCustomer();
        request.setAttribute("customers", customers);
        RequestDispatcher dis = request.getRequestDispatcher("manager/manage.jsp");
        dis.forward(request, response);
    }

    public void showDepositForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis = request.getRequestDispatcher("manager/deposit.jsp");
        request.setAttribute("customer", existingCustomer);
        dis.forward(request, response);
    }

    public void showWithdrawForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis = request.getRequestDispatcher("manager/withdraw.jsp");
        request.setAttribute("customer", existingCustomer);
        dis.forward(request, response);
    }

    public void deposit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String balance = request.getParameter("balance");
        Customer editCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if (balance == "") {
            request.setAttribute("success", null);
            request.setAttribute("error", "Balance is required");
            showDepositForm(request, response);
        } else {
            if (!CheckTools.isNumeric(balance)) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Invalid Value");
                showDepositForm(request, response);

            } else {
                if (editCustomer == null) {
                    dis = request.getRequestDispatcher("Customer/error-404.jsp");
                } else {
                    int salary = Integer.parseInt(balance);
                    if(salary <=0 || salary >99999){
                        request.setAttribute("success", null);
                        request.setAttribute("error", "Salary require greater than 0 and less than 100000");
                        showDepositForm(request, response);

                    }else {
                        editCustomer.setId(id);
                        editCustomer.setSalary(salary);
                        customerDAO.DepositBalance(editCustomer);
                        request.setAttribute("success", "Customer was update salary success");
                        request.setAttribute("error", null);
                        showDepositForm(request, response);
                    }
                }

            }
        }
    }

    public void withdraw(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String balance = request.getParameter("balance");
        Customer editCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if (balance == "") {
            request.setAttribute("success", null);
            request.setAttribute("error", "Balance is required");
            showWithdrawForm(request, response);
        } else {
            if (!CheckTools.isNumeric(balance)) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Invalid Value");
                showWithdrawForm(request, response);

            } else {
                if (editCustomer == null) {
                    dis = request.getRequestDispatcher("Customer/error-404.jsp");
                } else {
                    int salary = Integer.parseInt(balance);
                    if(salary <=0 || salary >99999 || salary < editCustomer.getSalary()){
                        request.setAttribute("success", null);
                        request.setAttribute("error", "Salary require greater than 0 and less than 100000 or Salary isn't enough to withdraw");
                        showWithdrawForm(request, response);
                    }else {
                        editCustomer.setId(id);
                        editCustomer.setSalary(salary);
                        customerDAO.WithdrawBalance(editCustomer);
                        request.setAttribute("success", "Customer was update salary success");
                        request.setAttribute("error", null);
                        showWithdrawForm(request, response);
                    }
                }

            }
        }
    }


}

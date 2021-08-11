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

@WebServlet(name = "CustomerServlet", urlPatterns = "/banking_system")
public class CustomerServlet extends HttpServlet {
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
                case "edit":
                    updateCustomer(request, response);
                    break;
                case "delete":
                    break;
                case "transfer":
                    transferE(request,response);
                    break;
                case "search":
                    listSearchCustomer(request, response);
                    break;
                default:
                    insertCustomer(request, response);
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
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
//                    showDeleteForm(request, response);
                    deleteCustomer(request,response);
                    break;
                case "transfer":
                    showTransfer(request, response);
                    break;
                case "search":
                    listSearchCustomer(request, response);
                    break;
                default:
                    showListCustomer(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void showListCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Customer> customers = customerDAO.selectAllCustomer();
        request.setAttribute("customers", customers);
        RequestDispatcher dis = request.getRequestDispatcher("Customer/list.jsp");
        dis.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis = request.getRequestDispatcher("Customer/edit.jsp");
        request.setAttribute("customer", existingCustomer);
        dis.forward(request, response);
    }

    public void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis = request.getRequestDispatcher("Customer/delete.jsp");
        request.setAttribute("customer", existingCustomer);
        dis.forward(request, response);
    }

    public void showTransfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        Transfer transfer = new Transfer();
        request.setAttribute("customer", existingCustomer);
        request.setAttribute("feePercent", transfer.getTransaction_fee());
        RequestDispatcher dis = request.getRequestDispatcher("Transfer/transferE.jsp");
        dis.forward(request, response);
    }

    public void listSearchCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String search = request.getParameter("search");
        List<Customer> customers = customerDAO.selectSearchCustomer(search);
        request.setAttribute("customers", customers);
        RequestDispatcher dis = request.getRequestDispatcher("Customer/list.jsp");
        dis.forward(request, response);
//        response.sendRedirect("/customersManage");
    }



    public void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        if (name == "" || email == "" || phone == "") {
            request.setAttribute("success", null);
            request.setAttribute("error", "All field is required");
            showListCustomer(request, response);
        } else {
            if (!CheckTools.isEmail(email) || CheckTools.isNumeric(name) || !CheckTools.isPhoneNumber(phone) ) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Invalid Value");
                showListCustomer(request, response);
            } else {
                Customer newCustomer = new Customer(name, phone, email);
                customerDAO.insertCustomer(newCustomer);
                request.setAttribute("success", "Customer information was created");
                request.setAttribute("error", null);
                showListCustomer(request, response);
            }

        }
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Customer editCustomer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if (name == "" || email == "" || phone == "") {
            request.setAttribute("success", null);
            request.setAttribute("error", "All field is required");
            showEditForm(request, response);
        } else {
            if (!CheckTools.isEmail(email) || CheckTools.isNumeric(name) || !CheckTools.isPhoneNumber(phone)) {
                request.setAttribute("success", null);
                request.setAttribute("error", "Invalid Value");
                showEditForm(request, response);
            } else {
                if (editCustomer == null) {
                    dis = request.getRequestDispatcher("Customer/error-404.jsp");
                } else {
                    editCustomer.setId(id);
                    editCustomer.setName(name);
                    editCustomer.setEmail(email);
                    editCustomer.setPhone(phone);
                    customerDAO.isUpdateCustomer(editCustomer);
                    request.setAttribute("success", "Customer was edit success!");
                    request.setAttribute("error", null);
                    showEditForm(request, response);
                }

            }
        }
    }

    public void transferE(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String  str_idS = request.getParameter("idS");
        String idStringR = request.getParameter("idR");
        String amountString = request.getParameter("amount");
        String str_fee_percent = request.getParameter("feepercent");
        RequestDispatcher dis;
        if (idStringR == "" || amountString == "" || str_idS == "" || str_fee_percent == "" ) {
            request.setAttribute("success", null);
            request.setAttribute("error", "All field is required");
            showTransfer(request, response);
        }else{
            if (!CheckTools.isNumeric(idStringR) || !CheckTools.isNumeric(amountString) || !CheckTools.isNumeric(str_idS)) {
                request.setAttribute("error", "Transfer isn't successful");
                request.setAttribute("success", null);
                showTransfer(request, response);
            } else {
                int ids = Integer.parseInt(str_idS);
                int idR = Integer.parseInt(idStringR);
                int amount = Integer.parseInt(amountString);
                int fee_percent = Integer.parseInt(str_fee_percent);
                Customer customerS = customerDAO.selectCustomerById(ids);
                Customer customerR = customerDAO.selectCustomerById(idR);
                int total_fee = (amount*fee_percent)/100;
                if (amount >= (customerS.getSalary()+total_fee) || amount <= 0 || customerR == null || ids == idR) {
                    request.setAttribute("error", "Transfer isn't successful");
                    request.setAttribute("success", null);
                    showTransfer(request, response);
                } else {
                    if (!customerDAO.isUpdateTransfer(customerS, customerR, amount, (fee_percent * amount) / 100)) {
                        request.setAttribute("error", "Transfer isn't successful");
                        request.setAttribute("success", null);
                        showTransfer(request, response);
                    } else {

                        Transfer transfer = new Transfer(ids, idR, amount, fee_percent, (fee_percent * amount) / 100);
                        transferDAO.insertTransfer(transfer);
                        request.setAttribute("success", "TranSfer Successful");
                        request.setAttribute("error", null);
                        showTransfer(request, response);
                    }
                }
            }
        }

    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        List<Transfer> transferList = transferDAO.selectAllTransfer();
        Customer customer = customerDAO.selectCustomerById(id);
        RequestDispatcher dis;
        if(customer == null){
            dis = request.getRequestDispatcher("error-404.jsp");
        }else{
            boolean check = true;
            for(Transfer transfer : transferList){
                if(transfer.getIdReceiver() == id || transfer.getIdSender() == id){
                    check = false;
                    break;
                }
            }
            if(check){
                customerDAO.isDeleteCustomer(id);
                List<Customer> customerList = customerDAO.selectAllCustomer();
                request.setAttribute("customers",customerList);
                response.sendRedirect("banking_system");
                request.setAttribute("error",null);
                request.setAttribute("success","Customer was deleted");
            }else{
                response.sendRedirect("banking_system");
                request.setAttribute("error","Traded Customer cannot be deleted");
                request.setAttribute("success",null);

            }

        }
    }

}
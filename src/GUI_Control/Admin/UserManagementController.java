package GUI_Control.Admin;

import DAO.Customer_dataAccess;
import DAO.FlightSchedule_dataAccess;
import Domain.Customer;
import Domain.Flight;
import GUI_Control.Admin.PopUpBoxes.PopUpAlertBox;
import GUI_Control.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static DAO.FlightSchedule_dataAccess.deleteFlight;

public class UserManagementController extends AdminSceneController implements Initializable {

    //Flight List
    private final ObservableList<Customer> listOfCustomersDataList = FXCollections.observableArrayList();

    //TableView of customers
    @FXML private Button searchFlightButton;
    @FXML private TableView<Customer> customerTableView;
    @FXML private TextField filteredField;
    @FXML private TableColumn<Customer, Integer> customerID_Col;
    @FXML private TableColumn<Customer, String> usernameCol;
    @FXML private TableColumn<Customer, String> passwordCol;
    @FXML private TableColumn<Customer, String> firstNameCol;
    @FXML private TableColumn<Customer, String> lastNameCol;

    @FXML private Button backToAdminBtn;

    //menu Buttons\
    @FXML private Button deleteCustomerBtn;
    @FXML private Button addCustomerBtn;

    private Customer selectedCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

          customerID_Col.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
          usernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
          passwordCol.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
          firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
          lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        try {
            listOfCustomersDataList.addAll(Customer_dataAccess.getListOfCustomers());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        customerTableView.setItems(listOfCustomersDataList);
    }

    public void handleButtonAction(javafx.event.ActionEvent event) throws IOException
    {
        if(event.getSource() == backToAdminBtn) {
            //close current
            Stage stage = (Stage) backToAdminBtn.getScene().getWindow();
            stage.close();
            AdminSceneController.initialize();
        }
        else
        {
            Stage stage = (Stage) addCustomerBtn.getScene().getWindow();
            stage.close();
            new Controller().registrationScene(new ActionEvent());
        }
    }

    public static void startUserManagement() throws IOException {
        //Screen initiation
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(UserManagementController.class.getResource("UserManagement.fxml"));
        primaryStage.setTitle("User Management");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    //this methods removes a customer
    public void processDeleteCustomer(javafx.event.ActionEvent event)
    {
        if(event.getSource() == deleteCustomerBtn) {
            selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        }

        try{
            Customer_dataAccess.deleteCustomer(selectedCustomer.getCustomer_id());
            PopUpAlertBox.display("Confirmation", "Customer has been deleted");
        }
        catch (Exception ex){
            ex.getMessage();
        }

    }
}

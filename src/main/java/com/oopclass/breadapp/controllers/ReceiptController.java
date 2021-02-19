package com.oopclass.breadapp.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.models.Receipt;
import com.oopclass.breadapp.services.impl.ReceiptService;
import com.oopclass.breadapp.views.FxmlView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Controller
public class ReceiptController implements Initializable {

    @FXML
    private Label receiptId;
    
    @FXML
    private Label createdAt;
    
    @FXML
    private Label updatedAt;
    
    
    
    

    @FXML
    private TextField name;
    
    @FXML
    private TextField address;

    @FXML
    private TextField amount;
    
    
    @FXML
    private Button Records;
    

  
            
    @FXML
    private DatePicker date;
    
    @FXML
    private ComboBox comb;
    
    
     @FXML 
            private Label unit;
     
     
     

    
 

    

    @FXML
    private Button reset;

    @FXML
    private Button saveReceipt;
    
     @FXML
    private Button deleteReceipts;
     
      @FXML
    private Button LogIN;
      
      @FXML
    private Button LogOUT;
      
      @FXML
    private Button Schedule;
      
       @FXML
    private Button Receipt;
      
      

    @FXML
    private TableView<Receipt> receiptTable;

    @FXML
    private TableColumn<Receipt, Long> colReceiptId;
    
     @FXML
    private TableColumn<Receipt, String> colUnit;
     
     @FXML
    private TableColumn<Receipt, String> colName;

    @FXML
    private TableColumn<Receipt, String> colAddress;

    @FXML
    private TableColumn<Receipt, String> colAmount;

    @FXML
    private TableColumn<Receipt, LocalDate> colDate;
    
     @FXML
    private TableColumn<Receipt, String> colCreated;

       @FXML
    private TableColumn<Receipt, String> colUpdated;
   

    @FXML
    private TableColumn<Receipt, Boolean> colEdit;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ReceiptService receiptService;

    private ObservableList<Receipt> receiptList = FXCollections.observableArrayList();

//    @FXML
//    private void exit(ActionEvent event) {
//        Platform.exit();
//    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    void Select(ActionEvent event){
        String s = comb.getSelectionModel().getSelectedItem().toString();
    unit.setText(s);
    }
    
    
    
    
    
     @FXML
    void Records(ActionEvent event) {
        stageManager.switchScene(FxmlView.REPAIR);
    }
    
     @FXML
    void Schedule(ActionEvent event) {
        stageManager.switchScene(FxmlView.SCHEDULE);
    }
    
     @FXML
    void Receipt(ActionEvent event) {
        stageManager.switchScene(FxmlView.RECEIPT);
    }
    
    
     public void createdAt(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       createdAt.setText(dtf.format(now));
       
    }
      public void updatedAt(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       updatedAt.setText(dtf.format(now));
       
    }
                  
    
     
     
    @FXML
    private void LogOUT(ActionEvent event){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Logout?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
             stageManager.switchScene(FxmlView.LOGIN);
        }
        
        
       
    }
    
    
    @FXML
    private void saveReceipt(ActionEvent event) {

        if (
                
                 emptyValidation("DOB", date.getEditor().getText().isEmpty())
                ) {

            if (receiptId.getText() == null || "".equals(receiptId.getText())) {
                if (true) {

                    Receipt receipt = new Receipt();
                    receipt.setName(getName());
                    receipt.setCreatedAt(getCreatedAt());
                    receipt.setUpdatedAt(getCreatedAt());
                    receipt.setAddress(getAddress());
                    receipt.setAmount(getAmount());
                    receipt.setUnit(getUnit());
                    receipt.setDate(getDate());
                    
                    

                    Receipt newReceipt = receiptService.save(receipt);

                    saveAlert(newReceipt);
                }

            } else {
                Receipt receipt = receiptService.find(Long.parseLong(receiptId.getText()));
                receipt.setAddress(getAddress());
                receipt.setAmount(getAmount());
                receipt.setName(getName());
               receipt.setCreatedAt(getCreatedAt());
               receipt.setUpdatedAt(getUpdatedAt());
               receipt.setUnit(getUnit());
                receipt.setDate(getDate());
                
                Receipt updatedReceipt = receiptService.update(receipt);
                updateAlert(updatedReceipt);
            }

            
            
            
            
            
            
            
            clearFields();
            loadReceiptDetails();
        }

    }

    @FXML
    private void deleteReceipts(ActionEvent event) {
        List<Receipt> receipts = receiptTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            receiptService.deleteInBatch(receipts);
        }

        loadReceiptDetails();
    }

    private void clearFields() {
        receiptId.setText(null);
        address.clear();
        amount.clear();
        createdAt.setText(null);
         updatedAt.setText(null);
        
        name.clear();
        unit.setText("Units");
       
    
        
        date.getEditor().clear();
      
    }

    private void saveAlert(Receipt receipt) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Receipt saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The receipt Id " + receipt.getId()+  " has been created.");
        alert.showAndWait();
    }

    private void updateAlert(Receipt receipt) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Receipt updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The receipt Id " + receipt.getId() +" has been updated.");
        alert.showAndWait();
    }

    

    public String getAddress() {
        return address.getText();
    }

    
 public String getCreatedAt(){
     return createdAt.getText();
 }
 
 public String getUpdatedAt(){
     return updatedAt.getText();
 }
    
    public String getAmount() {
       
        return amount.getText();
    }
    
    public String getUnit() {
        return unit.getText();
    }
     
     public String getName() {
        return name.getText();
    }
  

    public LocalDate getDate() {
        return date.getValue();
    }

   


    /*
	 *  Set All receiptTable column properties
     */
    private void setColumnProperties() {
        /* Override date format in table
		 * colDOB.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd/MM/yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));*/

        colReceiptId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
         colCreated.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
              colUpdated.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
         
        
        
        
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Receipt, Boolean>, TableCell<Receipt, Boolean>> cellFactory
            = new Callback<TableColumn<Receipt, Boolean>, TableCell<Receipt, Boolean>>() {
        @Override
        public TableCell<Receipt, Boolean> call(final TableColumn<Receipt, Boolean> param) {
            final TableCell<Receipt, Boolean> cell;
            cell = new TableCell<Receipt, Boolean>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEdit.setOnAction(e -> {
                            Receipt receipt = getTableView().getItems().get(getIndex());
                            updateReceipt(receipt);
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                private void updateReceipt(Receipt receipt) {
                    
                    receiptId.setText(Long.toString(receipt.getId()));
                    address.setText(receipt.getAddress());
                    name.setText(receipt.getName());
                   amount.setText(receipt.getAmount());
                   unit.setText(receipt.getUnit());
                   createdAt.setText(receipt.getCreatedAt());
                    updatedAt.setText(receipt.getUpdatedAt());
                   
                    date.setValue(receipt.getDate());
                    
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All receipts to observable list and update table
     */
    private void loadReceiptDetails() {
        receiptList.clear();
        receiptList.addAll(receiptService.findAll());

        receiptTable.setItems(receiptList);
    }

    /*
	 * Validations
     */
    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) {
            alert.setContentText("Please Select " + field);
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }
    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {

         
        createdAt();
        updatedAt();
        
        
        receiptTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        
        ObservableList<String> list = FXCollections.observableArrayList("Ryzen3 3200g", "Rx580", "BarraCuda Hard Drive 1tb", "Hyper X Fury 8gb");
        comb.setItems(list);

        // Add all receipts into table
        loadReceiptDetails();
    }
}

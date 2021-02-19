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
import com.oopclass.breadapp.models.Repair;
import com.oopclass.breadapp.services.impl.RepairService;
import com.oopclass.breadapp.views.FxmlView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
public class RepairController implements Initializable {

    @FXML
    private Label repairId;
    
    @FXML
    private Label status;
    
@FXML
    private Label createdAt;

@FXML
    private Label updatedAt;


    @FXML
    private TextField name;
    
    @FXML
    private TextField address;
    
     @FXML
    private TextField contact;
     
      @FXML
    private TextField unit;

   
    
    
    
    

  
            
    @FXML
    private DatePicker date;
    
  
    
    @FXML
    private ComboBox comb;
    

    
 

    

    @FXML
    private Button reset;

    @FXML
    private Button saveRepair;
    
     @FXML
    private Button deleteRepair;
     

      
      @FXML
    private Button Back;

    @FXML
    private TableView<Repair> repairTable;

    @FXML
    private TableColumn<Repair, Long> colRepairId;
    
     @FXML
    private TableColumn<Repair, String> colCreated;
     
     @FXML
    private TableColumn<Repair, String> colUpdated;
    
    
     
     @FXML
    private TableColumn<Repair, String> colContact;
     
     @FXML
    private TableColumn<Repair, String> colUnit;
     
     @FXML
    private TableColumn<Repair, String> colName;

    @FXML
    private TableColumn<Repair, String> colAddress;

  

    @FXML
    private TableColumn<Repair, LocalDate> colDate;
    
    @FXML
    private TableColumn<Repair, String> colStatus;

   

    @FXML
    private TableColumn<Repair, Boolean> colEdit;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private RepairService repairService;

    private ObservableList<Repair> repairList = FXCollections.observableArrayList();

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
    status.setText(s);
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
    private void Repair(ActionEvent event) {
      stageManager.switchScene(FxmlView.REPAIR);
    }
    
      @FXML
    private void Schedule(ActionEvent event) {
      stageManager.switchScene(FxmlView.SCHEDULE);
    }
    
     @FXML
    private void Receipt(ActionEvent event) {
      stageManager.switchScene(FxmlView.RECEIPT);
    }
    
    @FXML
    private void saveRepair(ActionEvent event) {

        if (validate("Address", getAddress(), "([a-zA-Z]{3,30}\\s*)+")
                
                && emptyValidation("DOB", date.getEditor().getText().isEmpty())
                ) {

            if (repairId.getText() == null || "".equals(repairId.getText())) {
                if (true) {

                    Repair repair = new Repair();
                    repair.setName(getName());
                    repair.setAddress(getAddress());
                    repair.setCreatedAt(getCreatedAt());
              repair.setUpdatedAt(getUpdatedAt());
                    repair.setContact(getContact());
                    repair.setStatus(getStatus());
                    repair.setUnit(getUnit());
                    
                    
                    repair.setDate(getDate());
                    
                    

                    Repair newRepair = repairService.save(repair);

                    saveAlert(newRepair);
                }

            } else {
                Repair repair = repairService.find(Long.parseLong(repairId.getText()));
                repair.setAddress(getAddress());
               
                repair.setName(getName());
                repair.setContact(getContact());
              repair.setStatus(getStatus());
              repair.setUnit(getUnit());
              repair.setCreatedAt(getCreatedAt());
              repair.setUpdatedAt(getUpdatedAt());
                repair.setDate(getDate());
                
                Repair updatedRepair = repairService.update(repair);
                updateAlert(updatedRepair);
            }

            
            
            
            
            
            
            
            clearFields();
            loadRepairDetails();
        }

    }

    @FXML
    private void deleteRepair(ActionEvent event) {
        List<Repair> repairs = repairTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            repairService.deleteInBatch(repairs);
        }

        loadRepairDetails();
    }

    private void clearFields() {
        repairId.setText(null);
        address.clear();
      contact.clear();
      createdAt.setText(null);
      updatedAt.setText(null);
      unit.clear();
      status.setText(null);
        name.clear();
      
       
    
        
        date.getEditor().clear();
      
    }

    private void saveAlert(Repair repair) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Repair saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The repair Id " + repair.getId() + " has been created.");
        alert.showAndWait();
    }

    private void updateAlert(Repair repair) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Repair updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The repair Id" + repair.getId() + " has been updated.");
        alert.showAndWait();
    }

    

    
    
    public String getAddress() {
        return address.getText();
    }
    
    public String getUnit() {
        return unit.getText();
    }
    
    
    public String getUpdatedAt(){
        return updatedAt.getText();
    }
  
    
   
 public String getCreatedAt(){
     return createdAt.getText();
 }
    
    public String getStatus(){
        return status.getText();
    }
    
   
     
     public String getName() {
        return name.getText();
    }
  
      public String getContact() {
        return contact.getText();
    }
     
     

    public LocalDate getDate() {
        return date.getValue();
    }

   


    /*
	 *  Set All repairTable column properties
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

        colRepairId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit")); 
         colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
          colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
      colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
       
       
        
        colCreated.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
         colUpdated.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
        
        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Repair, Boolean>, TableCell<Repair, Boolean>> cellFactory
            = new Callback<TableColumn<Repair, Boolean>, TableCell<Repair, Boolean>>() {
        @Override
        public TableCell<Repair, Boolean> call(final TableColumn<Repair, Boolean> param) {
            final TableCell<Repair, Boolean> cell;
            cell = new TableCell<Repair, Boolean>() {
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
                            Repair repair = getTableView().getItems().get(getIndex());
                            updateRepair(repair);
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

                private void updateRepair(Repair repair) {
                    
                    repairId.setText(Long.toString(repair.getId()));
                    address.setText(repair.getAddress());
                    unit.setText(repair.getUnit());
                    createdAt.setText(repair.getCreatedAt());
                    updatedAt.setText(repair.getUpdatedAt());
                    status.setText(repair.getStatus());
                    name.setText(repair.getName());
                contact.setText(repair.getContact());
                    date.setValue(repair.getDate());
                    
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All repairs to observable list and update table
     */
    private void loadRepairDetails() {
        repairList.clear();
        repairList.addAll(repairService.findAll());

        repairTable.setItems(repairList);
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
     
        
        
        repairTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();
        
        
         ObservableList<String> list = FXCollections.observableArrayList("Done", "Pending", "UnRepair");
        comb.setItems(list);
        
       
        // Add all repairs into table
        loadRepairDetails();
    }

   
    
}

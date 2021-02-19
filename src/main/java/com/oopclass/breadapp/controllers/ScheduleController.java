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
import com.oopclass.breadapp.models.Schedule;
import com.oopclass.breadapp.services.impl.ScheduleService;
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
public class ScheduleController implements Initializable {

    @FXML
    private Label scheduleId;

    @FXML
    private Label createdAt;

    @FXML
    private Label updatedAt;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

   

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox comb;

    @FXML
    private Button reset;

    @FXML
    private Button saveSchedule;

    @FXML
    private Button deleteSchedule;

    @FXML
    private Button Repair;

    @FXML
    private Button Schedule;

    @FXML
    private Button Receipt;

    @FXML
    private TableView<Schedule> scheduleTable;

    @FXML
    private TableColumn<Schedule, Long> colScheduleId;

   

    @FXML
    private TableColumn<Schedule, String> colName;

    @FXML
    private TableColumn<Schedule, String> colAddress;

    @FXML
    private TableColumn<Schedule, LocalDate> colDate;

    @FXML
    private TableColumn<Schedule, String> colCreated;

    @FXML
    private TableColumn<Schedule, String> colUpdated;

    @FXML
    private TableColumn<Schedule, Boolean> colEdit;

    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private ScheduleService scheduleService;

    private ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();

//    @FXML
//    private void exit(ActionEvent event) {
//        Platform.exit();
//    }
    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    public void createdAt() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        createdAt.setText(dtf.format(now));

    }

    public void updatedAt() {
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
    private void saveSchedule(ActionEvent event) {

        if (emptyValidation("DOB", date.getEditor().getText().isEmpty())) {

            if (scheduleId.getText() == null || "".equals(scheduleId.getText())) {
                if (true) {

                    Schedule schedule = new Schedule();
                    schedule.setName(getName());
                    schedule.setCreatedAt(getCreatedAt());

                 
                    schedule.setUpdatedAt(getCreatedAt());
                    schedule.setAddress(getAddress());

                    schedule.setDate(getDate());

                    Schedule newSchedule = scheduleService.save(schedule);

                    saveAlert(newSchedule);
                }

            } else {
                Schedule schedule = scheduleService.find(Long.parseLong(scheduleId.getText()));
                schedule.setAddress(getAddress());

                schedule.setName(getName());
                schedule.setCreatedAt(getCreatedAt());
               
                schedule.setUpdatedAt(getUpdatedAt());

                schedule.setDate(getDate());

                Schedule updatedSchedule = scheduleService.update(schedule);
                updateAlert(updatedSchedule);
            }

            clearFields();
            loadScheduleDetails();
        }

    }

    @FXML
    private void deleteSchedule(ActionEvent event) {
        List<Schedule> schedules = scheduleTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            scheduleService.deleteInBatch(schedules);
        }

        loadScheduleDetails();
    }

    private void clearFields() {
        scheduleId.setText(null);
        address.clear();
       
        createdAt.setText(null);
        updatedAt.setText(null);

        name.clear();

        date.getEditor().clear();

    }

    private void saveAlert(Schedule schedule) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Schedule saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The schedule Id " + schedule.getId() + " has been created.");
        alert.showAndWait();
    }

    private void updateAlert(Schedule schedule) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Schedule updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The schedule Id " + schedule.getId() + " has been updated.");
        alert.showAndWait();
    }

    public String getAddress() {
        return address.getText();
    }

  

    public String getCreatedAt() {
        return createdAt.getText();
    }

    public String getUpdatedAt() {
        return updatedAt.getText();
    }

    public String getName() {
        return name.getText();
    }

    public LocalDate getDate() {
        return date.getValue();
    }

    /*
	 *  Set All scheduleTable column properties
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

        colScheduleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        colCreated.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colUpdated.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        colEdit.setCellFactory(cellFactory);
    }

    Callback<TableColumn<Schedule, Boolean>, TableCell<Schedule, Boolean>> cellFactory
            = new Callback<TableColumn<Schedule, Boolean>, TableCell<Schedule, Boolean>>() {
        @Override
        public TableCell<Schedule, Boolean> call(final TableColumn<Schedule, Boolean> param) {
            final TableCell<Schedule, Boolean> cell;
            cell = new TableCell<Schedule, Boolean>() {
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
                            Schedule schedule = getTableView().getItems().get(getIndex());
                            updateSchedule(schedule);
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

                private void updateSchedule(Schedule schedule) {

                    scheduleId.setText(Long.toString(schedule.getId()));
                    address.setText(schedule.getAddress());
                    name.setText(schedule.getName());
                   

                    createdAt.setText(schedule.getCreatedAt());
                    updatedAt.setText(schedule.getUpdatedAt());

                    date.setValue(schedule.getDate());

                }
            };
            return cell;
        }
    };

    /*
	 *  Add All schedules to observable list and update table
     */
    private void loadScheduleDetails() {
        scheduleList.clear();
        scheduleList.addAll(scheduleService.findAll());

        scheduleTable.setItems(scheduleList);
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

        scheduleTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        // Add all schedules into table
        loadScheduleDetails();
    }
}

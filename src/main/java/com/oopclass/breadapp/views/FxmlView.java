package com.oopclass.breadapp.views;

import java.util.ResourceBundle;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
public enum FxmlView {

    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    }
    ,RECEIPT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("receipt.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Receipt.fxml";
        }
    }
        
       
        
         ,REPAIR {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("repair.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Repair.fxml";
        }
         }
         ,SCHEDULE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("schedule.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Schedule.fxml";
        }
         };
        
      
        
    public abstract String getTitle();

    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
    

}

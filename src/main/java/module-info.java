module com.mycompany.llistacontinguts_nachodeinza {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql; 
    requires java.desktop; 
    requires javafx.base;
    opens com.mycompany.llistacontinguts_nachodeinza to javafx.fxml;
    exports com.mycompany.llistacontinguts_nachodeinza;
}

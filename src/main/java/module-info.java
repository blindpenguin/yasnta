module de.limeonfire.yasnta {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.jdbc;
    requires java.sql;


    opens de.limeonfire.yasnta to javafx.fxml, ormlite.jdbc;
    exports de.limeonfire.yasnta;
}
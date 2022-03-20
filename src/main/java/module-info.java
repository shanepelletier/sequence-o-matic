module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens me.shanepelletier.sequenceomatic to javafx.fxml;
    exports me.shanepelletier.sequenceomatic;
}
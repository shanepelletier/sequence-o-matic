module me.shanepelletier.sequenceomatic {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.media;


    opens me.shanepelletier.sequenceomatic to javafx.fxml;
    exports me.shanepelletier.sequenceomatic;
}
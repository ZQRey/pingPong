module com.zqrey.pingpong {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.zqrey.pingpong to javafx.fxml;
    exports com.zqrey.pingpong;
}
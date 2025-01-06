module cn.rainblood.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires Sky.Dragon;
    requires java.sql;

    opens io.github.rainblooding to javafx.fxml;
    opens io.github.rainblooding.fx.gui to javafx.fxml;
    exports io.github.rainblooding.fx.learn;
    exports io.github.rainblooding.fx.gui;
    exports io.github.rainblooding.paidcar.car;
    opens io.github.rainblooding.paidcar.car to javafx.fxml;
    exports io.github.rainblooding.fx;
    opens io.github.rainblooding.fx to javafx.fxml;
}
module cabbypatty.cab302_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens cabbypatty.cab302_assignment.controller to javafx.fxml;
    exports cabbypatty.cab302_assignment.controller;
    exports cabbypatty.cab302_assignment.utils;
    exports cabbypatty.cab302_assignment;
    opens cabbypatty.cab302_assignment to javafx.fxml;
}
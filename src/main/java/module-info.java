module com.example.pt2023_talumircea_30433_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.example.pt2023_talumircea_30433_assignment_1.model;
    opens com.example.pt2023_talumircea_30433_assignment_1.model to javafx.fxml;
    exports com.example.pt2023_talumircea_30433_assignment_1.view;
    opens com.example.pt2023_talumircea_30433_assignment_1.view to javafx.fxml;
}
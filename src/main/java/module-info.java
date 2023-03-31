module com.example.pt2023_talumircea_30433_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pt2023_talumircea_30433_assignment_1 to javafx.fxml;
    exports com.example.pt2023_talumircea_30433_assignment_1;
    exports com.example.pt2023_talumircea_30433_assignment_1.Model;
    opens com.example.pt2023_talumircea_30433_assignment_1.Model to javafx.fxml;
    exports com.example.pt2023_talumircea_30433_assignment_1.View;
    opens com.example.pt2023_talumircea_30433_assignment_1.View to javafx.fxml;
}
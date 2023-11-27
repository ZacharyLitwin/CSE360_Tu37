package prototype;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PlanningPoker{

    private List<String> teamMembers;
    private String task;
    private List<Integer> estimates;
    private Stage primaryStage;
    
    public PlanningPoker(Stage stage) {
    	primaryStage = stage;
    }

    public void displayPlanningPoker() {
        primaryStage.setTitle("Planning Poker with Labels");
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Team Members Input
        TextField teamMembersField = new TextField();
        teamMembersField.setPromptText("Enter team members (comma-separated)");
        Button startButton = new Button("Start Planning Poker");

        // Task Input
        TextField taskField = new TextField();
        taskField.setPromptText("Enter task/user story");

        HBox labels = new HBox(10);
        for (int estimate = 0; estimate <= 100; estimate += 5) {
        	final int finalestimate = estimate;
            Label label = new Label(String.valueOf(estimate));
            label.setOnMouseClicked(event -> estimateWithLabel(finalestimate));
            labels.getChildren().add(label);
        }

        // Estimates Input
        ListView<String> estimateList = new ListView<>();

        startButton.setOnAction(event -> {
            teamMembers = new ArrayList<>();
            String teamMembersText = teamMembersField.getText();
            teamMembers.addAll(List.of(teamMembersText.split(",")));

            task = taskField.getText();
            estimates = new ArrayList();

            // Shuffle the order of team members for anonymity
            java.util.Collections.shuffle(teamMembers);

            teamMembersField.setDisable(true);
            taskField.setDisable(true);
            labels.setDisable(false);
        });

        root.getChildren().addAll(teamMembersField, startButton, taskField, labels, estimateList);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void estimateWithLabel(int estimate) {
        if (estimates != null) {
            estimates.add(estimate);
            ListView<String> estimateList = new ListView<>();
            estimateList.getItems().add("Estimate: " + estimate);

            if (estimates.size() >= teamMembers.size()) {
                showAverageEstimate();
            }
        }
    }

    private void showAverageEstimate() {
        if (estimates != null && !estimates.isEmpty()) {
            double totalEstimate = estimates.stream().mapToDouble(Integer::doubleValue).sum();
            double averageEstimate = totalEstimate / estimates.size();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Average Estimate");
            alert.setHeaderText(null);
            alert.setContentText("Average estimate for '" + task + "': " + averageEstimate);
            alert.showAndWait();
        }
    }
}

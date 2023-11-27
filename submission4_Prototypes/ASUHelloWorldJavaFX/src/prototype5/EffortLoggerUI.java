package prototype5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class EffortLoggerUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
    	
    	
    	//-----------------START OF EFFORTLOGGER 2.0 CONSOLE----------------------------------
    	
    	
    	//Text Fields
        primaryStage.setTitle("EffortLogger 2.0 Console");
        Text text1 = new Text(20,20,"EffortLogger-2.0 Console"); 
    	text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 40));
    	Text text2 = new Text(20,20,"Press the button below to start an activity."); 
    	text2.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
    	Text text3 = new Text(20,20,"Select the project, life cycle step, effort category, and deliverable."); 
    	text3.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
    	Text text4 = new Text(20,20,"Press the button below to stop the activity."); 
    	text4.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
    	Text text5 = new Text(20,20,"Project:"); 
    	text5.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
    	Text text6 = new Text(20,20,"Life Cycle Step:"); 
    	text6.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
    	Text text7 = new Text(20,20,"Effort Category:"); 
    	text7.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
    	Text text8 = new Text(20,20,"Deliverable:"); 
    	text8.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));

    	
    	//Buttons 
        Button btn1 = new Button();
        btn1.setText("Start an Activity");
        Button btn2 = new Button();
        btn2.setText("Effort Log Editor");
        Button btn3 = new Button();
        btn3.setText("Defect Log Console");
        Button btn4 = new Button();
        btn4.setText("Definitions");
        Button btn5 = new Button();
        btn5.setText("Effort and Defect Logs");
        Button btn6 = new Button();
        btn6.setText("Stop the Activity");
       
        
        //Choice Boxes
        ChoiceBox<String> cbox1 = new ChoiceBox<String>();
        cbox1.getItems().add("Example 1");
        cbox1.getItems().add("Example 2");
        cbox1.getItems().add("Example 3");
        ChoiceBox<String> cbox2 = new ChoiceBox<String>();
        cbox2.getItems().add("Example 1");
        cbox2.getItems().add("Example 2");
        cbox2.getItems().add("Example 3");
        ChoiceBox<String> cbox3 = new ChoiceBox<String>();
        cbox3.getItems().add("Example 1");
        cbox3.getItems().add("Example 2");
        cbox3.getItems().add("Example 3");
        ChoiceBox<String> cbox4 = new ChoiceBox<String>();
        cbox4.getItems().add("Example 1");
        cbox4.getItems().add("Example 2");
        cbox4.getItems().add("Example 3");
        
        
        //Buttons
        Pane root = new Pane();
        btn1.setLayoutY(150);
        btn1.setLayoutX(375);
        btn1.setPrefSize(150, 50);
        btn1.setStyle("-fx-font-size: 15; ");
        root.getChildren().add(btn1);
        btn2.setLayoutY(625);
        btn2.setLayoutX(100);
        btn2.setPrefSize(150, 50);
        btn2.setStyle("-fx-font-size: 15; "); 
        root.getChildren().add(btn2);
        btn3.setLayoutY(625);
        btn3.setLayoutX(275);
        btn3.setPrefSize(150, 50);
        btn3.setStyle("-fx-font-size: 15; ");
        root.getChildren().add(btn3);
        btn4.setLayoutY(625);
        btn4.setLayoutX(450);
        btn4.setPrefSize(150, 50);
        btn4.setStyle("-fx-font-size: 15; ");
        root.getChildren().add(btn4);
        btn5.setLayoutY(625);
        btn5.setLayoutX(625);
        btn5.setPrefSize(200, 50);
        btn5.setStyle("-fx-font-size: 15; ");
        root.getChildren().add(btn5);
        btn6.setLayoutY(525);
        btn6.setLayoutX(350);
        btn6.setPrefSize(200, 50);
        btn6.setStyle("-fx-font-size: 15; ");
        root.getChildren().add(btn6);
        
        
        //Text Fields
        text1.setLayoutY(20);
        text1.setLayoutX(200);
        root.getChildren().add(text1);
        text2.setLayoutY(100);
        text2.setLayoutX(250);
        root.getChildren().add(text2);
        text3.setLayoutY(225);
        text3.setLayoutX(150);
        root.getChildren().add(text3);
        text4.setLayoutY(475);
        text4.setLayoutX(250);
        root.getChildren().add(text4);
        text5.setLayoutY(275);
        text5.setLayoutX(100);
        root.getChildren().add(text5);
        text6.setLayoutY(275);
        text6.setLayoutX(500);
        root.getChildren().add(text6);
        text7.setLayoutY(375);
        text7.setLayoutX(100);
        root.getChildren().add(text7);
        text8.setLayoutY(375);
        text8.setLayoutX(500);
        root.getChildren().add(text8);
        
        
        //Choice Boxes
        cbox1.setLayoutY(315);
        cbox1.setLayoutX(100);
        cbox1.setPrefSize(300, 20);
        root.getChildren().add(cbox1);
        cbox2.setLayoutY(315);
        cbox2.setLayoutX(520);
        cbox2.setPrefSize(300, 20);
        root.getChildren().add(cbox2);
        cbox3.setLayoutY(415);
        cbox3.setLayoutX(100);
        cbox3.setPrefSize(300, 20);
        root.getChildren().add(cbox3);
        cbox4.setLayoutY(415);
        cbox4.setLayoutX(520);
        cbox4.setPrefSize(300, 20);
        root.getChildren().add(cbox4);

        Scene scene1 = new Scene(root, 900, 700);
        
        
        //-----------------END OF EFFORTLOGGER 2.0 CONSOLE----------------------------------
        
        
        //-----------------START OF EFFORTLOGGER 2.0 EDITOR----------------------------------
        
     
        Pane root2 = new Pane();
        
        
        //Text Fields
        primaryStage.setTitle("Effort Log Editor");
        Text btext1 = new Text(20,20,"Effort Log Editor"); 
        btext1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 40));
        Text btext2 = new Text(20,20,"Options to delete, update, or split the current entry."); 
        btext2.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext3 = new Text(20,20,"Select project to edit."); 
        btext3.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext4 = new Text(20,20,"Select effort log to be modified."); 
        btext4.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext5 = new Text(20,20,"Modify the information of the currently selected Effort log and press update entry."); 
        btext5.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext6 = new Text(20,20,"Start time:"); 
        btext6.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext7 = new Text(20,20,"Stop Time:"); 
        btext7.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext8 = new Text(20,20,"Date:"); 
        btext8.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext9 = new Text(20,20,"Life Cycle Step:"); 
        btext9.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext10 = new Text(20,20,"Effort Category:"); 
        btext10.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text btext11 = new Text(20,20,"yyyy-mm-dd"); 
        btext11.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
        Text btext12 = new Text(20,20,"hh:mm:ss"); 
        btext12.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
        Text btext13 = new Text(20,20,"hh:mm:ss"); 
        btext13.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));

        
        //Buttons
        Button bbtn1 = new Button();
        bbtn1.setText("Split Entry");
        Button bbtn2 = new Button();
        bbtn2.setText("Effort Log Console");
        Button bbtn3 = new Button();
        bbtn3.setText("Delete Entry");
        Button bbtn4 = new Button();
        bbtn4.setText("Update Entry");
        Button bbtn5 = new Button();
        bbtn5.setText("Clear Log");
        
        
        //Choice Boxes
        ChoiceBox<String> bcbox1 = new ChoiceBox<String>();
        bcbox1.getItems().add("Example 1");
        bcbox1.getItems().add("Example 2");
        bcbox1.getItems().add("Example 3");
        ChoiceBox<String> bcbox2 = new ChoiceBox<String>();
        bcbox2.getItems().add("Example 1");
        bcbox2.getItems().add("Example 2");
        bcbox2.getItems().add("Example 3");
        ChoiceBox<String> bcbox3 = new ChoiceBox<String>();
        bcbox3.getItems().add("Example 1");
        bcbox3.getItems().add("Example 2");
        bcbox3.getItems().add("Example 3");
        ChoiceBox<String> bcbox4 = new ChoiceBox<String>();
        bcbox4.getItems().add("Example 1");
        bcbox4.getItems().add("Example 2");
        bcbox4.getItems().add("Example 3");
        ChoiceBox<String> bcbox5 = new ChoiceBox<String>();
        bcbox5.getItems().add("Example 1");
        bcbox5.getItems().add("Example 2");
        bcbox5.getItems().add("Example 3");
        
        
        //Text Boxes
        TextField btf1 = new TextField();
        TextField btf2 = new TextField();
        TextField btf3 = new TextField();
        
        
        //Buttons
        bbtn1.setLayoutY(625);
        bbtn1.setLayoutX(200);
        bbtn1.setPrefSize(150, 50);
        bbtn1.setStyle("-fx-font-size: 15; ");
        root2.getChildren().add(bbtn1);
        bbtn2.setLayoutY(625);
        bbtn2.setLayoutX(650);
        bbtn2.setPrefSize(150, 50);
        bbtn2.setStyle("-fx-font-size: 15; "); 
        root2.getChildren().add(bbtn2);
        bbtn3.setLayoutY(625);
        bbtn3.setLayoutX(40);
        bbtn3.setPrefSize(150, 50);
        bbtn3.setStyle("-fx-font-size: 15; ");
        root2.getChildren().add(bbtn3);
        bbtn4.setLayoutY(625);
        bbtn4.setLayoutX(360);
        bbtn4.setPrefSize(150, 50);
        bbtn4.setStyle("-fx-font-size: 15; ");
        root2.getChildren().add(bbtn4);
        bbtn5.setLayoutY(125);
        bbtn5.setLayoutX(600);
        bbtn5.setPrefSize(150, 50);
        bbtn5.setStyle("-fx-font-size: 15; ");
        root2.getChildren().add(bbtn5);
        
        
        //Text Fields
        btext1.setLayoutY(20);
        btext1.setLayoutX(275);
        root2.getChildren().add(btext1);
        btext2.setLayoutY(575);
        btext2.setLayoutX(30);
        root2.getChildren().add(btext2);
        btext3.setLayoutY(75);
        btext3.setLayoutX(50);
        root2.getChildren().add(btext3);
        btext4.setLayoutY(175);
        btext4.setLayoutX(50);
        root2.getChildren().add(btext4);
        btext5.setLayoutY(275);
        btext5.setLayoutX(50);
        root2.getChildren().add(btext5);
        btext6.setLayoutY(310);
        btext6.setLayoutX(50);
        root2.getChildren().add(btext6);
        btext7.setLayoutY(310);
        btext7.setLayoutX(325);
        root2.getChildren().add(btext7);
        btext8.setLayoutY(310);
        btext8.setLayoutX(600);
        root2.getChildren().add(btext8);
        btext9.setLayoutY(375);
        btext9.setLayoutX(50);
        root2.getChildren().add(btext9);
        btext10.setLayoutY(475);
        btext10.setLayoutX(50);
        root2.getChildren().add(btext10);
        btext11.setLayoutY(335);
        btext11.setLayoutX(680);
        root2.getChildren().add(btext11);
        btext12.setLayoutY(335);
        btext12.setLayoutX(460);
        root2.getChildren().add(btext12);
        btext13.setLayoutY(335);
        btext13.setLayoutX(180);
        root2.getChildren().add(btext13);
        
       
        //Choice Boxes
        bcbox1.setLayoutY(125);
        bcbox1.setLayoutX(100);
        bcbox1.setPrefSize(400, 20);
        root2.getChildren().add(bcbox1);
        bcbox2.setLayoutY(525);
        bcbox2.setLayoutX(100);
        bcbox2.setPrefSize(250, 20);
        root2.getChildren().add(bcbox2);
        bcbox3.setLayoutY(425);
        bcbox3.setLayoutX(100);
        bcbox3.setPrefSize(350, 20);
        root2.getChildren().add(bcbox3);
        bcbox4.setLayoutY(225);
        bcbox4.setLayoutX(100);
        bcbox4.setPrefSize(300, 20);
        root2.getChildren().add(bcbox4);
        bcbox5.setLayoutY(525);
        bcbox5.setLayoutX(375);
        bcbox5.setPrefSize(450, 20);
        root2.getChildren().add(bcbox5);
        
        
        //Text Boxes
        btf1.setLayoutY(310);
        btf1.setLayoutX(175);
        btf1.setPrefSize(125, 20);
        root2.getChildren().add(btf1);
        btf2.setLayoutY(310);
        btf2.setLayoutX(450);
        btf2.setPrefSize(125, 20);
        root2.getChildren().add(btf2);
        btf3.setLayoutY(310);
        btf3.setLayoutX(680);
        btf3.setPrefSize(125, 20);
        root2.getChildren().add(btf3);
        
        
        Scene scene2 = new Scene(root2, 900, 700);
        
        
        //-----------------END OF EFFORTLOGGER 2.0 EDITOR----------------------------------
        
        
        //-----------------START OF DEFECT CONSOLE-----------------------------------------
        
        
        Pane root3 = new Pane();
        
        
        //Text Fields
        primaryStage.setTitle("Defect Console");
        Text ctext1 = new Text(20,20,"Defect Console"); 
        ctext1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 40));
        Text ctext2 = new Text(20,20,"Fix: "); 
        ctext2.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext3 = new Text(20,20,"Select project."); 
        ctext3.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext4 = new Text(20,20,"Select decfect to be modified or press the create defect button."); 
        ctext4.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext5 = new Text(20,20,"Modify the current defects attributes."); 
        ctext5.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext6 = new Text(20,20,"Defect Name:"); 
        ctext6.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext7 = new Text(20,20,"Status: "); 
        ctext7.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext8 = new Text(20,20,"Step when removed"); 
        ctext8.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext9 = new Text(20,20,"Defect causes and proposed resolutions: "); 
        ctext9.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext10 = new Text(20,20,"Step when injected"); 
        ctext10.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        Text ctext11 = new Text(20,20,"Defect Category "); 
        ctext11.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        
        //Buttons
        Button cbtn1 = new Button();
        cbtn1.setText("Delete Defect");
        Button cbtn2 = new Button();
        cbtn2.setText("Effort Log Console");
        Button cbtn3 = new Button();
        cbtn3.setText("Update Defect");
        Button cbtn4 = new Button();
        cbtn4.setText("Create Defect");
        Button cbtn5 = new Button();
        cbtn5.setText("Clear Defect Log");
        Button cbtn6 = new Button();
        cbtn6.setText("Close");
        Button cbtn7 = new Button();
        cbtn7.setText("Open/Reopen");
        
        
        //Choice Boxes
        ChoiceBox<String> ccbox1 = new ChoiceBox<String>();
        ccbox1.getItems().add("Example 1");
        ccbox1.getItems().add("Example 2");
        ccbox1.getItems().add("Example 3");
        ChoiceBox<String> ccbox2 = new ChoiceBox<String>();
        ccbox2.getItems().add("Example 1");
        ccbox2.getItems().add("Example 2");
        ccbox2.getItems().add("Example 3");
        ChoiceBox<String> ccbox3 = new ChoiceBox<String>();
        ccbox3.getItems().add("Example 1");
        ccbox3.getItems().add("Example 2");
        ccbox3.getItems().add("Example 3");
        ChoiceBox<String> ccbox4 = new ChoiceBox<String>();
        ccbox4.getItems().add("Example 1");
        ccbox4.getItems().add("Example 2");
        ccbox4.getItems().add("Example 3");
        ChoiceBox<String> ccbox5 = new ChoiceBox<String>();
        ccbox5.getItems().add("Example 1");
        ccbox5.getItems().add("Example 2");
        ccbox5.getItems().add("Example 3");
        ChoiceBox<String> ccbox6 = new ChoiceBox<String>();
        ccbox6.getItems().add("Example 1");
        ccbox6.getItems().add("Example 2");
        ccbox6.getItems().add("Example 3");
        ChoiceBox<String> ccbox7 = new ChoiceBox<String>();
        ccbox7.getItems().add("Example 1");
        ccbox7.getItems().add("Example 2");
        ccbox7.getItems().add("Example 3");
        
        
        //Text Boxes
        TextField ctf1 = new TextField();
        TextField ctf2 = new TextField();
        
        
        //Buttons
        cbtn1.setLayoutY(625);
        cbtn1.setLayoutX(375);
        cbtn1.setPrefSize(150, 50);
        cbtn1.setStyle("-fx-font-size: 15; ");
        root3.getChildren().add(cbtn1);
        cbtn2.setLayoutY(625);
        cbtn2.setLayoutX(575);
        cbtn2.setPrefSize(150, 50);
        cbtn2.setStyle("-fx-font-size: 15; "); 
        root3.getChildren().add(cbtn2);
        cbtn3.setLayoutY(625);
        cbtn3.setLayoutX(175);
        cbtn3.setPrefSize(150, 50);
        cbtn3.setStyle("-fx-font-size: 15; ");
        root3.getChildren().add(cbtn3);
        cbtn4.setLayoutY(210);
        cbtn4.setLayoutX(600);
        cbtn4.setPrefSize(150, 50);
        cbtn4.setStyle("-fx-font-size: 15; ");
        root3.getChildren().add(cbtn4);
        cbtn5.setLayoutY(110);
        cbtn5.setLayoutX(600);
        cbtn5.setPrefSize(150, 50);
        cbtn5.setStyle("-fx-font-size: 15; ");
        root3.getChildren().add(cbtn5);
        cbtn6.setLayoutY(310);
        cbtn6.setLayoutX(500);
        cbtn6.setPrefSize(150, 50);
        cbtn6.setStyle("-fx-font-size: 15; ");
        root3.getChildren().add(cbtn6);
        cbtn7.setLayoutY(310);
        cbtn7.setLayoutX(680);
        cbtn7.setPrefSize(150, 50);
        cbtn7.setStyle("-fx-font-size: 15; ");
        root3.getChildren().add(cbtn7);
        
        
        //Text Fields
        ctext1.setLayoutY(20);
        ctext1.setLayoutX(290);
        root3.getChildren().add(ctext1);
        ctext2.setLayoutY(575);
        ctext2.setLayoutX(80);
        root3.getChildren().add(ctext2);
        ctext3.setLayoutY(75);
        ctext3.setLayoutX(50);
        root3.getChildren().add(ctext3);
        ctext4.setLayoutY(175);
        ctext4.setLayoutX(50);
        root3.getChildren().add(ctext4);
        ctext5.setLayoutY(275);
        ctext5.setLayoutX(50);
        root3.getChildren().add(ctext5);
        ctext6.setLayoutY(310);
        ctext6.setLayoutX(80);
        root3.getChildren().add(ctext6);
        ctext7.setLayoutY(280);
        ctext7.setLayoutX(500);
        root3.getChildren().add(ctext7);
        ctext8.setLayoutY(485);
        ctext8.setLayoutX(325);
        root3.getChildren().add(ctext8);
        ctext9.setLayoutY(365);
        ctext9.setLayoutX(80);
        root3.getChildren().add(ctext9);
        ctext10.setLayoutY(485);
        ctext10.setLayoutX(80);
        root3.getChildren().add(ctext10);
        ctext11.setLayoutY(485);
        ctext11.setLayoutX(570);
        root3.getChildren().add(ctext11);
        
       
        //Choice Boxes
        ccbox1.setLayoutY(125);
        ccbox1.setLayoutX(100);
        ccbox1.setPrefSize(400, 20);
        root3.getChildren().add(ccbox1);
        ccbox2.setLayoutY(515);
        ccbox2.setLayoutX(100);
        ccbox2.setPrefSize(200, 20);
        root3.getChildren().add(ccbox2);
        ccbox4.setLayoutY(225);
        ccbox4.setLayoutX(100);
        ccbox4.setPrefSize(400, 20);
        root3.getChildren().add(ccbox4);
        ccbox5.setLayoutY(575);
        ccbox5.setLayoutX(155);
        ccbox5.setPrefSize(300, 20);
        root3.getChildren().add(ccbox5);
        ccbox6.setLayoutY(515);
        ccbox6.setLayoutX(345);
        ccbox6.setPrefSize(200, 20);
        root3.getChildren().add(ccbox6);
        ccbox7.setLayoutY(515);
        ccbox7.setLayoutX(590);
        ccbox7.setPrefSize(200, 20);
        root3.getChildren().add(ccbox7);
        
        
        //Text Boxes
        ctf1.setLayoutY(310);
        ctf1.setLayoutX(230);
        ctf1.setPrefSize(225, 20);
        root3.getChildren().add(ctf1);
        ctf2.setLayoutY(400);
        ctf2.setLayoutX(100);
        ctf2.setPrefSize(700, 75);
        root3.getChildren().add(ctf2);
        
        
        Scene scene3 = new Scene(root3, 900, 700);
        
       
        //-----------------END OF DEFECT CONSOLE-----------------------------------------
        
        
        //Action Handling 
      
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               
               primaryStage.setScene(scene2);
            }
        });
        
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               
               primaryStage.setScene(scene3);
            }
        });
        
        bbtn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	primaryStage.setScene(scene1);
            }
        });
        
        cbtn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	primaryStage.setScene(scene1);
            }
        });
       
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
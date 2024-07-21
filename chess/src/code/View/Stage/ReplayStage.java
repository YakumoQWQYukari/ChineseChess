package code.View.Stage;

import code.board.Chessboard;
import code.Operation.Process;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ReplayStage {
    private ArrayList<int[][]> list ;
    private int index = 0;

    public Stage createStage(int a){
        //转载需要读取的数组
        Process.processList.clear();
        Process process = new Process(Process.process);
        process.readOut();
        if(Process.processList.size() > 0){
            list = Process.processList.get(a);
        }

        Stage stage = new Stage();
        //总面板
        AnchorPane anchorPane = new AnchorPane();

        //中-下棋面板
        Chessboard chessboard = new Chessboard(Chessboard.board);
        Chessboard.pane.setLayoutX(25);
        Chessboard.pane.setLayoutY(25);


        //下-功能区
        HBox hBox = new HBox(15);
        Button bt1 = new Button("上一步");
        bt1.setPrefWidth(75);
        bt1.setPrefHeight(40);
        bt1.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        bt1.setOnAction(e1 -> {
            index--;
            if(index < 0){
                index = 0;
            }
            if(index >= 0 && index <= list.size()-1){
                Chessboard c1 = new Chessboard();
                c1.reload(list.get(index),1);
            }

        });
        Button bt2 = new Button("下一步");
        bt2.setPrefWidth(75);
        bt2.setPrefHeight(40);
        bt2.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        bt2.setOnAction(e2 -> {
            index++;
            if(index >  list.size()-1){
                index = list.size()-1;
            }
            if(index >= 0 && index <= list.size()-1){
                Chessboard c1 = new Chessboard();
                c1.reload(list.get(index),1);
            }
        });
        hBox.getChildren().addAll(bt1,bt2);
        hBox.setLayoutY(800);
        hBox.setLayoutX(275);

        //设置总面板
        anchorPane.getChildren().addAll(hBox,Chessboard.pane);
        anchorPane.setPrefHeight(850);
        anchorPane.setPrefWidth(700);

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);

        return stage;
    }


}

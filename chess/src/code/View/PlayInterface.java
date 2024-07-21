package code.View;

import code.Entrance;
import code.board.Chessboard;
import code.chessman.Chessman_jiang;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;

//游玩界面
public class PlayInterface extends Application {
    @Override
    public void start(Stage playStage) throws Exception {
        //主体面板构成（背景、功能按钮、棋盘面板）
        AnchorPane anchorPane = new AnchorPane();

        //背景
//        ImageView imageView = new ImageView(new Image(new File("src\\resources\\image\\background1.jpg").toURI().toString()));
//        ImageView imageView = new ImageView(new Image(new File(Entrance.class.getResource("/resources/image/background1.jpg").toString()).toString()));
        ImageView imageView = new ImageView(new Image(new File(Class.class.getResource("/resources/image/background1.jpg").toString()).toString()));
        imageView.setFitWidth(900);
        imageView.setFitHeight(850);

        //棋盘面板设计
        Chessboard board = new Chessboard(Chessboard.board);
        Chessboard.pane.setLayoutX(125);
        Chessboard.pane.setLayoutY(25);

        //功能按钮设计
        VBox vBox = new VBox();
        //退出按钮
        Button bt = new Button("退出");
        bt.setPrefWidth(100);
        bt.setPrefHeight(40);
        bt.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        bt.setOnAction(event -> {
            MenuInterface menuInterface = new MenuInterface();
            try {
                menuInterface.start(playStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button b1 = new Button("重新开始");
        b1.setPrefWidth(100);
        b1.setPrefHeight(40);
        b1.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        b1.setOnAction(e1 -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("是否要重新开始");
            Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            ok.setOnAction(event -> {
                Chessboard chessboard = new Chessboard(Chessboard.board);
            });
            alert.showAndWait();

        });

//      悔棋
        Button b2 = new Button("悔棋");
        b2.setPrefWidth(100);
        b2.setPrefHeight(40);
        b2.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        b2.setOnAction(e2 -> {
            Chessboard chessboard = new Chessboard();
            if(Chessboard.process.size() >= 2){
                chessboard.reload(Chessboard.process.get(Chessboard.process.size()-1-1),2);
            }
        });

        //和棋
        Button b3 = new Button("和棋");
        b3.setPrefWidth(100);
        b3.setPrefHeight(40);
        b3.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        b3.setOnAction(e3 -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("是否要和棋");
            Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            ok.setOnAction(event -> {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setContentText("是否要保存记录");
                Button ok1 = (Button) alert1.getDialogPane().lookupButton(ButtonType.OK);
                ok1.setOnAction(event1 -> {
                    Chessman_jiang.record("和棋");
                });
                Button cancel = (Button) alert1.getDialogPane().lookupButton(ButtonType.CANCEL);
                //取消了就还原棋盘
                cancel.setOnAction(event2 -> {
                    Chessboard chessboard = new Chessboard(Chessboard.board);
                });
                alert1.show();

            });
            Button cancel = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
            //取消了就还原棋盘
            cancel.setOnAction(event2 -> {
                Chessboard chessboard = new Chessboard(Chessboard.board);
            });
            alert.showAndWait();
        });
        vBox.getChildren().addAll(bt,b1,b2,b3);
        vBox.setSpacing(20);
        vBox.setLayoutX(10);
        vBox.setLayoutY(25);

        //设置主面板信息
        anchorPane.getChildren().addAll(imageView,Chessboard.pane,vBox);

        Scene scene = new Scene(anchorPane);
        playStage.setScene(scene);
        playStage.show();
    }
}

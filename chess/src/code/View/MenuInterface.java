package code.View;

import code.Entrance;
import code.View.Stage.RuleStage;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;

public class MenuInterface extends Application {
    @Override
    public void start(Stage menuStage) throws Exception {
        //登录界面的面板类型
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBlendMode(BlendMode.SRC_OVER);

        //界面元素设置
        //背景图片
//        Image image1 = new Image(new File("src\\resources\\image\\background.jpg").toURI().toString());//绝对路径
        Image image1 = new Image(new File(this.getClass().getResource("/resources/image/background.jpg").toString()).toString());//以类为基准的相对路径
//        jar:file:/D:/%e7%bc%96%e7%a8%8b/idaePro/chess/out/artifacts/chess_jar/chess.jar!/resources/image/background.jpg//错误原因

        ImageView imageView1 = new ImageView(image1);
        //标题图片
//        Image image2 = new Image(new File("src\\resources\\image\\title.png").toURI().toString());
        Image image2 = new Image(new File(this.getClass().getResource("/resources/image/title.png").toString()).toString());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(646.6);
        imageView2.setFitHeight(229.5);
        imageView2.setX(65);
        imageView2.setY(100);

        //开始游戏按钮设置
        Button b1 = new Button("开始游戏");
        b1.setLayoutX(220);
        b1.setLayoutY(450);
        b1.setPrefWidth(350);
        b1.setPrefHeight(80);
        Font font2 = Font.font("SimHei",35);
        b1.setFont(font2);
        b1.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887 ");
        b1.setCursor(Cursor.HAND);
        b1.setOnAction(e -> {
            PlayInterface playInterface = new PlayInterface();
            try {
                playInterface.start(menuStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //历史记录按钮设置
        Button b2 = new Button("历史记录");
        b2.setLayoutX(220);
        b2.setLayoutY(600);
        b2.setPrefWidth(350);
        b2.setPrefHeight(80);
        Font font3 = Font.font("SimHei",35);
        b2.setFont(font3);
        b2.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        b2.setCursor(Cursor.HAND);
        b2.requestFocus();
        b2.setOnAction(e -> {
            RecordingInterface recordingInterface = new RecordingInterface();
            try {
                recordingInterface.start(menuStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //游戏规则
        Button button = new Button("游戏规则");
        button.setLayoutX(320);
        button.setLayoutY(715);
        button.setPrefWidth(150);
        button.setPrefHeight(70);
        Font font4 = Font.font("SimHei",25);
        button.setFont(font4);
        button.setStyle("-fx-background-radius: 50; -fx-background-color: #48d1cc");
        button.setOnAction(event -> {
            RuleStage ruleStage = new RuleStage();
            ruleStage.createStage().show();
        });

        //主要面板的设置
        anchorPane.getChildren().addAll(imageView1,imageView2,b1, b2,button);

        //舞台设置
        Scene scene = new Scene(anchorPane);
        menuStage.setScene(scene);
        menuStage.setTitle("中国象棋");
        menuStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

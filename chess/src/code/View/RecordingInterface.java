package code.View;

import code.Operation.Record;
import code.View.Stage.ReplayStage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RecordingInterface extends Application {
    public static int selected = 0;
    public static Object selectedItem = null;
    @Override
    public void start(Stage recordingStage) throws Exception {
        //listView的信息列表
        ObservableList<Label> list = FXCollections.observableArrayList();
        //主界面（上-工具栏 下-历史记录列表）
        AnchorPane anchorPane = new AnchorPane();
        //下 历史记录列表
        ListView<Label> listView = new ListView();
        listView.setLayoutX(10);
        listView.setLayoutY(60);
        listView.setPrefSize(360,400);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {
            @Override
            public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
                Record.historyList.clear();
                Record record = new Record(Record.history);
                record.readOut();
                //获取选择的对象
                selectedItem = newValue;
                if(selectedItem != null){
                    selected = Record.historyList.indexOf(newValue.getText());
                }
            }
        });

        //上 工具栏
        HBox hBox = new HBox(15);
        Button bt1 = new Button("查询");
        bt1.setPrefWidth(75);
        bt1.setPrefHeight(40);
        bt1.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        bt1.setOnAction(e1 -> {
            //显示对局信息
            list.clear();
            Record.historyList.clear();
            Record record = new Record(Record.history);
            record.readOut();
            for(String s:Record.historyList){
                list.add(new Label(s));
            }
            listView.setItems(list);
        });
        //显示历史记录
        Button bt2 = new Button("查看");
        bt2.setPrefWidth(75);
        bt2.setPrefHeight(40);
        bt2.setStyle("-fx-background-radius: 50; -fx-background-color: #deb887");
        bt2.setOnAction(e2 -> {
            if(selectedItem != null){
                ReplayStage replayStage = new ReplayStage();
                replayStage.createStage(selected).show();
            }
        });
        hBox.getChildren().addAll(bt1,bt2);
        hBox.setLayoutY(10);
        hBox.setLayoutX(10);

        //退出设置
        Button bt = new Button("退出");
        bt.setPrefWidth(75);
        bt.setPrefHeight(40);
        bt.setStyle("-fx-background-radius: 50; -fx-background-color: #87cefa");
        bt.setLayoutX(275);
        bt.setLayoutY(10);
        bt.setOnAction(event -> {
            MenuInterface menuInterface = new MenuInterface();
            try {
                menuInterface.start(recordingStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //主面板设置
        anchorPane.getChildren().addAll(hBox,listView,bt);
        anchorPane.setPrefWidth(400);
        anchorPane.setPrefHeight(400);

        Scene scene = new Scene(anchorPane);
        recordingStage.setScene(scene);
        recordingStage.setTitle("历史记录");
        recordingStage.show();
    }
}

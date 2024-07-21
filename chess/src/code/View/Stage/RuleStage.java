package code.View.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RuleStage {

    public Stage createStage(){
        Stage stage = new Stage();
        String s =
                "中国象棋棋子行走规则：\n" +
                "\n" +
                "帅（将）：帅和将是棋中的首脑，是双方竭力争夺的目标。它只能在\"九宫格\"之内活动，\n" +
                "可上可下，可左可右，每次走动只能按竖线或横线走动一格。\n"+
                "仕（士）：红方为“士”，黑方为“仕”。它也只能在九宫内走动，行棋路径只能是九宫内的斜线，一次只能走一个斜格。\n" +
                "\n" +
                "相（象）：红方为“相”，黑方为“象”。它的走法是每次循对角线走两格，俗称“象飞田”。\n" +
                "相（象）的活动范围限于“河界”以内的本方阵地，\n"+
                "不能过河，且如果它走的方向被阻挡（“田”字中央有一个棋子），就不能走，俗称“塞象眼”。\n" +
                "\n" +
                "车：在其行动道路上，即水平线或垂直线上，可前后左右移动，步数不限，无阻隔物即可直达目的地。\n" +
                "\n" +
                "马：行动方式是\"日\"字形，即先横走或竖走一格，再斜走一格，但是如果它走的方向被阻挡（“蹩腿”），就不能走。\n" +
                "\n" +
                "炮：平时走直线，不论纵横，步数不限，无阻隔物即可到达目的地。捉对方棋子时，必须隔一个棋子，炮就能炮打对方棋子。\n" +
                "\n" +
                "兵（卒）：未过河时，只能向前走一步，过河后，可以左右移动，但每次只能走一格，不能后退";

        Text text = new Text(s);
        text.setLayoutX(50);
        text.setLayoutY(50);
        Font font = Font.font("SimHei",25);
        text.setFont(font);
        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.setPrefWidth(700);
//        anchorPane.setPrefHeight(400);

        anchorPane.getChildren().add(text);
        Scene scene = new Scene(anchorPane);
        stage.setTitle("游戏规则");
        stage.setScene(scene);
        return stage;
    }
}

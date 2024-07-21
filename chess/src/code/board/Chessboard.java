package code.board;

import code.Entrance;
import code.chessman.Chessman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class Chessboard{
    public static Pane pane = new Pane();//棋盘、棋子、标识点
    public static Point[][] points = new Point[10][9];//标识点-棋子可以走的点--棋子的移动依靠标识点
    public static Chessman[][] chessBoard = new Chessman[10][9];//装填棋子
    public static int[][] board = new int[][]{
            {17,18,19,20,21,22,23,24,25},
            {0,0,0,0,0,0,0,0,0},
            {0,26,0,0,0,0,0,27,0},
            {28,0,29,0,30,0,31,0,32},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {16,0,15,0,14,0,13,0,12},
            {0,11,0,0,0,0,0,10,0},
            {0,0,0,0,0,0,0,0,0},
            {9,8,7,6,5,4,3,2,1}
    };//棋子虚拟对应
    public static ArrayList<int[][]> process = new ArrayList<>();//储存整个比赛过程
    private ImageView imageView = new ImageView(new Image(new File("src\\resources\\image\\board.jpg").toURI().toString()));
//    private ImageView imageView = new ImageView(new Image(new File(Entrance.class.getResource("/resources/image/board.jpg").toString()).toString()));


    public Chessboard(int[][] board) {
        //设置棋盘
        imageView.setFitWidth(675);
        imageView.setFitHeight(750);
        pane.getChildren().add(imageView);
        //装填棋子,设置可移动点
        loadChessman(board);
    }
    public Chessboard(){}

    //装填移动点
    public void loadPoints(){
        for(int i = 0;i<10;i++){
            for(int j = 0;j < 9;j++){
                if(i == 0 && j == 0){
                    Point point = new Point(25,25,15,j+1,i+1);
                    point.setFill(Color.YELLOW);
                    point.setOpacity(0);
                    point.setOnMouseClicked(e -> {
                    });
                    points[i][j] = point;
                    pane.getChildren().add(point);
                }
                Point point = new Point(points[0][0].getCenterX() + 78.75* j,points[0][0].getCenterY() + 78.75*i,15,j+1,i+1);
                point.setFill(Color.YELLOW);
                point.setOpacity(0);
                point.setOnMouseClicked(e -> {
                });
                points[i][j] = point;
                pane.getChildren().add(point);

            }
        }
    }

    //把棋子加到面板上(一般从第一步开始)
    public void loadChessman(int[][] a){
        //先将棋子清空
        for(int i = 9;i >= 0;i--){
            for(int j = 8;j >= 0;j--){
                chessBoard[i][j] = null;
            }
        }
        pane.getChildren().clear();
//      初始化
        process.clear();
        Chessman.flag_round = 0;
        Chessman.mutex = null;

        //根据输入数组将装填棋子
        imageView.setFitWidth(675);
        imageView.setFitHeight(750);
        pane.getChildren().add(imageView);
        loadPoints();
        for(int i = 9;i >= 0;i--){
            for(int j = 8;j >= 0;j--){
                Chessman.setChessman(points[i][j],a[i][j]);
            }
        }

        //最后把棋子放在棋盘上
        for(int i = 9;i >= 0;i--){
            for(int j = 8;j >= 0;j--){
                if (chessBoard[i][j] != null){
                    pane.getChildren().add(chessBoard[i][j]);

                }
            }
        }
        //将初始状态的棋盘数组加入列表
        int[][] tmp = new int[10][9];
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 9;j++){
                tmp[i][j] = board[i][j];
            }
        }
        process.add(a);
    }

    //清空路径，从中间开始重新装填
    //关键算法 -- ReplayStage左右切换棋局 -- PlayInterface悔棋
    public void reload(int[][] a,int mode){
        //mode 1 是正常加载(数组不会变化)，mode 2 是回溯
        //先将棋子清空,初始化
        for(int i = 9;i >= 0;i--){
            for(int j = 8;j >= 0;j--){
                chessBoard[i][j] = null;
            }
        }
        //中间初始
        if(mode == 1){
            Chessman.mutex = null;
            Chessman.flag_round = 2;
        }
        if(mode == 2){
            //是奇数(红方回合)
            if((Chessboard.process.size()-1) % 2 == 1){
                Chessman.mutex = null;
                Chessman.flag_round = 0;
            }
            //是偶数(黑方回合)
            if((Chessboard.process.size()-1) % 2 == 0){
                Chessman.mutex = null;
                Chessman.flag_round = 1;
            }
        }
        pane.getChildren().clear();
        //根据输入数组将装填棋子
        imageView.setFitWidth(675);
        imageView.setFitHeight(750);
        pane.getChildren().add(imageView);
        loadPoints();
        for(int i = 9;i >= 0;i--){
            for(int j = 8;j >= 0;j--){
                Chessman.setChessman(points[i][j],a[i][j]);
            }
        }

        //最后把棋子放在棋盘上
        for(int i = 9;i >= 0;i--){
            for(int j = 8;j >= 0;j--){
                if (chessBoard[i][j] != null){
                    pane.getChildren().add(chessBoard[i][j]);

                }
            }
        }

        if(mode == 2){
            Chessboard.process.remove(Chessboard.process.get(Chessboard.process.size()-1));
        }
    }

}

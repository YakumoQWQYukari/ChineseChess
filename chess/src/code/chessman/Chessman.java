package code.chessman;

import code.board.Chessboard;
import code.board.Point;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Stack;

public class Chessman extends Group {
    //静态变量
    public static Chessman mutex = null;//棋子之间的互斥量(获取点前选中的棋子)
    public static int flag_round = 0;//红、黑控制器（true 0-红，false 1-黑）

    private int id;
    private Point point;
    private Stack<Point> paths = new Stack<>();//存储象棋走过的路径
    private ArrayList<Point> movement = new ArrayList<>();//存储可走点位
    private ArrayList<Chessman> edibleChess = new ArrayList<>();//存储可以吃的棋子
    private int flag = 0;//控制器--棋子是否被选中（0未选中，1选中）
    //棋子图像
    private Circle circle = new Circle();
    private ImageView imageView;

    //设置棋子
    public static void setChessman(Point point,int id){
        if(id != 0){
            if(id == 1 || id == 9 || id == 17 || id == 25){
                //车！！！！！！
                Chessman_ju chessman_ju = new Chessman_ju(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_ju;
            }else if(id == 2 || id == 8 || id == 18 || id == 24){
                //马！！！！！
                Chessman_ma chessman_ma = new Chessman_ma(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_ma;
            }else if(id == 3 || id == 7 || id == 19 || id == 23){
                //象！！！！
                Chessman_xiang chessman_xiang = new Chessman_xiang(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_xiang;
            }else if(id == 4 || id == 6 || id == 20 || id == 22){
                //士！！！！
                Chessman_shi chessman_shi = new Chessman_shi(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_shi;
            }else if(id == 5 || id == 21){
                //帅!!!
                Chessman_jiang chessman_jiang = new Chessman_jiang(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_jiang;
            }else if(id == 10 || id == 11 || id == 26 || id == 27){
                //炮！！
                Chessman_pao chessman_pao = new Chessman_pao(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_pao;
            }else {
                Chessman_bing chessman_bing = new Chessman_bing(point,id);
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_bing;
            }
        }
    }

    public void ini(){}

    public void eat(){}

    public void dispose(){}

    public int getNum() {
        return id;
    }

    public Stack<Point> getPaths() {
        return paths;
    }

    public ArrayList<Point> getMovement() {
        return movement;
    }

    public Point getPoint() {
        return point;
    }

    public int getFlag() {
        return flag;
    }

    public ArrayList<Chessman> getEdibleChess() {
        return edibleChess;
    }

    public Circle getCircle() {
        return circle;
    }

}

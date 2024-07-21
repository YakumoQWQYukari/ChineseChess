package code.chessman;

import code.Entrance;
import code.board.Chessboard;
import code.board.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class Chessman_ma extends Chessman {
    private int id;
    private Point point;
    private Stack<Point> paths = new Stack<>();//存储象棋走过的路径
    private ArrayList<Point> movement = new ArrayList<>();//存储可走点位
    private ArrayList<Chessman> edibleChess = new ArrayList<>();//存储可以吃的棋子
    private int flag = 0;//控制器--棋子是否被选中（0未选中，1选中）
    private Chessman_ma chessman_ma = null;//是否被选中--处理器
    //棋子图像
    private Circle circle = new Circle();
    private ImageView imageView1 = new ImageView(new Image(new File("src\\resources\\image\\RM.png").toURI().toString()));
    private ImageView imageView2 = new ImageView(new Image(new File("src\\resources\\image\\BM.png").toURI().toString()));
//    private ImageView imageView1 = new ImageView(new Image(new File(Entrance.class.getResource("/resources/image/RM.png").toString()).toString()));
//    private ImageView imageView2 = new ImageView(new Image(new File(Entrance.class.getResource("/resources/image/BM.png").toString()).toString()));

    public Chessman_ma(Point point,int id) {
        //初始化对象
        this.point = point;
        this.id = id;
        paths.push(new Point(point.getCenterX(),point.getCenterY(),point.getRadius(),point.getX(),point.getY()));
        //设置图片信息
        circle.setCenterX(point.getCenterX());
        circle.setCenterY(point.getCenterY());
        imageView1.setFitWidth(70);
        imageView1.setFitHeight(70);
        imageView1.xProperty().bind(circle.centerXProperty().subtract(imageView1.getFitWidth()/2));
        imageView1.yProperty().bind(circle.centerYProperty().subtract(imageView1.getFitHeight()/2));
        imageView2.setFitWidth(70);
        imageView2.setFitHeight(70);
        imageView2.xProperty().bind(circle.centerXProperty());
        imageView2.yProperty().bind(circle.centerYProperty());
        imageView2.xProperty().bind(circle.centerXProperty().subtract(imageView1.getFitWidth()/2));
        imageView2.yProperty().bind(circle.centerYProperty().subtract(imageView1.getFitHeight()/2));
        if(id <= 16){
            circle.setStyle("-fx-fill: white;-fx-stroke: black;-fx-stroke-width: 5");
            getChildren().addAll(circle,imageView1);
        }else {
            circle.setStyle("-fx-fill: white;-fx-stroke: black;-fx-stroke-width: 5");
            getChildren().addAll(circle,imageView2);
        }
        circle.setRadius(35);
        //点击对象切换
        setOnMouseClicked(e -> {
            MouseButton bt = e.getButton();
            if (bt == MouseButton.PRIMARY) {
                //棋子自身的互动
                if (flag == 1) {
                    ini();
                } else {
                    //控制回合
                    if(Chessman.mutex == null && Chessman.flag_round == 0 && id <= 16){
                        Chessman.mutex = this;
                        flag = 1;
                        chessman_ma = this;//获取自身
                        circle.setStroke(Color.RED);
                        removablePoints(paths.peek());
                    }else if(Chessman.mutex == null && Chessman.flag_round == 1 && id >= 17){
                        Chessman.mutex = this;
                        flag = 1;
                        chessman_ma = this;//获取自身
                        circle.setStroke(Color.RED);
                        removablePoints(paths.peek());
                    }
                }
                //棋子与棋子之间的互动(一定要放在棋子自身互动之后否则会再次运行棋子互动后的动作)
                if(Chessman.mutex != null && Chessman.mutex != this && Chessman.mutex.getEdibleChess().size() != 0){
                    for(Chessman c:Chessman.mutex.getEdibleChess()){
                        if(c.getNum() == id){
                            if(Chessman.mutex.getNum() <= 16 && id >= 17){
                                eat();
                                break;
                            }else if(Chessman.mutex.getNum() >= 17 && id <= 16){
                                eat();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    public Chessman_ma() {
    }

    //计算出可以走的点位(ps:设置相同的事件会重置)
    public void removablePoints(Point p){
        int[] range = new int[]{2};
        double[] k = new double[]{-0.5,0.5,-2,2};
        double b = 0;
        double y = 0;
        double x = 0;
        for(int i = 0;i < k.length-2;i++){
            b = p.getY() - k[i] * p.getX();
            x = range[0] + p.getX();
            y = k[i] * x + b;
            if(x>= 1 && x<=9 && y>=1 && y<=10){
                if(Chessboard.chessBoard[(int)y-1][(int)x-1] != null){
                    if(id <= 16 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() >= 17){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }else if(id >= 17 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() <= 16){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }
                }
                if(Chessboard.chessBoard[paths.peek().getY()-1][paths.peek().getX()-1+1] == null){
                    dispose(x,y);
                }
            }
            x = p.getX() - range[0];
            y = k[i] * x + b ;
            if(x>= 1 && x<=9 && y>=1 && y<=10){
                if(Chessboard.chessBoard[(int)y-1][(int)x-1] != null){
                    if(id <= 16 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() >= 17){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }else if(id >= 17 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() <= 16){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }
                }
                if(Chessboard.chessBoard[paths.peek().getY()-1][paths.peek().getX()-1-1] == null){
                    dispose(x,y);
                }
            }
        }

        for(int i = 2;i < k.length;i++){
            b = p.getY() - k[i] * p.getX();
            y = range[0] + p.getY();
            x = -b/k[i] + y/k[i];
            if(x>= 1 && x<=9 && y>=1 && y<=10){
                if(Chessboard.chessBoard[(int)y-1][(int)x-1] != null){
                    if(id <= 16 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() >= 17){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }else if(id >= 17 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() <= 16){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }
                }
                if(Chessboard.chessBoard[paths.peek().getY()-1+1][paths.peek().getX()-1] == null){
                    dispose(x,y);
                }
            }
            y = p.getY() - range[0];
            x = -b/k[i] +  y/k[i];
            if(x>= 1 && x<=9 && y>=1 && y<=10){
                if(Chessboard.chessBoard[(int)y-1][(int)x-1] != null){
                    if(id <= 16 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() >= 17){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }else if(id >= 17 && Chessboard.chessBoard[(int)y-1][(int)x-1].getNum() <= 16){
                        edibleChess.add(Chessboard.chessBoard[(int)y-1][(int)x-1]);
                    }
                }
                if(Chessboard.chessBoard[paths.peek().getY()-1-1][paths.peek().getX()-1] == null){
                    dispose(x,y);
                }
            }
        }
    }

    //负则处理标识点
    public void dispose(double x,double y){
        Point point = Chessboard.points[(int)y-1][(int)x-1];
        point.setFill(Color.RED);
        point.setOpacity(1);
        point.setOnMouseClicked(e -> {
            if(chessman_ma != null){
                //把当前对象移动到对应点位
                chessman_ma.circle.setCenterX((int)point.getCenterX());
                chessman_ma.circle.setCenterY((int)point.getCenterY());
                Chessboard.chessBoard[point.getY()-1][point.getX()-1] = chessman_ma;
                Chessboard.chessBoard[paths.peek().getY()-1][paths.peek().getX()-1] = null;
                //加入踪迹
                int[][] tmp = new int[10][9];
                for(int i = 0;i < 10;i++){
                    for(int j = 0;j < 9;j++){
                        if(Chessboard.chessBoard[i][j] == null){
                            tmp[i][j] = 0;
                        }else {
                            tmp[i][j] = Chessboard.chessBoard[i][j].getNum();
                        }

                    }
                }
                Chessboard.process.add(tmp);
                //选择到这个点位就把这个作为路径
                paths.push(new Point(point.getCenterX(),point.getCenterY(),point.getRadius(),point.getX(),point.getY()));
                //回合结束
                if(chessman_ma.getNum() <= 16){
                    Chessman.flag_round = 1;
                }else{
                    Chessman.flag_round = 0;
                }
                ini();
            }
        });
        movement.add(point);
    }

    public void ini(){
        //将变量初始化（不在选中--控制器、处理器,互斥量，可移动点）
        flag = 0;
        chessman_ma = null;
        for(Point pt:movement){
            pt.setFill(Color.YELLOW);
            pt.setOpacity(0);
            pt.setOnMouseClicked(event -> {});
        }
        movement.clear();
        edibleChess.clear();
        Chessman.mutex = null;
        circle.setStroke(Color.BLACK);
    }

    //吃
    public void eat(){
        //到达新点位
        Chessman.mutex.getCircle().setCenterX(paths.peek().getCenterX());
        Chessman.mutex.getCircle().setCenterY(paths.peek().getCenterY());
        //虚拟点位的改变
        Chessboard.chessBoard[Chessman.mutex.getPaths().peek().getY()-1][Chessman.mutex.getPaths().peek().getX()-1] = null;
        Chessman.mutex.getPaths().push(new Point(paths.peek().getCenterX(),paths.peek().getCenterY(),paths.peek().getRadius(),paths.peek().getX(),paths.peek().getY()));
        Chessboard.chessBoard[Chessman.mutex.getPaths().peek().getY()-1][Chessman.mutex.getPaths().peek().getX()-1] =  Chessman.mutex;
        //加入踪迹
        int[][] tmp = new int[10][9];
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 9;j++){
                if(Chessboard.chessBoard[i][j] == null){
                    tmp[i][j] = 0;
                }else {
                    tmp[i][j] = Chessboard.chessBoard[i][j].getNum();
                }

            }
        }
        Chessboard.process.add(tmp);
        //移除
        Chessboard.pane.getChildren().remove(this);
        //回合结束
        if( Chessman.mutex.getNum() <= 16){
            Chessman.flag_round = 1;
        }else{
            Chessman.flag_round = 0;
        }
        Chessman.mutex.ini();
    }


    public int getNum() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public Stack<Point> getPaths() {
        return paths;
    }

    @Override
    public ArrayList<Point> getMovement() {
        return movement;
    }

    @Override
    public ArrayList<Chessman> getEdibleChess() {
        return edibleChess;
    }

    public int getFlag() {
        return flag;
    }

    public Chessman getChessman() {
        return chessman_ma;
    }

    @Override
    public Circle getCircle() {
        return circle;
    }


}

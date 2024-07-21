package code;

import code.View.MenuInterface;

//程序入口点
public class Entrance {
    public static void main(String[] args) {
//        System.out.println(Entrance.class.getResource("/resources/image/background.jpg").getFile());
//        System.out.println(Entrance.class.getClassLoader().getResource("/resources/image/background.jpg").toString());
        MenuInterface.main(args);
    }
}

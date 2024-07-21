package code.Operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Process {
    public static ArrayList<ArrayList<int[][]>>processList = new ArrayList<>();
    public static final File process = new File("process.txt");
    //成员变量
    private File file;
    public Process(File file){
        this.file = file;
    }

    //写入
    public void writeIn() {
        try {
            try(PrintWriter output = new PrintWriter(new FileOutputStream(file,true))){
                for(ArrayList<int[][]> list:processList ){
                    output.print(list.size() + " ");
                    for(int[][] a:list){
                        for(int i = 0;i < 10;i++){
                            for(int j = 0;j < 9;j++){
                                output.print(a[i][j] + " ");
                            }
                        }
                    }

                }
                output.println();
            }
        }catch(Exception ex){
            System.out.println("wrong");
        }
    }

    //覆写
    public void overWrite() {
        try {
            try(PrintWriter output = new PrintWriter(new FileOutputStream(file))){
                if(!(processList.size() == 0)){
                    for(ArrayList<int[][]> list:processList ){
                        output.println(list.size() + " ");
                        for(int[][] a:list){
                            for(int i = 0;i < 10;i++){
                                for(int j = 0;j < 9;j++){
                                    output.print(a[i][j] + " ");
                                }
                            }
                        }

                    }
                    output.println();
                }else{
                    output.print("");
                }
                processList.clear();
            }
        }catch(Exception ex){
            System.out.println("wrong");
        }
    }

    //读取
    public void readOut() {
        try {
            try(Scanner input = new Scanner(file)){
                while(input.hasNext()) {
                    ArrayList<int[][]> ls = new ArrayList<>();
                    int time = Integer.valueOf(input.next());
                    for(int x = 0;x < time;x++){
                        int[][] a = new int[10][9];
                        for(int i = 0;i < 10;i++){
                            for(int j = 0;j < 9;j++){
                                a[i][j] = Integer.valueOf(input.next()) ;
                            }
                        }
                        ls.add(a);
                    }
                    processList.add(ls);
                }
            }
        }catch(Exception ex){
            System.out.println();
        }
    }

}

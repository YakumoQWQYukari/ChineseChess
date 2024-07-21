package code.Operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Record {
    public static ArrayList<String> historyList = new ArrayList<>();
    public static final File history = new File("history.txt");
    //成员变量
    private File file;
    public Record(File file){
        this.file = file;
    }

    //写入
    public void writeIn() {
        try {
            try(PrintWriter output = new PrintWriter(new FileOutputStream(file,true))){
                for(String history: historyList){
                    output.println(history);
                }
            }
        }catch(Exception ex){
            System.out.println("wrong");
        }
    }

    //覆写
    public void overWrite() {
        try {
            try(PrintWriter output = new PrintWriter(new FileOutputStream(file))){
                if(!(historyList.size() == 0)){
                    for(String history: historyList){
                        output.println(history);
                    }
                }else{
                    output.print("");
                }
                historyList.clear();
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
                    String s1 = input.nextLine();
                    historyList.add(s1);
                }
            }
        }catch(Exception ex){
            System.out.println();
        }
    }

}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Shapes extends JComponent  {
    static final int MAX_HEIGHT = 1000;
    static final int MAX_WIDTH = 1000;

    public void paintComponent(Graphics g) {

        //set random color
        Random rainbow = new Random();
        int red = rainbow.nextInt(255);
        int blue = rainbow.nextInt(255);
        int green = rainbow.nextInt(255);
       // int sideno = rainbow.nextInt(7)+3;
        g.setColor(new Color(red, blue, green));

        /*
        double theta = 2 * Math.PI/sideno;
        int[] arrx = new int[sideno];
        int[] arry = new int[sideno];
        for(int i =0;i<sideno;i++){
            arrx[i] = getWidth()/2 +(int)(Math.cos(theta * i)*50);
            arry[i] = getHeight()/2 + (int)(Math.sin(theta * i)*50);
            */
        }
        //g.drawPolygon(arrx,arry,sideno);
    }


class createShapes implements Runnable {
    ArrayList<Object> shapes = new ArrayList<>();

        public void run(){
            int count = 1;
            for (int i = 0; i < count; i++) {
                shapes.add(new Polygon());
                shapes.add(new Oval());
                count = +count;
            }


        try {
            Thread.sleep(300);
        }
        catch(InterruptedException e){
            e.printStackTrace();

        }
    }}


abstract class holdShapes{
    protected int width;
    protected int height;
    protected int rad;
    protected Random rand = new Random();
}

class Oval extends holdShapes {
    Oval() {
        width = rand.nextInt(Shapes.MAX_WIDTH);
        rad = width / 2;
        }
    }
   class Polygon extends holdShapes{
       int nosides;
       int []polyx;
       int[] polyy;
    Polygon(){
        nosides = rand.nextInt(7)+ 3 ;
        int count = nosides;
        polyx = new int[count];
        polyy = new int[count];
        for (int i = 0; i < nosides; i++){
            polyx[i] = rand.nextInt(width);
            polyy[i] = rand.nextInt(height);
            count++;
        }
    }

        }
class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean endprogram = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("START - run the program");
        System.out.println("STOP - save the shapes to a file");
        System.out.println("RUN - run the saved shapes");
       // String x = scan.nextLine();
        //String y = scan.nextLine();
        //String z = scan.nextLine();
        JFrame frame = new JFrame();
        Shapes panel = new Shapes();
        frame.setSize(Shapes.MAX_WIDTH, Shapes.MAX_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.setBackground(Color.black);
        createShapes x1 = new createShapes();
        Thread threader = new Thread(x1);
        threader.start();

    }
}

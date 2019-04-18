import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

 public abstract class Shapes extends JComponent  {
    static final int MAX_HEIGHT = 1000;
    static final int MAX_WIDTH = 1000;

    public void paint(Graphics g) {

        //set random color
        /*Random rainbow = new Random();
        int red = rainbow.nextInt(255);
        int blue = rainbow.nextInt(255);
        int green = rainbow.nextInt(255);
       // int sideno = rainbow.nextInt(7)+3;
        g.setColor(new Color(red, blue, green));
        repaint();*/


        /*
        double theta = 2 * Math.PI/sideno;
        int[] arrx = new int[sideno];
        int[] arry = new int[sideno];
        for(int i =0;i<sideno;i++){
            arrx[i] = getWidth()/2 +(int)(Math.cos(theta * i)*50);
            arry[i] = getHeight()/2 + (int)(Math.sin(theta * i)*50);
            */
        }
        public static Color Rainbow(){
            Random rainbow = new Random();
            int red = rainbow.nextInt(255);
            int blue = rainbow.nextInt(255);
            int green = rainbow.nextInt(255);
            return new Color(red,green,blue);
        }
        //g.drawPolygon(arrx,arry,sideno);
    }


class createShapes implements Runnable {
    int x1,y1;
    ArrayList<Object> shapes = new ArrayList<>();
    createShapes(int x, int y){
       this.x1 = x;
       this.y1 = x;
    }

        public void run(){
            for (int i = 1; i < 99; i++) {
                int x= (int)(Math.random()*x1);
                int y = (int)(Math.random()*y1);
                System.out.println(x + " " + y);
                shapes.add(new PPolygon(x, y));
                shapes.add(new POval(x,y));
                //count +=count;
            }



        try {
            Thread.sleep(300);
        }
        catch(InterruptedException e){
            e.printStackTrace();

        }
    }
}




class POval extends Shapes {

    protected int width;
    protected int height;
    protected int rad;

    int x,y;
    Color g1 = Shapes.Rainbow();
    POval(int x, int y) {
        this.x=x;
        this.y=y;
        width=(int)(Math.random()*x);
        height=(int)(Math.random()*y);
        }
        @Override
        public void paint(Graphics g){
        super.paintComponent(g);
        g.setColor(g1);
        g.drawOval(x,y,width,height);

        }

    }
   class PPolygon extends Shapes{
       int nosides;
       int []polyx;
       int[] polyy;
       Color g1 = Shapes.Rainbow();

       Random rand = new Random();
    PPolygon(int x, int y){
        nosides = rand.nextInt(7)+ 3 ;
        polyx = new int[nosides];
        polyy = new int[nosides];
        for (int i = 0; i < nosides; i++){
            polyx[i] = rand.nextInt(x);
            polyy[i] = rand.nextInt(y);
        }
    }
    @Override
       public void paint(Graphics g){
        super.paintComponent(g);

        // int sideno = rainbow.nextInt(7)+3;
        g.setColor(g1);
        g.drawPolygon(polyx,polyy,nosides);

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

        frame.setSize(Shapes.MAX_WIDTH, Shapes.MAX_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        frame.setBackground(Color.black);
//        frame.getContentPane().add(new JFrame());
        createShapes x1 = new createShapes(Shapes.MAX_WIDTH,Shapes.MAX_HEIGHT);
        Thread threader = new Thread(x1);
        threader.start();
        threader.join();
        for(Object x : x1.shapes){
            frame.add(((Shapes)x));
            frame.setVisible(true);
            Thread.sleep(200);
        }

    }
}

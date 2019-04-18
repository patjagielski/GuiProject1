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
        repaint();
        g.drawOval(10, 40, 50, 100);

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
    int x,y;
    ArrayList<Object> shapes = new ArrayList<>();
    createShapes(int x, int y){
       this.x = 10;
       this.y = 10;
    }

        public void run(){
            for (int i = 1; i < 99; i++) {
                shapes.add(new PPolygon(x, y));
                shapes.add(new POval());
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




class POval extends JComponent {
    protected int width;
    protected int height;
    protected int rad;
    protected Random rand = new Random();
    int x,y;
    POval(int width, int rad) {
        this.width = width;
        this.rad = rad;
        }
        @Override
        public void paint(Graphics g){
        super.paintComponent(g);
            Random rainbow = new Random();
            int red = rainbow.nextInt(255);
            int blue = rainbow.nextInt(255);
            int green = rainbow.nextInt(255);
            g.setColor(new Color(red, blue, green));
        g.drawOval(x,y,width,height);
        repaint();
        }
        public POval dimensions(int x, int r){
        POval PO1 = new POval(x, r);
        x = rand.nextInt();
        r = width/2;


        }
    }
   class PPolygon extends JComponent{
       int nosides;
       int []polyx;
       int[] polyy;

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
        g.drawPolygon(polyx,polyy,nosides);
        Random rainbow = new Random();
        int red = rainbow.nextInt(255);
        int blue = rainbow.nextInt(255);
        int green = rainbow.nextInt(255);
        // int sideno = rainbow.nextInt(7)+3;
        g.setColor(new Color(red, blue, green));
        repaint();
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
//        frame.getContentPane().add(new JFrame());
        createShapes x1 = new createShapes(Shapes.MAX_WIDTH,Shapes.MAX_HEIGHT);
        Thread threader = new Thread(x1);
        threader.start();
        threader.join();
        for(Object x:x1.shapes){
            System.out.println(x);
        }
    }
}

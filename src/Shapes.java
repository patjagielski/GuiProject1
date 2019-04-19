import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Shapes extends JComponent{
    static final int MAX_HEIGHT = 1000;
    static final int MAX_WIDTH = 1000;
    protected int width;
    protected int rad;
    int x,y;
    int nosides;
    int []polyx;
    int[] polyy;
    String objectType;

/*
    public void paint(Graphics g) {

        //set random color
       // int sideno = rainbow.nextInt(7)+3;

        /*
        double theta = 2 * Math.PI/sideno;
        int[] arrx = new int[sideno];
        int[] arry = new int[sideno];
        for(int i =0;i<sideno;i++){
            arrx[i] = getWidth()/2 +(int)(Math.cos(theta * i)*50);
            arry[i] = getHeight()/2 + (int)(Math.sin(theta * i)*50);

    }
    */
    public static Color rainbow() {
        Random rainbow = new Random();
        int red = rainbow.nextInt(255);
        int blue = rainbow.nextInt(255);
        int green = rainbow.nextInt(255);
        return new Color(red, blue, green);
    }

    }


class createShapes implements Runnable {
    int width, height;
    Frame frame;
    ArrayList<Shapes> shapes = new ArrayList<>();

    createShapes(int x, int y, Frame frame) {
        this.width = x;
        this.height = y;
        this.frame = frame;
    }

    public void run() {
        for (int i = 1; i < 99; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            System.out.println(x + " | " + y);
            shapes.add(new PPolygon(x, y));
            shapes.add(new POval(x, y));
        }

            double scalex = (double) frame.getWidth() / Shapes.MAX_WIDTH;
            double scaley = (double) frame.getHeight() / Shapes.MAX_HEIGHT;
        System.out.printf(scalex+" "+scaley);
/*
            for (Shapes no : shapes) {
                no.x *= scalex;
                no.y *= scaley;
                if (no.objectType.equals("Polygon")) {
                    for (int i = 0; i < no.polyx.length; i++) {
                        no.polyx[i] = no.polyx[i] * (int) scalex;
                        no.polyy[i] = no.polyy[i] * (int) scaley;

                    }
                }

           }
           */

            frame.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

    }
}


    class POval extends Shapes {
        Color g1 = Shapes.rainbow();


        POval(int x, int y) {
            super();
            objectType = "Oval";
            this.x = x;
            this.y = y;
            this.width = (int) (Math.random() * 100);
            this.rad = (int) (Math.random() * 100);

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(g1);
            g.drawOval(x, y, width, rad);
            repaint();
            while(x > MAX_WIDTH ||  y>MAX_HEIGHT){
                x= 0;
                y =0;
            }
            while(x<0 || y<0){
                x = 0;
                y = 0;
            }
        }

        @Override
        public String toString() {
            return null;
        }
    }

    class PPolygon extends Shapes {
        Color g1 = Shapes.rainbow();
        Random rand = new Random();

        PPolygon(int x, int y) {
            super();
            objectType = "Polygon";
            nosides = rand.nextInt(3) + 3;
            polyx = new int[nosides];
            polyy = new int[nosides];
            for (int i = 0; i < nosides; i++) {
                polyx[i] = rand.nextInt(x);
                polyy[i] = rand.nextInt(y);

            }
        }

        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            g.setColor(g1);
            g.drawPolygon(polyx, polyy, nosides);
            // int sideno = rainbow.nextInt(7)+3;
            while(x > MAX_WIDTH || x< 0 || y>MAX_HEIGHT || y<0){
                x = 0;
                y = 0;
            }
        }

        @Override
        public String toString() {
            return super.toString();
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
            frame.setResizable(true);
            frame.setBackground(Color.black);
            createShapes x1 = new createShapes(Shapes.MAX_WIDTH, Shapes.MAX_HEIGHT, frame);
            Thread threader = new Thread(x1);
            threader.start();
            threader.join();
            for (Object x : x1.shapes) {
                frame.add((Shapes) (x));
                frame.setVisible(true);
                System.out.println(x);
                Thread.sleep(600);
            }

        }
    }


import javax.swing.*;
import java.awt.*;
import java.io.*;
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
    int scalex = getWidth() / Shapes.MAX_WIDTH;
    int scaley = getHeight() / Shapes.MAX_HEIGHT;

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
        System.out.printf(scalex + " " + scaley);
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

    public void read() throws IOException {
        try {
            FileWriter fw = new FileWriter("GUIProjects");
            Writer output = new BufferedWriter(fw);
            int sz = shapes.size();
            for (int i = 0; i < sz; i++) {
                output.write(shapes.get(i).toString() + " ");
            }
            output.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void write(){
        String s;
        try{
            BufferedReader input = new BufferedReader(new FileReader("GUIProjects.txt"));
            if(!input.ready()){
                throw new IOException();
            }

            int sz = shapes.size();
            for(int i =0; i<sz; i++){
                System.out.println(shapes.get(i).toString());
            }
        }catch (IOException e){
            System.err.println(e);
        }

    }


}



    class POval extends Shapes {
        Color g1 = Shapes.rainbow();


        POval(int x, int y) {
            super();
            objectType = "Oval";
            this.x = getWidth()*scalex;
            this.y = getHeight()*scaley;
            this.width = (int)(Math.random()*MAX_WIDTH);
            this.rad = (int)(Math.random()*MAX_HEIGHT/2);


        }
        public String objType(){
            return objectType;
        }
        public Color getRGB(){
            return POval.rainbow();
        }
        public ArrayList getDimensions(){
            int count = 0;
            ArrayList<Integer> dimO = new ArrayList<>();
            for(int i = 0; i<4; i++){
                if(count == 0) {
                  dimO.add(x);
                    count++;
                }else if(count == 1){
                    dimO.add(y);
                    count++;
                }else if(count == 2){
                    dimO.add(width);
                    count++;
                }
                else{
                    dimO.add(rad);
                }
            }
            return dimO;
        }
        @Override
        public String toString(){
            return  "|" +objType() + " " + getRGB() + " " + getDimensions() + " |";
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(g1);
            g.drawOval(x, y, width, rad);
            repaint();
        }


    }

    class PPolygon extends Shapes{
        Color g1 = Shapes.rainbow();
        Random rand = new Random();

        PPolygon(int x, int y) {
            super();
            objectType = "Polygon";
            nosides = rand.nextInt(7) + 3;
            polyx = new int[nosides];
            polyy = new int[nosides];
            for (int i = 0; i < nosides; i++) {
                polyx[i] = rand.nextInt(x);
                polyy[i] = rand.nextInt(y);
            }
        }
        /*

        //set random color
       // int sideno = rainbow.nextInt(7)+3;
        double theta = 2 * Math.PI/sideno;
        int[] arrx = new int[sideno];
        int[] arry = new int[sideno];
        for(int i =0;i<sideno;i++){
            arrx[i] = getWidth()/2 +(int)(Math.cos(theta * i)*50);
            arry[i] = getHeight()/2 + (int)(Math.sin(theta * i)*50);

    }
    */
        public String ObjName(){
            return objectType;
        }
        public Color ShapeRGB(){
           return PPolygon.rainbow();
        }
        public ArrayList<Integer> getDimensions(){
            int count = 0;
            int it = 0;
            ArrayList<Integer> dim = new ArrayList<>();
            for(int i = 0; i<it; i++){
                if(count == 0) {
                    dim.add(polyx[it]);
                   count++;
                }else{
                    dim.add(polyy[it]);
                    count--;
                    it++;
                }
            }
            return dim;
        }
        @Override
        public String toString(){
            return "/" + ObjName() + " " + ShapeRGB() + " " + getDimensions() + "/";
        }



        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            g.setColor(g1);
            g.drawPolygon(polyx, polyy, nosides);


        }

    }

    class Main {
        public static void main(String[] args) throws InterruptedException   {
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


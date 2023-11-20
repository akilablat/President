import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class Map_data {
    private static USstate[]  states = new USstate[50];
   
    Map_data() throws FileNotFoundException, IOException  {
        String filename = "lib\\state.txt";
        readMapFile(filename);
        
        for (USstate state : states) {
            System.out.println(state);
            state.printCoordinates();
            System.out.println();
        }
       
    }
    
    public USstate[] getStates()
    {
        return states;
    }
    
    public static void readMapFile(String filename) throws FileNotFoundException, IOException
    {
        FileInputStream fstream = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        
        String line;
        int counter = 0;
        while ((line = br.readLine()) != null)
        {
                String[] content= line.split("#");
                System.out.println(content[0] +"=>"+content[1] + "=>" + (content.length));
                USstate s = new USstate();
                s.setIndex(Integer.parseInt(content[0]));
                s.setName(content[1]);
                double[][] c = new double[content.length-2][2];
         
                for (int i=0; i < content.length-2; i++)
                {
                  String[] latlong = content[i+2].split(",");  
                  c[i][0] =  Double.parseDouble(latlong[0]);
                  c[i][1] =  Double.parseDouble(latlong[1]);
                  
                }
                s.setCoordinate(c);
                states[counter++] = s;
        }
        br.close();
    }
    
}

class USstate{
    private int index;
    private String name;
    private double[][] coordinate;
    public USstate(int i, String n, double[][] c)
    {
        index = i;
        name = n;
        coordinate = c; 
    }
    
    public USstate()
    {
        index = 0;
        name = null;
        coordinate = null;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(double[][] coordinate) {
        this.coordinate = coordinate;
    }
    
    @Override
    public String toString()
    {
        String result = String.format("%2d %s %d", index, name, coordinate.length);
        return result;         
    }
    public void printCoordinates()
    {
        for (int i=0; i < coordinate.length; i++)
            System.out.print(coordinate[i][0] + "," + coordinate[i][1] + "#");
    }
}

public class Csci230_mapanddata extends JFrame implements ActionListener{ 
    JTextPane text;
    JButton start,stop;
    JLabel year;
    JPanel panel;
    ArrayList<String> ye,htmltxt;
    int z1, z2,z3,z4,z5,z6,z7,z8,z9,z10,z11,z12,z13,z14,z15,z16,z17,z18,z19,z20,zz,ax;
    private static Map_data mymap; 
    private static USstate[] mystate = new USstate[50];
    private static double[][] mycoordinate; 
    Polygon[] polygons  = new Polygon[50];
    Color[] regularcolors = new Color[] {  
           Color.lightGray, new Color(255,204,204), new Color(255,153,153), new Color(255,77,77), new Color(230,0,0), new Color(128,0,0),new Color(77,0,0) }; 
    Color[] colors = new Color[50];  
    
    public Csci230_mapanddata() throws IOException{

        for(int i= 0; i <colors.length; i++)
        colors[i] = regularcolors[0];
        mymap = new Map_data();
        mystate = mymap.getStates();
        z1=z2=z3=z4=z5=z6=z7=z8=z9=z10=z11=z12=z13=z14=z15=z16=z17=z18=z20=0;
        z19=zz=1;

        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setBackground(Color.black); 
                g2d.setStroke(new BasicStroke(2));  
                for (int j =0; j < mystate.length; j++)
                {
                mycoordinate = mystate[j].getCoordinate(); 
                g2d.setColor(colors[j]);
                Polygon onestate = new Polygon();
                    for (double[] mycoordinate1 : mycoordinate) {
                        onestate.addPoint((int) Math.abs(mycoordinate1[0]), (int) Math.abs(mycoordinate1[1]));
                    }
                polygons[j] = onestate;
                g2d.fillPolygon(onestate);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(onestate);
                }
                g2d.setColor(new Color(77,0,0));
                g2d.fillRect(800, 400, 50, 20);
                g2d.setColor(new Color(128,0,0));
                g2d.fillRect(800, 420, 50, 20);
                g2d.setColor(new Color(230,0,0));
                g2d.fillRect(800, 440, 50, 20);
                g2d.setColor(new Color(255,77,77));
                g2d.fillRect(800, 460, 50, 20);
                g2d.setColor(new Color(255,153,153));
                g2d.fillRect(800, 480, 50, 20);
                g2d.setColor(new Color(255,204,204));
                g2d.fillRect(800, 500, 50, 20);
                g2d.setColor(Color.black);
                g2d.drawString("6 and more", 852,415);
                g2d.drawString("5", 852,435);
                g2d.drawString("4", 852,455);
                g2d.drawString("3", 852,477);
                g2d.drawString("2", 852,495);
                g2d.drawString("1", 852,515);
                g2d.drawString("Number of U.S. Presidents primarily", 600,515);
                g2d.drawString("affiliated with each state", 600,530);
                g2d.setColor(regularcolors[z1]);
                Polygon cali = new Polygon();
                double[][]mc = mystate[4].getCoordinate();
                for (double[] mc1 : mc){
                    cali.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(cali);
                g2d.setColor(regularcolors[z2]);
                Polygon ak = new Polygon();
                double[][]mb = mystate[2].getCoordinate();
                for (double[] mc1 : mb){
                    ak.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(ak);
                g2d.setColor(regularcolors[z3]);
                Polygon ge = new Polygon();
                double[][]ma = mystate[9].getCoordinate();
                for (double[] mc1 : ma){
                    ge.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(ge);
                g2d.setColor(regularcolors[z4]);
                Polygon ha = new Polygon();
                double[][]md = mystate[10].getCoordinate();
                for (double[] mc1 : md){
                    ha.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(ha);
                g2d.setColor(regularcolors[z5]);
                Polygon ia = new Polygon();
                double[][]me = mystate[11].getCoordinate();
                for (double[] mc1 : me){
                    ia.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(ia);
                g2d.setColor(regularcolors[z6]);
                Polygon il = new Polygon();
                double[][]mf = mystate[13].getCoordinate();
                for (double[] mc1 : mf){
                    il.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(il);
                g2d.setColor(regularcolors[z7]);
                Polygon ke = new Polygon();
                double[][]mg = mystate[16].getCoordinate();
                for (double[] mc1 : mg){
                    ke.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(ke);
                g2d.setColor(regularcolors[z8]);
                Polygon con = new Polygon();
                double[][]mh = mystate[6].getCoordinate();
                for (double[] mc1 : mh){
                    con.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(con);
                g2d.setColor(regularcolors[z9]);
                Polygon mas = new Polygon();
                double[][]mi = mystate[18].getCoordinate();
                for (double[] mc1 : mi){
                    mas.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(mas);
                g2d.setColor(regularcolors[z10]);
                Polygon nc = new Polygon();
                double[][]mj = mystate[26].getCoordinate();
                for (double[] mc1 : mj){
                    nc.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(nc);
                g2d.setColor(regularcolors[z11]);
                Polygon mis = new Polygon();
                double[][]mk = mystate[23].getCoordinate();
                for (double[] mc1 : mk){
                    mis.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(mis);
                g2d.setColor(regularcolors[z12]);
                Polygon neba = new Polygon();
                double[][]ml = mystate[28].getCoordinate();
                for (double[] mc1 : ml){
                    neba.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(neba);
                g2d.setColor(regularcolors[z13]);
                Polygon nhm = new Polygon();
                double[][]mm = mystate[29].getCoordinate();
                for (double[] mc1 : mm){
                    nhm.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(nhm);
                g2d.setColor(regularcolors[z14]);
                Polygon nje = new Polygon();
                double[][]mn = mystate[30].getCoordinate();
                for (double[] mc1 : mn){
                    nje.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(nje);
                g2d.setColor(regularcolors[z15]);
                Polygon nyk = new Polygon();
                double[][]mo = mystate[33].getCoordinate();
                for (double[] mc1 : mo){
                    nyk.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(nyk);
                g2d.setColor(regularcolors[z16]);
                Polygon oh = new Polygon();
                double[][]mp = mystate[34].getCoordinate();
                for (double[] mc1 : mp){
                    oh.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(oh);
                g2d.setColor(regularcolors[z17]);
                Polygon pe = new Polygon();
                double[][]mq = mystate[37].getCoordinate();
                for (double[] mc1 : mq){
                    pe.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(pe);
                g2d.setColor(regularcolors[z18]);
                Polygon tx = new Polygon();
                double[][]mr = mystate[42].getCoordinate();
                for (double[] mc1 : mr){
                    tx.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(tx);
                g2d.setColor(regularcolors[z19]);
                Polygon vir = new Polygon();
                double[][]ms = mystate[44].getCoordinate();
                for (double[] mc1 : ms){
                    vir.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(vir);
                g2d.setColor(regularcolors[z20]);
                Polygon ver = new Polygon();
                double[][]mt = mystate[45].getCoordinate();
                for (double[] mc1 : mt){
                    ver.addPoint((int)Math.abs(mc1[0]),(int)Math.abs(mc1[1]));
                }
                g2d.fillPolygon(ver);
            }   
            };
            panel.setBounds(0,0,1000,650);
            
        ye = new ArrayList<String>();
        htmltxt = new ArrayList<String>();
        ax=0;
        Scanner scanner = new Scanner(new File("lib\\presidents.txt"));
        while(scanner.hasNextLine()){
        String[] line = scanner.nextLine().split("#");
        ye.add(line[1]);
        htmltxt.add(line[2]);
        }scanner.close();
        System.out.println(ye);

        text = new JTextPane();
        start = new JButton("Start");
        stop = new JButton("Stop");
        year = new JLabel();

        year.setBounds(300, 680, 500, 100);
        year.setFont(new Font("Arial", Font.BOLD, 40));
        year.setText(ye.get(ax));

        stop.setBounds(700, 720, 100, 30);
        stop.setBackground(Color.white);
        stop.setFocusable(false);
        stop.addActionListener(this);
        start.setBounds(590, 720, 100, 30);
        start.setBackground(Color.white);
        start.setFocusable(false);
        start.addActionListener(this);

        text.setBounds(1000, 10, 260, 740);
        text.setEditable(false);
        text.setContentType("text/html");
        text.setText(htmltxt.get(ax));
        add(start);
        add(stop);
        add(year);
        add(text);
        add(panel);
        setSize(1280, 800);
        setTitle("United States");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
    }

      
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
               
            Csci230_mapanddata frame = null;
                try {
                    frame = new Csci230_mapanddata();
                } catch (IOException ex) {
                    Logger.getLogger(Csci230_mapanddata.class.getName()).log(Level.SEVERE, null, ex);
                }
            frame.setVisible(true);
        }
        });}
        Timer t2 = new Timer(5000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent c){
                ax = ax + 1;
                if (ax == 65){
                    ax = 0;
                }
                zz = zz + 1;
               
                if (zz == 1){
                    z19 = 1;}
                if (zz == 3){
                    z9 = 1;}
                if (zz == 4){
                    z19 = 2;}
                if (zz == 6){
                    z19 = 3;}
                if (zz == 8){
                    z19 = 4;}
                if (zz == 10){
                    z9 = 2;}
                if (zz == 11){
                    z10 = 1;}
                 if (zz == 13){
                    z15 = 1;}
                 if (zz == 14){
                     z19 = 5;}
                 if (zz == 15){
                    z19 = 6;}
                if (zz == 16){
                    z10 = 2;}
                if (zz == 18){
                    z15 = 2;}
                if (zz == 19){
                    z13 = 1;}
                 if (zz == 20){
                    z17 = 1;}
                if (zz == 21){
                    z7 = 1;}
                if (zz == 22){
                    z10 = 3;}
                if (zz == 23){
                     z16 = 1;} 
                if (zz == 25){
                    z16 = 2;}  
                if (zz == 26){
                    z16 = 3;} 
                if (zz == 27){
                    z20 = 1;}
                if (zz == 28){
                    z14 = 1;} 
                if (zz == 29){
                    z16 = 4;}
                if (zz == 30){
                    z14 = 2;} 
                if (zz == 31){
                    z16 = 5;} 
                if (zz == 32){
                    z15 = 3;}  
                if (zz == 34){
                    z16 = 6;} 
                if (zz == 38){
                    z20 = 2;}
                if (zz == 40){
                    z5 = 1;}
                if (zz == 41){
                    z15 = 4;}  
                if (zz == 44){
                    z11 = 1;} 
                if (zz == 46){
                    z18 = 1;}
                if (zz == 48){
                    z9 = 3;} 
                if (zz == 49){
                    z18 = 2;}
                if (zz == 51){
                    z1 = 1;}  
                if (zz == 53){
                    z12 = 1;} 
                if (zz == 54){
                    z3 = 1;} 
                if (zz == 55){
                    z6 = 1;}
                if (zz == 57){
                    z9 = 4;}  
                if (zz == 58){
                    z2 = 1;}
                if (zz == 60){
                    z8 = 1;}
                if (zz == 62){
                    z4 = 1;}
                if (zz == 64){
                    z15 = 5;} 
                if (zz == 65){
                    z17 = 2;}
                if (zz == 66){
                    zz = z19 = 1;
                    z1=z2=z3=z4=z5=z6=z7=z8=z9=z10=z11=z12=z13=z14=z15=z16=z17=z18=z20=0;}

                    year.setText(ye.get(ax));
                    text.setText(htmltxt.get(ax));
                    panel.repaint(); 
                }
             });
   
    @Override
    public void actionPerformed(ActionEvent c){  
       if (c.getSource() == start){
           t2.start();
        }
        if (c.getSource() == stop){
            t2.stop();
        }
    } 
}







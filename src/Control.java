/**
 *
 * @author Ivan
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Control  extends Frame{
    Button inicio;
    Ventana v = new Ventana();

   public Control(){
        super("Viruz Blog: Control de Hilos");
        setSize(420,420);
        setLayout(new FlowLayout());
        inicio = new Button("iniciar");
        add(inicio);
        setBackground(Color.black);
        v.setLocation(50,50);
        v.setVisible(true);
        setLocation(500,50);
        setVisible(true);

        addWindowListener(new WindowAdapter(){
        @Override
        public void windowClosing(WindowEvent e){
        System.exit(0);
        }
        });

        inicio.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            Graphics g= getGraphics();
            Balon b = new Balon(g,v);

            b.start();
            }
        });
    }

     private class Balon extends Thread{
        int x,y,diametro;
        boolean direccionX=true;
        boolean direccionY=true;
        Graphics g;
        Ventana v;

     public Balon(Graphics g,Ventana v){
            x= (int)(Math.random()*300+1);
            y=(int)(Math.random()*300+1);
            diametro=10;
            this.g=g;
            this.v=v;
        }
     
        @Override
         public  void run(){
            int incX,incY;
            while(!v.salir){
                g.setColor(Color.BLACK);
                g.fillOval(x,y,diametro,diametro);
            if(direccionX) incX = v.IncX;
                else incX=-v.IncX;
                    if(x+incX<=0||x+incX>=400)
                        direccionX=!direccionX;
                            if(direccionY)
                                incY= v.IncY;
                                    else incY=-v.IncY;
                                        if(y+incY<=30||y+incY>=400)
                                            direccionY=!direccionY;
                                                x+=incX;
                                                y+=incY;
                                                g.setColor(Color.WHITE);
                                                g.fillOval(x,y,diametro,diametro);
                                                 try{
                                                    sleep(50);
                                                    }
                                                 catch(InterruptedException e){
                                                     System.err.print("Error de excepcion");
                                                    }
                                            }
                                     }
                             }
}
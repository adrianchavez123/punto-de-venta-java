

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.w3c.dom.css.*;
/**
 *
 * @author adrian
 */
public class Lienzo extends Canvas implements Runnable
{
    
     
     int x1 =25,x2=37,x3=49,x4=61;
     int tiempo = 9,tiempo2=9;
    Thread h1;
    private Image dbImage;
    private Graphics dbGraphic;
    private String arr[]=null;
    
    private Graphics bufferGraphics = null;
    private BufferStrategy bufferStrategy = null;
    
    private Thread thread;
    private boolean running;
    Image img;
    Lienzo()
    {
       this.thread = new Thread(this);
         
       try {
            img=ImageIO.read(this.getClass().getClassLoader().getResource("logo.png"));
            

        } catch (IOException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }
            running = true;
     }
    
    public void paint (Graphics g)
    {
        dbImage = createImage(getWidth(),getHeight());
        dbGraphic = dbImage.getGraphics();
        paintComponent(dbGraphic);
        g.drawImage(dbImage, 0, 0, this);
    }
    public void paintComponent(Graphics g)
    {
      
        if(bufferStrategy == null)
        {
            this.createBufferStrategy(2);
            bufferStrategy = this.getBufferStrategy();
            bufferGraphics = bufferStrategy.getDrawGraphics();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.thread.start();
            
        }
    }
    
    
    
    @Override
    public void run() {
     
         while(running)
            {
              
                //System.out.println("er");
                DoLogic();
                Draw();
                DrawBackBufferToScreen();
              
                Thread.currentThread();
               
            }
        
    }
    
    
    public void DoLogic()
    {
        //rect.setLocation(rect.x + 1, rect.y);
        
        
        //calculation of the leevel editor
        
    }
    
    public void Draw()
    {
        bufferGraphics = bufferStrategy.getDrawGraphics();
        try
        {
            bufferGraphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        
            Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
           
            Color c = new Color(0,151,156);
            bufferGraphics2D.setColor(c);
            bufferGraphics2D.fillRect(0,0,this.getSize().width, this.getSize().height);

            
            bufferGraphics2D.setColor(Color.WHITE);
            Font fu=new Font("Arial", Font.PLAIN, 15);
            bufferGraphics2D.setFont(fu);
            bufferGraphics2D.drawString("Iniciando ...", 15, 220);
            
            Font fuente=new Font("Arial", Font.PLAIN, 60);
            bufferGraphics2D.setFont(fuente);
            bufferGraphics2D.drawString("Tienda", 130, 130);
            
            
            bufferGraphics2D.setColor(Color.WHITE);
            Font fue=new Font("Monospaced", Font.PLAIN, 20);
            bufferGraphics2D.setFont(fue);
            bufferGraphics2D.drawString("CompuTec", 40, 30);
            
             bufferGraphics2D.drawImage(img,13, 13,this);
          
            
            
            if(x4<250)
            {
                if(tiempo%5==0 && tiempo >80)
                {
                    tiempo-=3;
                    x4+=5;
                }else
                {
                    x4+=3;
                }
                if(tiempo>2)
                    Thread.sleep(tiempo);
                
                
            }
           
            if(x3<238)
            {
                if(tiempo2%5==0 && tiempo2 >120)
                {
                    tiempo2-=3;
                    x3+=4;
                    x1++;
                    
                }else
                {
                    x3+=2;
                    x2++;
                    if(x3 %3==0)
                    x1+=2;
                }
                
                if(tiempo2>2)
                    Thread.sleep(tiempo2);
            }
            
            if(x3>=238 && x2<226)
            {
                x2+=2;
                x1++;
                Thread.sleep(4);
                
            }
            
            if(x2>=226 && x1<214)
            {
               x1++;
               Thread.sleep(4); 
            }
            
            tiempo=13;
            if(x4<440 && x1>200)
            {
                if(x4%5==0)
                {
                    tiempo--;
                    x4+=3;
                    x3++;
                }
                else
                {
                    x4+=2;
                    x3++;
                }
                
                if(tiempo>2)
                {
                    Thread.sleep(tiempo);
                }
                
            }
            
            
             tiempo2=13;
            if(x3<440 && x4>340)
            {
                if(x3%5==0)
                {
                    tiempo2--;
                    x3+=3;
                    x2++;
                }
                else
                {
                    x3+=2;
                    x2++;
                }
                
                if(tiempo2>2)
                {
                    Thread.sleep(tiempo2);
                }
                
            }
            int tiempo3=13;
            if(x2<440 && x3>340)
            {
                if(x2%5==0)
                {
                    tiempo3--;
                    x2+=3;
                    x1++;
                }
                else
                {
                    x2+=2;
                    x1++;
                }
                
                if(tiempo3>2)
                {
                    Thread.sleep(tiempo3);
                }
                
            }
            
            if(x1<440 && x2 >390)
            {
                x1+=2;
                Thread.sleep(5);
            }
            
            if(x1>=440)
            {
                
                running=false;
                bufferGraphics.dispose();
                bufferStrategy.dispose();
                thread.stop();
               
                 
            }
            bufferGraphics2D.fillOval(x1,150, 7, 7);
            bufferGraphics2D.fillOval(x2,150, 7, 7);
            bufferGraphics2D.fillOval(x3,150, 7, 7);
            bufferGraphics2D.fillOval(x4,150, 7, 7);
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
           
        }
    }

    public void DrawBackBufferToScreen()
    {
        bufferStrategy.show();
        
        Toolkit.getDefaultToolkit().sync();
    }

    public void cerrar()
    {
        running=false;
                //bufferGraphics.dispose();
                //bufferStrategy.dispose();
                thread.stop();
    }
    

   
}


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrian
 */
public class Welcome extends JFrame implements Runnable{
    
    JLabel min = new JLabel("-");
    JLabel close = new JLabel("x");
    Lienzo l = new Lienzo();
    long inicio;
    Thread t;
    Welcome()
    {
        this.setSize(440,250);
        this.setUndecorated(true);
        this.setLocation(460, 260);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        t = new Thread(this);
        
        
        l.setBounds(0,0,440,250);
        min.setBounds(390,10,16,16);
        close.setBounds(420,10,16,16);
        this.add(l);
        this.add(min);
        this.add(close);
        t.start();
        inicio = System.currentTimeMillis();
        min.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
               }

            @Override
            public void mouseReleased(MouseEvent e) {
               }

            @Override
            public void mouseEntered(MouseEvent e) {
              }

            @Override
            public void mouseExited(MouseEvent e) {
             }
        });
        
        close.addMouseListener(new MouseListener()
        {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
              }

            @Override
            public void mouseReleased(MouseEvent e) {
              }

            @Override
            public void mouseEntered(MouseEvent e) {
              }

            @Override
            public void mouseExited(MouseEvent e) {
              }
            
        });
    }
    
    
    public static void main (String [] a)
    {
        new Welcome().setVisible(true);
    }

    @Override
    public void run() {
        try {    
            Thread.sleep(9000);
            
            new Login().setVisible(true);
            l.cerrar();
            dispose();
            t.stop();
        } catch (InterruptedException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
}

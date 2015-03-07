
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrian
 */
public class Login extends JFrame
{
    JLabel lblNombre = new JLabel("Nombre de usuario");
    JLabel lblPass = new JLabel("Password");
    
    JTextField nombre = new JTextField();
    JPasswordField pass = new JPasswordField();
    
    JPanel panel = new JPanel();
    
    JButton btnLog =new JButton("Ingresar");
    JLabel lblIntentos =  new JLabel();
    
    private int idUsuario=0;
    private int intentos =0; 
    public Login()
    {
        setTitle("Login");
        setSize(450,330);
        setLocation(300,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        
        panel.setBackground(new Color(0,151,156));
        panel.setBounds(0,0,450,330);
        panel.setLayout(null);
        
        lblNombre.setBounds(10,30,300,48);
        lblNombre.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblNombre.setForeground(Color.WHITE);
        nombre.setBounds(250,40,130,30);
        
        
        lblPass.setBounds(30,100,150,48);
        lblPass.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblPass.setForeground(Color.WHITE);
        pass.setBounds(250,110,130,30);
        
        btnLog.setBounds(120,180,150,48);
        btnLog.setFont(new Font("Segoe UI Semibold", 1, 24));
        btnLog.setForeground(Color.WHITE);
        btnLog.setBackground(Color.LIGHT_GRAY);
      
        lblIntentos.setBounds(10,220,500,48);
        lblIntentos.setFont(new Font("Segoe UI Semibold", 1, 18));
        lblIntentos.setForeground(Color.RED);
        
        panel.add(lblNombre);
        panel.add(nombre);
        panel.add(pass);
        panel.add(lblPass);
        panel.add(btnLog);
        panel.add(lblIntentos);
        add(panel);
        
        btnLog.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e)
            {
                conexion();
            }
        });
        
    }
  
    
    private void conexion()
    {
        try {
            String username = nombre.getText();
            String password = pass.getText();
            
            if(username.equals("") || password.equals(""))
            {
                lblIntentos.setText("Los campos no pueden estar vacios");
            }else
            {
                Connection con;
                PreparedStatement pst;
                ResultSet rs;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","1234");
                String sentencia ="select idusuario  from usuarios where password =md5('"+password+"') and username ='"+username +"';";
                System.out.println(sentencia);
                pst = con.prepareStatement(sentencia);

                rs =  pst.executeQuery();

                while (rs.next()) 
                {

                    System.out.println(rs.getString(1));       
                    idUsuario = Integer.parseInt(rs.getString(1));
                }

                if(idUsuario == 0)
                {
                    intentos++;
                    lblIntentos.setText("Datos incorrectos, intente de nuevo. Intento :"+intentos);
                    System.out.println("intentos++");
                    
                    nombre.setText("");
                    pass.setText("");
                }
                else
                {
                    new Ventana(idUsuario).setVisible(true);
                    dispose();
                }
                
                if(intentos == 5)
                {
                    lblIntentos.setText("Datos incorrectos, intente de nuevo. Intento :"+intentos);
                    Thread.sleep(500);
                    
                    lblIntentos.setText("Datos invalidos. Contate al adminstrador");
                   
                    Thread.sleep(1000);
                    
                    System.exit(0);
                }
            }
        
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
    }
    
    public static void main(String []a)
    {
        new Ventana(1).setVisible(true);
        
    }
}

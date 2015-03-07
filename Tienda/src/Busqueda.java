
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrian
 */
public class Busqueda extends JFrame
{
    Connection con;
                PreparedStatement pst;
                ResultSet rs;
                
    String buscar=null;
    int tipo=0;
    
    JTable t;
    JScrollPane scroll;
    String a,b,c;
    String columna []={"Nombre","Codigo","Precio"};
    String datos[][]=null;
    JLabel lblPass = new JLabel("Selecccione el producto");
    JButton agregar = new JButton("Agregar");
    JButton cancelar = new JButton("Cancelar");
    JPanel p = new JPanel();
    Busqueda(String buscar,int tipo)
    {
        this.buscar=buscar;
        this.tipo =tipo;
        setTitle("Busqueda");
        setSize(400,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        lblPass.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblPass.setForeground(Color.BLUE);
        add(lblPass,BorderLayout.NORTH);
        if(tipo == 1)
        {
            busquedaPorNombre();
        }
        else if(tipo ==2)
        {
            busquedaPorCodigo();
        }
        else
        {
            busquedaPorCategoria();
        }
        
        scroll= new JScrollPane();
                         t = new JTable();
	
                        DefaultTableModel tableModel = new DefaultTableModel(datos,columna) {
                           
                            @Override
                            public boolean isCellEditable(int row, int column) {
                               
                               return false;
                            }
                        };

                        t.setModel(tableModel);
                        scroll.setViewportView(t);
                        scroll.setColumnHeaderView(t.getTableHeader());
                       add(scroll,BorderLayout.CENTER);
       
       add(p,BorderLayout.SOUTH);
       
       p.setLayout(null);
       p.setPreferredSize(new Dimension(400,100));
       p.setBackground(new Color(0,151,156));
       agregar.setBounds(50,30,100,24);
       cancelar.setBounds(200,30,100,24);
       p.add(agregar);
       p.add(cancelar);
       
       
       agregar.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
              
              a =t.getValueAt(t.getSelectedRow(), 0).toString();
              b =t.getValueAt(t.getSelectedRow(), 1).toString();
              c=t.getValueAt(t.getSelectedRow(), 2).toString();
              
              System.out.println("datos :\n"+a+"  "+b+"  "+c);
              dispose();
           }
       });
       
       cancelar.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {
               dispose();
           }
       });
    }

    private void busquedaPorNombre()
    {
        try {           
            ArrayList<String> id = new ArrayList<String>();
            ArrayList<String> precio = new ArrayList<String>();
            ArrayList<String> cantidad = new ArrayList<String>();
            ArrayList<String> codigo = new ArrayList<String>();
            ArrayList<String> nombre = new ArrayList<String>();
		
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","1234");
                String sentencia ="select idproducto,precioventa,nombre,cantidad,codigo from productos where nombre like '%"+buscar+"%';";
                System.out.println(sentencia);
               
            
            pst = con.prepareStatement(sentencia);
			
            rs =  pst.executeQuery();
			 while (rs.next()) {
	                
	                System.out.println(rs.getString(3)+rs.getString(2));
	                id.add(rs.getString(1));
	                
                        precio.add(rs.getString(2));
	                nombre.add(rs.getString(3));
	                cantidad.add(rs.getString(4));
	                codigo.add(rs.getString(5));
	                
                        }
			 
                         if(precio.size()>0)
                         {
                              
                              datos =  new String[precio.size()][3];
                             for(int i = 0; i< precio.size();i++)
                             {
                                 datos[i][0]=nombre.get(i);
                                 datos[i][1]=codigo.get(i);
                                 datos[i][2]=precio.get(i);
                             }
                         }
                         
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
    	{
    		try
    		{
    			if(rs != null)
    			{
    				rs.close();
    			}
    			if(pst != null)
    			{
    				pst.close();
    			}
    			
    			if(con !=null)
    			{
    				con.close();
    			}
    		}
    		catch(Exception ex)
    		{
    			System.out.println(ex.getMessage());
    		}
    	}
    }

    private void busquedaPorCodigo() {
        
        try {           
            ArrayList<String> precio = new ArrayList<String>();
            ArrayList<String> codigo = new ArrayList<String>();
            ArrayList<String> nombre = new ArrayList<String>();
		
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","1234");
                String sentencia ="select precioventa,nombre,codigo from productos where codigo like '"+buscar+"%';";
                System.out.println(sentencia);
               
            
            pst = con.prepareStatement(sentencia);
			
            rs =  pst.executeQuery();
			 while (rs.next()) {
	                
	                
                        precio.add(rs.getString(1));
	                nombre.add(rs.getString(2));
	                codigo.add(rs.getString(3));
                        System.out.println(rs.getString(3)+rs.getString(2));
	                           
                        }
			 
                         if(precio.size()>0)
                         {
                              
                              datos =  new String[precio.size()][3];
                             for(int i = 0; i< precio.size();i++)
                             {
                                 datos[i][0]=nombre.get(i);
                                 datos[i][1]=codigo.get(i);
                                 datos[i][2]=precio.get(i);
                             }
                         }
                         
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
    	{
    		try
    		{
    			if(rs != null)
    			{
    				rs.close();
    			}
    			if(pst != null)
    			{
    				pst.close();
    			}
    			
    			if(con !=null)
    			{
    				con.close();
    			}
    		}
    		catch(Exception ex)
    		{
    			System.out.println(ex.getMessage());
    		}
    	}
   
    }
    
    private void busquedaPorCategoria() {
        
        try {           
            ArrayList<String> precio = new ArrayList<String>();
            ArrayList<String> codigo = new ArrayList<String>();
            ArrayList<String> nombre = new ArrayList<String>();
		
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda","root","1234");
                String sentencia ="select precioventa,productos.nombre,codigo from productos,categoria where categoria.nombre ='"+buscar+"';";
                System.out.println(sentencia);
               
            
            pst = con.prepareStatement(sentencia);
			
            rs =  pst.executeQuery();
			 while (rs.next()) {
	                
	                
                        precio.add(rs.getString(1));
	                nombre.add(rs.getString(2));
	                codigo.add(rs.getString(3));
                        System.out.println(rs.getString(3)+rs.getString(2));
	                           
                        }
			 
                         if(precio.size()>0)
                         {
                              
                              datos =  new String[precio.size()][3];
                             for(int i = 0; i< precio.size();i++)
                             {
                                 datos[i][0]=nombre.get(i);
                                 datos[i][1]=codigo.get(i);
                                 datos[i][2]=precio.get(i);
                             }
                         }
                         
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally
    	{
    		try
    		{
    			if(rs != null)
    			{
    				rs.close();
    			}
    			if(pst != null)
    			{
    				pst.close();
    			}
    			
    			if(con !=null)
    			{
    				con.close();
    			}
    		}
    		catch(Exception ex)
    		{
    			System.out.println(ex.getMessage());
    		}
    	}
    }
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

public class Ventana extends JRibbonFrame {

    boolean compr = false;
    boolean venta = false;
    boolean consulta = false;
    ArrayList<String> id;
    ArrayList<Integer> ca;
    private final long serialVersionUID = 1L;
    // JFrame f = new JFrame();
    JPanel b = new JPanel();
    JPanel p = new JPanel();
    JPanel p2 = new JPanel();
    JTable tabla;
    JScrollPane scroll;
    String row[] = new String[4];
    DefaultTableModel model;
    String compra[] = {"Codigo", "Nombre producto", "Precio", "Cantidad"};
    JLabel lblTotal = new JLabel("Total : ");
    String nuevoProducto[] = {"codigo", "nombre", "precio compra", "precio venta", "cantidad", "categoria"};
    String nombre;
    JLabel nomCon = new JLabel("Nombre Empresa");
    JLabel dirCon = new JLabel("Direccion  Empresa");
    JLabel tipoCon = new JLabel();
    JPanel panelCon = new JPanel();
    String arreglob[][];
    String campos[];
    int d = 0;
    //buscar
    JTable t;
    JScrollPane scroll4;
    String nom, cod, pre;
    String columna[] = {"Nombre", "Codigo", "Precio"};
    String datos[][] = null;
    JLabel lblPass = new JLabel("Selecccione el producto");
    JPanel panelBuscar = new JPanel();
    JFrame fr;
    JPanel sur = new JPanel();
    JButton concretar = new JButton("Concretar");
    JButton rechazar = new JButton("Rechazar");
    ////consulta
    JFrame frConsulta;
    JRadioButton v = new JRadioButton("Ventas");
    JRadioButton c = new JRadioButton("Compras");
    ButtonGroup grupo;
    JPanel panelConsulta = new JPanel();
    JButton ok = new JButton("Ok");
    JButton cancel = new JButton("Cancelar");
    JLabel lblFecha = new JLabel("Ingresar fecha de la forma yyyy-mm-dd");
    JLabel lblFecha2 = new JLabel("Fecha Inicio");
    JLabel lblFecha3 = new JLabel("Fecha Fin");
    JTextField txtFecha1 = new JTextField();
    JTextField txtFecha2 = new JTextField();
    int tipoConsulta = 0;
    String fechaInicio = "";
    String fechaFin = "";
    //inventario
    JFrame frInventario;
    JPanel panelInventario = new JPanel();
    JButton okInventario = new JButton("Ok");
    JButton cancelInventario = new JButton("Cancelar");
    JLabel lblInventario = new JLabel("Seleccione en base a categoria");
    JCheckBox ckInventario[];
    String categoria[];
    String cat[];
    int idUsuario = 0;
    String usuario = "";

    Ventana(int user) {

        System.out.println("user" + user);
        usuario(user);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setExtendedState(MAXIMIZED_BOTH);
                pack();


                JRibbonBand band1 = new JRibbonBand(
                        "ventas",
                        getResizableIconFromResource("nuevo.png"));

                JRibbonBand band2 = new JRibbonBand("compras", getResizableIconFromResource("nuevo.png"));
                JRibbonBand band3 = new JRibbonBand("reportes", getResizableIconFromResource("nuevo_reporte.png"));
                JRibbonBand band4 = new JRibbonBand("ayuda", getResizableIconFromResource("ayuda.png"));

                JCommandButton button1 = new JCommandButton(
                        "Nueva Venta",
                        getResizableIconFromResource("nuevo.png"));
                JCommandButton button2 = new JCommandButton(
                        "Buscar por Nombre",
                        getResizableIconFromResource("buscar1.png"));
                JCommandButton button3 = new JCommandButton(
                        "Buscar por Codigo",
                        getResizableIconFromResource("buscar2.png"));
                JCommandButton button4 = new JCommandButton(
                        "Buscar por Categoria",
                        getResizableIconFromResource("add.png"));

                JCommandButton button5 = new JCommandButton(
                        "Eliminar Producto",
                        getResizableIconFromResource("remove.png"));




                JCommandButton button6 = new JCommandButton(
                        "Nueva Compra",
                        getResizableIconFromResource("nuevo.png"));

                JCommandButton button7 = new JCommandButton(
                        "Buscar por Nombre",
                        getResizableIconFromResource("buscar1.png"));
                JCommandButton button8 = new JCommandButton(
                        "Buscar por Codigo",
                        getResizableIconFromResource("buscar2.png"));
                JCommandButton button9 = new JCommandButton(
                        "Agregar Producto",
                        getResizableIconFromResource("add.png"));

                JCommandButton button10 = new JCommandButton(
                        "Cancelar Producto",
                        getResizableIconFromResource("remove.png"));

                JCommandButton button11 = new JCommandButton(
                        "Nuevo Producto",
                        getResizableIconFromResource("nuevo_producto.png"));


                JCommandButton button12 = new JCommandButton(
                        "Nuevo Reporte",
                        getResizableIconFromResource("nuevo_reporte.png"));

                JCommandButton button13 = new JCommandButton(
                        "Exportar a PDF",
                        getResizableIconFromResource("guardar_reporte.png"));

                JCommandButton button131 = new JCommandButton(
                        "Exportar a XLS",
                        getResizableIconFromResource("guardar_reporte.png"));

                JCommandButton button132 = new JCommandButton(
                        "Consultar Inventario",
                        getResizableIconFromResource("guardar_reporte.png"));

                JCommandButton button14 = new JCommandButton(
                        "Imprimir Reporte",
                        getResizableIconFromResource("imprimir.png"));


                JCommandButton button15 = new JCommandButton(
                        "Ayuda",
                        getResizableIconFromResource("ayuda.png"));

                JCommandButton button16 = new JCommandButton(
                        "Agregar Usuario",
                        getResizableIconFromResource("usuario.png"));

                JCommandButton button17 = new JCommandButton(
                        "Contacto",
                        getResizableIconFromResource("contacto.png"));

                button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("hola");
                        nuevo(1);
                    }
                });


                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (venta) {
                            nombre = JOptionPane.showInputDialog("Ingrese el Nombre del Producto");

                            busqueda(1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva venta");
                        }
                        //new Busqueda(nombre,1).setVisible(true);
                    }
                });

                button3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (venta) {
                            nombre = JOptionPane.showInputDialog("Ingrese el Codigo del Producto");

                            //new Busqueda(Codigo,2).setVisible(true);
                            busqueda(2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva venta");
                        }
                    }
                });


                button4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (venta) {
                            try {
                                String[] obj = null;
                                ArrayList<String> array = new ArrayList<String>();

                                Connection con;
                                PreparedStatement pst;
                                ResultSet rs;
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");
                                String sentencia = "select nombre from categoria;";
                                System.out.println(sentencia);
                                pst = con.prepareStatement(sentencia);

                                rs = pst.executeQuery();

                                while (rs.next()) {
                                    array.add(rs.getString(1));
                                }

                                obj = array.toArray(new String[array.size()]);
                                nombre = (String) JOptionPane.showInputDialog(
                                        null // para que se muestre centrado
                                        , "Seleccione de Datos" // Mensaje de la ventana
                                        , "Lista de Datos" // Titulo de la ventana
                                        , JOptionPane.QUESTION_MESSAGE // Icono
                                        , null //null para icono defecto de la ventana
                                        , obj // el objeto
                                        , 0 // posicion del que va aparecer seleccionado
                                        );

                                //new Busqueda(seleccion.toString(),3).setVisible(true);
                                System.out.println(nombre);
                                if (nombre.equals("null")) {
                                } else {
                                    busqueda(3);
                                }
                            } catch (Exception ex) {
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva venta");
                        }
                    }
                });

                button5.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (venta) {
                            try {
                                model.removeRow(tabla.getSelectedRow());
                            } catch (Exception es) {
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva venta");
                        }

                    }
                });


                button6.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nuevo(2);
                    }
                });

                button7.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (compr) {
                            nombre = JOptionPane.showInputDialog("Ingrese el Nombre del Producto");

                            busqueda(1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva compra");
                        }
                    }
                });

                button8.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (compr) {
                            nombre = JOptionPane.showInputDialog("Ingrese el Codigo del Producto");

                            //new Busqueda(Codigo,2).setVisible(true);
                            busqueda(2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva compra");
                        }
                    }
                });


                button9.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (compr) {
                            try {
                                String[] obj = null;
                                ArrayList<String> array = new ArrayList<String>();

                                Connection con;
                                PreparedStatement pst;
                                ResultSet rs;
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");
                                String sentencia = "select nombre from categoria;";
                                System.out.println(sentencia);
                                pst = con.prepareStatement(sentencia);

                                rs = pst.executeQuery();

                                while (rs.next()) {
                                    array.add(rs.getString(1));
                                }

                                obj = array.toArray(new String[array.size()]);
                                nombre = (String) JOptionPane.showInputDialog(
                                        null // para que se muestre centrado
                                        , "Seleccione de Datos" // Mensaje de la ventana
                                        , "Lista de Datos" // Titulo de la ventana
                                        , JOptionPane.QUESTION_MESSAGE // Icono
                                        , null //null para icono defecto de la ventana
                                        , obj // el objeto
                                        , 0 // posicion del que va aparecer seleccionado
                                        );

                                //new Busqueda(seleccion.toString(),3).setVisible(true);
                                System.out.println(nombre);
                                if (nombre.equals("null")) {
                                } else {
                                    busqueda(3);
                                }
                            } catch (Exception ex) {
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva compra");
                        }
                    }
                });

                button10.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (compr) {
                            try {
                                model.removeRow(tabla.getSelectedRow());
                            } catch (Exception es) {
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero crear una nueva compra");
                        }

                    }
                });


                button11.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newFrame();


                    }
                });

                button12.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (idUsuario == 1) {
                            if (!venta && !compr && !consulta) {
                                consulta();
                            } else {
                                borrar();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "necesitas privilegios");
                        }
                    }
                });

                button13.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (idUsuario == 1) {
                            if (consulta) {
                                JFileChooser fileChooser = new JFileChooser();
                                // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf","PDF"));//filtro para ver solo archivos .edu
                                int seleccion = fileChooser.showSaveDialog(null);
                                try {
                                    if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                                        File JFC = fileChooser.getSelectedFile();
                                        String PATH = JFC.getAbsolutePath();

                                        System.out.println(JFC.getPath());

                                        new CrearPdf(arreglob, campos, "2013-12-23", "adrian", d, PATH + ".pdf");
                                    }

                                } catch (Exception ex) {//por alguna excepcion salta un mensaje de error
                                    JOptionPane.showMessageDialog(null, "Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Primero debes realizar una consulta");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "necesitas privilegios");
                        }
                    }
                });

                button131.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (consulta) {
                            JFileChooser fileChooser = new JFileChooser();
                            // fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf","PDF"));//filtro para ver solo archivos .edu
                            int seleccion = fileChooser.showSaveDialog(null);
                            try {
                                if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                                    File JFC = fileChooser.getSelectedFile();
                                    String PATH = JFC.getAbsolutePath();

                                    System.out.println(JFC.getPath());

                                    Excel exe = new Excel(arreglob, campos, d);
                                    exe.crearExcel("reporte", PATH + ".xls");
                                }

                            } catch (Exception ex) {//por alguna excepcion salta un mensaje de error
                                JOptionPane.showMessageDialog(null, "Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Primero debes realizar una consulta");
                        }
                    }
                });

                button132.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (idUsuario == 1) {
                            if (!consulta && !venta && !compr) {

                                crearVentanaInventario();
                                System.out.println("fghht");
                            } else {

                                borrar();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "necesitas privilegios");
                        }
                    }
                });
                button14.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (idUsuario == 1) {
                            if (consulta) {
                                JFileChooser fileChooser = new JFileChooser();
                                int status = fileChooser.showOpenDialog(null);// Da un entero
// Si apretamos en aceptar ocurrirá esto
                                if (status == JFileChooser.APPROVE_OPTION) {
                                    File JFC = fileChooser.getSelectedFile();
                                    String PATH = JFC.getAbsolutePath();

                                    System.out.println(PATH);
                                    new Imprimir(PATH);
// Si apretamos en cancelar o cerramos la ventana ocurrirá esto
                                } else if (status == JFileChooser.CANCEL_OPTION) {
                                    System.out.println("Cancelar");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Primero debes realizar una consulta");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "necesitas privilegios");
                        }
                    }
                });

                button15.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Ayuda().setVisible(true);
                    }
                });

                button16.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Usuario().setVisible(true);
                    }
                });


                button17.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Contacto().setVisible(true);
                    }
                });


                concretar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        realizarOperaciones();
                    }
                });

                ///////////////////////
                rechazar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            BorderLayout layout = (BorderLayout) b.getLayout();
                            b.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                            b.updateUI();
                            b.repaint();

                            sur.setVisible(false);
                            venta = false;
                            compr = false;

                        } catch (Exception ex) {
                        }
                    }
                });

                band1.addCommandButton(button1, RibbonElementPriority.TOP);
                band1.addCommandButton(button2, RibbonElementPriority.MEDIUM);
                band1.addCommandButton(button3, RibbonElementPriority.MEDIUM);
                band1.addCommandButton(button4, RibbonElementPriority.MEDIUM);
                band1.addCommandButton(button5, RibbonElementPriority.MEDIUM);


                band2.addCommandButton(button6, RibbonElementPriority.TOP);
                band2.addCommandButton(button7, RibbonElementPriority.MEDIUM);
                band2.addCommandButton(button8, RibbonElementPriority.MEDIUM);
                band2.addCommandButton(button9, RibbonElementPriority.MEDIUM);
                band2.addCommandButton(button10, RibbonElementPriority.MEDIUM);
                band2.addCommandButton(button11, RibbonElementPriority.LOW);

                band3.addCommandButton(button12, RibbonElementPriority.MEDIUM);
                band3.addCommandButton(button13, RibbonElementPriority.MEDIUM);
                band3.addCommandButton(button131, RibbonElementPriority.MEDIUM);
                band3.addCommandButton(button14, RibbonElementPriority.MEDIUM);
                band3.addCommandButton(button132, RibbonElementPriority.MEDIUM);


                band4.addCommandButton(button15, RibbonElementPriority.TOP);
                band4.addCommandButton(button16, RibbonElementPriority.MEDIUM);
                band4.addCommandButton(button17, RibbonElementPriority.MEDIUM);


                band1.setResizePolicies((List) Arrays.asList(
                        new CoreRibbonResizePolicies.None(band1.getControlPanel()),
                        new CoreRibbonResizePolicies.Mirror(band1.getControlPanel()),
                        new CoreRibbonResizePolicies.Mid2Low(band1.getControlPanel()),
                        new CoreRibbonResizePolicies.High2Low(band2.getControlPanel()),
                        new IconRibbonBandResizePolicy(band1.getControlPanel())));


                band2.setResizePolicies((List) Arrays.asList(
                        new CoreRibbonResizePolicies.None(band2.getControlPanel()),
                        new CoreRibbonResizePolicies.Mirror(band2.getControlPanel()),
                        new CoreRibbonResizePolicies.Mid2Low(band2.getControlPanel()),
                        new IconRibbonBandResizePolicy(band2.getControlPanel())));


                band3.setResizePolicies((List) Arrays.asList(
                        new CoreRibbonResizePolicies.None(band1.getControlPanel()),
                        new IconRibbonBandResizePolicy(band3.getControlPanel())));


                band4.setResizePolicies((List) Arrays.asList(
                        new CoreRibbonResizePolicies.None(band4.getControlPanel()),
                        new CoreRibbonResizePolicies.Mirror(band4.getControlPanel()),
                        new CoreRibbonResizePolicies.Mid2Low(band4.getControlPanel()),
                        new IconRibbonBandResizePolicy(band4.getControlPanel())));

                RibbonTask task1 = new RibbonTask("VENTA", band1);
                RibbonTask task2 = new RibbonTask("COMPRA", band2);
                RibbonTask task3 = new RibbonTask("REPORTE", band3);
                RibbonTask task4 = new RibbonTask("AYUDA", band4);




                getRibbon().addTask(task1);
                getRibbon().addTask(task2);

                getRibbon().addTask(task3);
                getRibbon().addTask(task4);


                sur.setBackground(new Color(0, 151, 156));
                sur.add(concretar, BorderLayout.WEST);
                sur.add(rechazar, BorderLayout.CENTER);
                sur.add(lblTotal, BorderLayout.EAST);
                sur.setVisible(false);
                ///////////////b.add(sur,BorderLayout.SOUTH);

                b.setLayout(new BorderLayout());
                b.setBackground(new Color(0, 151, 156));
                lblTotal.setFont(new Font("Segoe UI Semibold", 1, 28));
                lblTotal.setForeground(Color.WHITE);
                p.setBackground(Color.red);
                p2.setBackground(Color.GREEN);

                add(b, BorderLayout.CENTER);
                add(sur, BorderLayout.SOUTH);
                add(p2, BorderLayout.WEST);

                getRibbon().setApplicationMenu(new RibbonApplicationMenu());
            }
        });
    }

    public ResizableIcon getResizableIconFromResource(String resource) {

        return ImageWrapperResizableIcon.getIcon(Ventana.class
                .getClassLoader().getResource(resource), new Dimension(48, 48));

    }

    private void newFrame() {
        final JTable tab;
        JLabel lblIngresar = new JLabel("Ingresar Nuevo Producto");
        final DefaultTableModel modelo = new DefaultTableModel(nuevoProducto, 0);
        JScrollPane scroll2 = new JScrollPane();
        tab = new JTable(modelo);

        scroll2.setViewportView(tab);
        scroll2.setColumnHeaderView(tab.getTableHeader());
        scroll2.setPreferredSize(new Dimension(1200, 400));

        JPanel panel = new JPanel();
        JButton agregar = new JButton("agregar");
        JButton cancelar = new JButton("cancelar");
        JButton nuevop = new JButton("Nuevo producto");
        JButton nuevocat = new JButton("Nueva Categoria");
        final JFrame f = new JFrame();

        f.setSize(850, 300);
        f.setLocation(200, 100);
        lblIngresar.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblIngresar.setForeground(Color.BLUE);
        f.add(scroll2, BorderLayout.CENTER);

        f.add(panel, BorderLayout.SOUTH);

        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(400, 100));
        panel.setBackground(new Color(0, 151, 156));
        agregar.setBounds(50, 30, 100, 24);
        cancelar.setBounds(200, 30, 100, 24);
        nuevop.setBounds(400, 30, 150, 24);
        nuevocat.setBounds(600, 30, 150, 24);
        panel.add(agregar);
        panel.add(cancelar);
        panel.add(nuevop);
        panel.add(nuevocat);

        nuevocat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                new Categoria().setVisible(true);
                f.dispose();
            }
        });

        categorias();
        JComboBox jcb = new JComboBox(categoria);
        TableColumn tc = tab.getColumnModel().getColumn(5);
        TableCellEditor tce = new DefaultCellEditor(jcb);
        tc.setCellEditor(tce);
        f.add(lblIngresar, BorderLayout.NORTH);
        f.setVisible(true);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        nuevop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] x = new String[nuevoProducto.length];

                modelo.addRow(x);

            }
        });
        agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection c = null;
                Statement st = null;
                ArrayList<String> array = new ArrayList<String>();
                String sentencia = "insert into productos(codigo,nombre,preciocompra,precioventa,cantidad,categoria) values(";
                try {

                    modelo.fireTableDataChanged();
                    String arrebi[][] = new String[modelo.getRowCount()][nuevoProducto.length];



                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        for (int j = 0; j < nuevoProducto.length; j++) {

                            System.out.print(modelo.getValueAt(i, j) + "\t");
                            arrebi[i][j] = (String) modelo.getValueAt(i, j);
                            if (j < nuevoProducto.length - 1) {
                                if (j == 2 || j == 3 || j == 4) {
                                    sentencia += arrebi[i][j] + ",";
                                } else {

                                    sentencia += "'" + arrebi[i][j] + "',";
                                }
                            } else {
                                //sentencia+=arrebi[i][j]+");";
                                for (int x = 0; x < categoria.length; x++) {
                                    if (arrebi[i][j].equals(categoria[x])) {
                                        sentencia += (x + 1) + ");";
                                    }
                                }
                            }
                        }
                        array.add(sentencia);
                        sentencia = "insert into productos(codigo,nombre,preciocompra,precioventa,cantidad,categoria) values(";
                        System.out.println();
                    }

                    ////////////////////////////
                    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");
                    st = c.createStatement();

                    c.setAutoCommit(false);

                    for (int i = 0; i < array.size(); i++) {
                        System.out.println(array.get(i));
                        st.executeUpdate(array.get(i));
                    }

                    c.commit();
                    f.dispose();
                } catch (Exception ex) {
                    try {
                        c.rollback();
                        System.out.println(ex.getMessage());
                    } catch (Exception exxxx) {
                    }
                } finally {
                    try {
                        st.close();
                        c.close();
                    } catch (Exception exx) {
                        System.out.println(exx.getMessage());
                    }
                }
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

    }

    private void busqueda(int tipo) {
        JButton agregar = new JButton("Agregar");
        JButton cancelar = new JButton("Cancelar");
        fr = new JFrame();
        fr.setTitle("Busqueda");
        fr.setSize(400, 300);
        fr.setLocation(300, 100);
        fr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        if (tipo == 1) {
            String sentencia = "select precioventa,nombre,codigo from productos where nombre like '%" + nombre + "%';";

            busquedaPorCategoria(sentencia);
        } else if (tipo == 2) {
            String sentencia = "select precioventa,nombre,codigo from productos where codigo like '" + nombre + "%';";

            busquedaPorCategoria(sentencia);
        } else if (tipo == 3) {
            String sentencia = "select precioventa,productos.nombre,codigo from productos,categoria where "
                    + "categoria.idcategoria = productos.categoria and categoria.nombre ='" + nombre + "';";

            busquedaPorCategoria(sentencia);
        }


        lblPass.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblPass.setForeground(Color.BLUE);
        fr.add(lblPass, BorderLayout.NORTH);

        scroll4 = new JScrollPane();
        t = new JTable();

        DefaultTableModel tableModel = new DefaultTableModel(datos, columna) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }
        };

        t.setModel(tableModel);
        scroll4.setViewportView(t);
        scroll4.setColumnHeaderView(t.getTableHeader());
        fr.add(scroll4, BorderLayout.CENTER);

        fr.add(panelBuscar, BorderLayout.SOUTH);

        panelBuscar.setLayout(null);
        panelBuscar.setPreferredSize(new Dimension(400, 100));
        panelBuscar.setBackground(new Color(0, 151, 156));
        agregar.setBounds(50, 30, 100, 24);
        cancelar.setBounds(200, 30, 100, 24);
        panelBuscar.add(agregar);
        panelBuscar.add(cancelar);

        fr.setVisible(true);

        agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                nom = t.getValueAt(t.getSelectedRow(), 0).toString();
                cod = t.getValueAt(t.getSelectedRow(), 1).toString();
                pre = t.getValueAt(t.getSelectedRow(), 2).toString();

                System.out.println("datos :\n" + nom + "  " + cod + "  " + pre);
                row[1] = nom;
                row[0] = cod;
                row[2] = pre;
                row[3] = String.valueOf(1);


                model.addRow(row);
//                model.setValueAt(nom, model.getRowCount()-1, 0);
//                model.setValueAt(cod,model.getRowCount()-1 , 1);
//                model.setValueAt(pre,model.getRowCount()-1 , 2);
//                model.setValueAt(String.valueOf(1),model.getRowCount()-1 , 1);
//                
                fr.dispose();
            }
        });

        cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
            }
        });
    }

    private void busquedaPorCategoria(String sentencia) {

        Connection c = null;
        PreparedStatement p = null;
        ResultSet r = null;

        try {
            ArrayList<String> precio = new ArrayList<String>();
            ArrayList<String> codigo = new ArrayList<String>();
            ArrayList<String> Nombre = new ArrayList<String>();

            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");
            // String sentencia ="select precioventa,productos.nombre,codigo from productos,categoria where categoria.nombre ='"+buscar+"';";
            System.out.println(sentencia);


            p = c.prepareStatement(sentencia);

            r = p.executeQuery();

            while (r.next()) {
                precio.add(r.getString(1));
                Nombre.add(r.getString(2));
                codigo.add(r.getString(3));

            }

            if (precio.size() > 0) {
                datos = new String[precio.size()][3];

                for (int i = 0; i < precio.size(); i++) {
                    datos[i][0] = Nombre.get(i);
                    datos[i][1] = codigo.get(i);
                    datos[i][2] = precio.get(i);
                }

            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } finally {
            try {
                if (r != null) {
                    r.close();
                }
                if (p != null) {
                    p.close();
                }

                if (c != null) {
                    c.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void consulta() {
        frConsulta = new JFrame();
        frConsulta.setTitle("Consulta");
        frConsulta.setSize(500, 400);
        frConsulta.setLocation(200, 100);
        frConsulta.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frConsulta.setLayout(null);

        grupo = new ButtonGroup();
        grupo.add(v);
        grupo.add(c);

        lblFecha.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblFecha.setForeground(Color.BLUE);
        lblFecha.setBounds(30, 30, 500, 40);
        v.setSelected(true);
        v.setBounds(50, 80, 100, 24);
        c.setBounds(50, 120, 100, 24);
        lblFecha2.setBounds(50, 200, 100, 24);
        txtFecha1.setBounds(200, 200, 100, 24);
        lblFecha3.setBounds(50, 250, 100, 24);
        txtFecha2.setBounds(200, 250, 100, 24);
        panelConsulta.setBounds(0, 300, 500, 100);
        panelConsulta.setBackground(new Color(0, 151, 156));
        panelConsulta.setLayout(null);
        ok.setBounds(80, 30, 100, 24);
        cancel.setBounds(300, 30, 100, 24);
        panelConsulta.add(ok);
        panelConsulta.add(cancel);

        frConsulta.add(lblFecha);
        frConsulta.add(v);
        frConsulta.add(c);
        frConsulta.add(lblFecha2);
        frConsulta.add(lblFecha3);
        frConsulta.add(txtFecha1);
        frConsulta.add(txtFecha2);
        frConsulta.add(panelConsulta);
        frConsulta.setVisible(true);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                String s[] = null;
                String temp1 = txtFecha1.getText();
                s = temp1.split("-");

                String s2[] = null;
                String temp2 = txtFecha2.getText();
                s2 = temp2.split("-");

                if (s.length == 3 && s2.length == 3) {
                    fechaInicio = temp1;
                    fechaFin = temp2;
                    ///fecha valida//////////////////////////////
                    if (v.isSelected()) {
                        tipoConsulta = 1;

                    } else if (c.isSelected()) {
                        tipoConsulta = 2;
                    }

                    System.out.println(fechaInicio + "\n" + fechaFin);

                    dibujarConsulta();
                } else {
                    JOptionPane.showMessageDialog(null, "Datos invalidos");
                }

                frConsulta.dispose();

            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                frConsulta.dispose();
            }
        });
    }

    private void dibujarConsulta() {
        try {
            BorderLayout layout = (BorderLayout) b.getLayout();
            b.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            b.updateUI();
            b.repaint();
        } catch (Exception e) {
        }
        sur.setVisible(false);

        compr = false;
        venta = false;
        consulta = true;

        if (tipoConsulta == 1) {
            tipoCon.setText("Reporte ventas " + fechaInicio + "    " + fechaFin);
        } else if (tipoConsulta == 2) {
            tipoCon.setText("Reporte compras " + fechaInicio + "    " + fechaFin);

        }


        panelCon.setVisible(true);
        panelCon.setBackground(Color.white);
        panelCon.add(nomCon, BorderLayout.PAGE_START);
        panelCon.add(dirCon, BorderLayout.PAGE_END);
        panelCon.add(tipoCon, BorderLayout.PAGE_END);

        generarTablaReporte();
        model = new DefaultTableModel(arreglob, campos) {
            public boolean isCellEditable(int row, int column) {

                if (column != 0) {
                    return true;
                } else {
                    return false;
                }

            }
        };

        model.isCellEditable(0, 0);
        scroll = new JScrollPane();
        tabla = new JTable(model);

        scroll.setViewportView(tabla);
        scroll.setColumnHeaderView(tabla.getTableHeader());
        scroll.setPreferredSize(new Dimension(1200, 400));

        b.setBackground(Color.white);
        b.add(panelCon, BorderLayout.NORTH);
        b.add(scroll, BorderLayout.CENTER);
        b.repaint();
        b.updateUI();
        repaint();

    }

    private void borrar() {
        int response = JOptionPane.showConfirmDialog(null, "Usted esta realizando una operacion, desea terminarla?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.NO_OPTION) {
            System.out.println("No button clicked");
        } else if (response == JOptionPane.YES_OPTION) {
            System.out.println("Yes button clicked");
            try {
                lblTotal.setText("Total :");
                BorderLayout layout = (BorderLayout) b.getLayout();
                b.remove(layout.getLayoutComponent(BorderLayout.CENTER));
                b.updateUI();
                b.repaint();
                b.setBackground(new Color(0, 151, 156));

                sur.setVisible(false);
                panelCon.setVisible(false);
                venta = false;
                compr = false;
                consulta = false;
            } catch (Exception ex) {
            }

        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.out.println("JOptionPane closed");
        }
    }

    private void generarTablaReporte() {
        String sentencia = "";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int num = 6;
        try {


            if (tipoConsulta == 1) {
                sentencia = "select noventa,codigo,nombre,ventas.precio,ventas.cantidad,ventas.fecha from ventas join(productos)"
                        + "where ventas.idproducto = productos.idproducto and ventas.fecha> '" + fechaInicio + "' and ventas.fecha <'" + fechaFin + "'"
                        + " order by (noventa);";
            } else if (tipoConsulta == 2) {
                sentencia = "select nocompra,codigo,nombre,compras.precio,compras.cantidad,compras.fecha from compras join(productos)"
                        + "where compras.idproducto = productos.idproducto and compras.fecha> '" + fechaInicio + "' and compras.fecha <'" + fechaFin + "'"
                        + " order by(nocompra);";

            } else if (tipoConsulta == 3) {
                sentencia = "select codigo,productos.nombre,preciocompra,precioventa,cantidad,categoria.nombre from productos join(categoria)"
                        + "where categoria.idcategoria=productos.categoria  ";

                for (int i = 0; i < cat.length; i++) {
                    if (i < cat.length - 1) {
                        sentencia += " and categoria.nombre='" + cat[i] + "'";
                    } else {
                        sentencia += " and categoria.nombre='" + cat[i] + "';";
                    }
                }
            }


            ArrayList<String> array = new ArrayList<String>();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");

            pst = con.prepareStatement(sentencia);
            rs = pst.executeQuery();
            String x = "";

            ResultSetMetaData meta = rs.getMetaData();

            campos = new String[num];



            while (rs.next()) {

                x = "";
                for (int i = 1; i <= num; i++) {
                    campos[i - 1] = meta.getColumnName(i);
                    rs.getObject(i);
                    System.out.print(rs.getObject(i) + "\t");
                    //array.add(arg0)
                    x += rs.getObject(i) + "*";
                }
                array.add(x);
                System.out.println();
            }




            String datos[] = array.toArray(new String[array.size()]);

            d = array.size();
            for (String t : datos) {
                System.out.println(t);
            }


            arreglob = new String[datos.length][num];


            for (int i = 0; i < datos.length; i++) {
                int j = 0;
                StringTokenizer tokens = new StringTokenizer(datos[i], "*");
                while (tokens.hasMoreTokens()) {
                    String s = tokens.nextToken();
                    arreglob[i][j] = s;
                    j++;
                }


            }

            for (String cam : campos) {
                System.out.print(cam + "  ");
            }

            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < num; j++) {
                    System.out.print(arreglob[i][j] + "  ");
                }
                System.out.println();
            }

            // new mostrardatos(arreglob,campos).setVisible(true);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
            }
        }

    }

    private void crearVentanaInventario() {
        frInventario = new JFrame();
        frInventario.setSize(800, 500);
        frInventario.setLocation(200, 100);
        frInventario.setLayout(null);
        frInventario.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblInventario.setFont(new Font("Segoe UI Semibold", 1, 24));
        lblInventario.setForeground(Color.BLUE);
        lblInventario.setBounds(10, 30, 450, 40);

        categorias();
//        String ategoria[]={"vfv","fv","cd","vrfr","vfsv","vfc","vv","vfv","fv","cd","vrfr","vfsv","vfc","vv",
//        "vfv","fv","cd","vrfr","vfsv","vfc","vv","vfv","fv","cd","vrfr","vfsv","vfc","vv","vfv","fv","cd","vrfr","vfsv","vfc","vv"};
//        
        ckInventario = new JCheckBox[categoria.length];
        int y = 80;
        int x = 50;
        for (int i = 0; i < categoria.length; i++) {
            ckInventario[i] = new JCheckBox(categoria[i]);
            ckInventario[i].setBounds(x, y, 100, 24);
            frInventario.add(ckInventario[i]);
            if (y < 300) {
                y += 35;
            } else {
                x += 150;
                y = 80;
            }
        }
        okInventario.setBounds(200, 30, 100, 24);
        cancelInventario.setBounds(400, 30, 100, 24);
        panelInventario.setLayout(null);
        panelInventario.setBackground(new Color(0, 151, 156));
        panelInventario.add(okInventario);
        panelInventario.add(cancelInventario);
        panelInventario.setBounds(0, 350, 800, 150);

        frInventario.add(panelInventario);
        frInventario.add(lblInventario);
        frInventario.setVisible(true);

        okInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int k = 0;
                for (int i = 0; i < ckInventario.length; i++) {
                    if (ckInventario[i].isSelected()) {
                        k++;
                    }
                }

                cat = new String[k];
                k = 0;
                for (int i = 0; i < ckInventario.length; i++) {
                    if (ckInventario[i].isSelected()) {
                        cat[k] = ckInventario[i].getText();
                        k++;
                    }
                }

                tipoConsulta = 3;
                //generarTablaReporte();
                dibujarConsulta();

                frInventario.dispose();
            }
        });


        cancelInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frInventario.dispose();
            }
        });
    }

    private void categorias() {

        ArrayList<String> array = new ArrayList<String>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");

            pst = con.prepareStatement("select nombre from categoria");
            rs = pst.executeQuery();
            while (rs.next()) {

                System.out.println(rs.getString(1));
                array.add(rs.getString(1));

            }



            categoria = array.toArray(new String[array.size()]);




        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    private void usuario(int use) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");

            String s = "select apaterno, amaterno,nombre,tipoUsuario from usuarios where idusuario=" + use + ";";
            System.out.println(s);
            pst = con.prepareStatement(s);
            rs = pst.executeQuery();
            while (rs.next()) {

                System.out.println(rs.getString(1) + rs.getString(2));
                usuario += rs.getString(1) + " ";
                usuario += rs.getString(2) + " ";
                usuario += rs.getString(3);
                idUsuario = rs.getInt(4);

            }

            System.out.println(usuario + " " + idUsuario);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    private void nuevo(int operacion) {
        if (!venta && !compr && !consulta) {

            model = new DefaultTableModel(compra, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {

                    if (column == 3) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };

            model.isCellEditable(0, 0);
            scroll = new JScrollPane();
            tabla = new JTable(model);

            scroll.setViewportView(tabla);
            scroll.setColumnHeaderView(tabla.getTableHeader());
            scroll.setPreferredSize(new Dimension(1200, 400));
            //lblTotal.setPreferredSize(new Dimension(1200,80));

            sur.setVisible(true);
            b.add(scroll, BorderLayout.CENTER);

            // b.add(lblTotal,BorderLayout.SOUTH);

            b.repaint();
            b.updateUI();
            repaint();
            if (operacion == 1) {
                venta = true;
            } else if (operacion == 2) {
                compr = true;
            }
        } else {
            borrar();
        }
    }

    private void realizarOperaciones() {
        String sentencia = "";
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> array2 = new ArrayList<String>();
        int canti[] = new int[model.getRowCount()];
        String nombreAr[] = new String[model.getRowCount()];
        String precioAr[] = new String[model.getRowCount()];
        
        if (venta) {
            sentencia = "select noventa from ventas group by noventa;";
            int noventa = 0;
            Connection c = null;
            PreparedStatement pst = null;
            ResultSet rs;
            try {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");
                pst = c.prepareStatement(sentencia);
                rs = pst.executeQuery();
                while (rs.next()) {

                    System.out.println(rs.getString(1));
                    noventa = rs.getInt(1);
                }

            } catch (Exception e) {
            } finally {
                try {
                    pst.close();
                    c.close();
                } catch (Exception ex) {
                }
            }

            noventa++;
            System.out.println("noventa" + noventa);

            sentencia = "insert into ventas(idproducto,precio,cantidad,noventa,fecha) values(";
            model.fireTableDataChanged();

            for (int i = 0; i < model.getRowCount(); i++) {

                System.out.println(model.getValueAt(i, 0) + "cantidad" + model.getValueAt(i, 3));
                array2.add((String) model.getValueAt(i, 0));
                String s = (String) model.getValueAt(i, 3);
                nombreAr[i] = (String) model.getValueAt(i, 1);
                precioAr[i] = (String) model.getValueAt(i, 2);
                
                canti[i] = Integer.parseInt(s);

            }

            listaIds(array2);
            //"Codigo", "Nombre producto", "Precio", "Cantidad"};


            for (int i = 0; i < model.getRowCount(); i++) {
                sentencia += id.get(i) + ",";
                for (int j = 2; j < model.getColumnCount(); j++) {
                    sentencia += model.getValueAt(i, j) + ",";

                }
                sentencia += noventa + ",now());";
                array.add(sentencia);
                sentencia = "insert into ventas(idproducto,precio,cantidad,noventa,fecha) values(";
            }

            sentencia = "update productos set cantidad =";

            for (int i = 0; i < id.size(); i++) {
                int z = ca.get(i) - canti[i];
                sentencia += z + " where idproducto=" + id.get(i) + ";";
                array.add(sentencia);
                sentencia = "update productos set cantidad =";
            }
            Connection con = null;
            Statement st = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");

                st = con.createStatement();
                con.setAutoCommit(false);
                for (int i = 0; i < array.size(); i++) {
                    System.out.println(array.get(i));
                    st.executeUpdate(array.get(i));
                }
                con.commit();

               
            } catch (Exception ex) {
                if (con != null) {
                    try {
                        con.rollback();
                        System.out.println(ex.getMessage());
                    } catch (SQLException ex1) {
                        System.out.println(ex1.getMessage());

                    }
                }
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }

                } catch (SQLException ex) {
                }
            }
            
            double total = 0;
            
            for(int i = 0; i< precioAr.length;i++)
            {
                double d = Double.parseDouble(precioAr[i]);
                double x= d* canti[i];
                System.out.println(x);
                total+=x;
            }
            
            
            
            System.out.println("total "+total);
            lblTotal.setText("Total : "+total);
            
             String recibido =  JOptionPane.showInputDialog(null,"Moto recibido");
            double r = Double.parseDouble(recibido);
           lblTotal.setText("Total : "+total+"  cambio : "+(r-total));
            
            
            new Lista(nombreAr,precioAr,canti,String.valueOf(total),usuario);

           // new Imprimir("operacion.txt");
            
            borrar();
        } else if (compr) {
            sentencia = "select nocompra from compras group by nocompra;";
            
            int noventa = 0;
            Connection c = null;
            PreparedStatement pst = null;
            ResultSet rs;
            try {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");
                pst = c.prepareStatement(sentencia);
                rs = pst.executeQuery();
                while (rs.next()) {

                    System.out.println(rs.getString(1));
                    noventa = rs.getInt(1);
                }

            } catch (Exception e) {
            } finally {
                try {
                    pst.close();
                    c.close();
                } catch (Exception ex) {
                }
            }

            noventa++;
            System.out.println("nocompra" + noventa);

            sentencia = "insert into compras(idproducto,precio,cantidad,nocompra,fecha) values(";
            model.fireTableDataChanged();

            for (int i = 0; i < model.getRowCount(); i++) {

                System.out.println(model.getValueAt(i, 0) + "cantidad" + model.getValueAt(i, 3));
                array2.add((String) model.getValueAt(i, 0));
                String s = (String) model.getValueAt(i, 3);
                canti[i] = Integer.parseInt(s);
                nombreAr[i] = (String) model.getValueAt(i, 1);
                precioAr[i] = (String) model.getValueAt(i, 2);
            }

            listaIds(array2);
            //"Codigo", "Nombre producto", "Precio", "Cantidad"};


            for (int i = 0; i < model.getRowCount(); i++) {
                sentencia += id.get(i) + ",";
                for (int j = 2; j < model.getColumnCount(); j++) {
                    sentencia += model.getValueAt(i, j) + ",";

                }
                sentencia += noventa + ",now());";
                array.add(sentencia);
                sentencia = "insert into compras(idproducto,precio,cantidad,nocompra,fecha) values(";
            }

            sentencia = "update productos set cantidad =";

            for (int i = 0; i < id.size(); i++) {
                int z = ca.get(i) + canti[i];
                sentencia += z + " where idproducto=" + id.get(i) + ";";
                array.add(sentencia);
                sentencia = "update productos set cantidad =";
            }
            Connection con = null;
            Statement st = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");

                st = con.createStatement();
                con.setAutoCommit(false);
                for (int i = 0; i < array.size(); i++) {
                    System.out.println(array.get(i));
                    st.executeUpdate(array.get(i));
                }
                con.commit();

               
            } catch (Exception ex) {
                if (con != null) {
                    try {
                        con.rollback();
                        System.out.println(ex.getMessage());
                    } catch (SQLException ex1) {
                        System.out.println(ex1.getMessage());

                    }
                }
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }

                } catch (SQLException ex) {
                }
            }
            
            double total = 0;
            
            for(int i = 0; i< precioAr.length;i++)
            {
                double d = Double.parseDouble(precioAr[i]);
                double x= d* canti[i];
                System.out.println(x);
                total+=x;
            }
            System.out.println("total "+total);
            lblTotal.setText("Total : "+total);
            
               String recibido =  JOptionPane.showInputDialog(null,"Moto otorgado");
            double r = Double.parseDouble(recibido);
           lblTotal.setText("Total : "+total+"  cambio a recibir  : "+(r-total));
            
            borrar();
        }
    }

    private void listaIds(ArrayList<String> codigos) {
        id = new ArrayList<String>();
        ca = new ArrayList<Integer>();
        Connection c = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "1234");


            c.setAutoCommit(false);
            for (int i = 0; i < codigos.size(); i++) {
                pst = c.prepareStatement("select idproducto,cantidad from productos where codigo='" + codigos.get(i) + "';");
                rs = pst.executeQuery();
                while (rs.next()) {

                    System.out.println(rs.getString(1));
                    id.add(rs.getString(1));
                    ca.add(rs.getInt(2));
                }
            }

            c.commit();

        } catch (Exception ex) {
            try {
                c.rollback();
                System.out.println(ex.getMessage());
            } catch (Exception exxxx) {
            }
        } finally {
            try {
                rs.close();
                pst.close();
                c.close();
            } catch (Exception exx) {
                System.out.println(exx.getMessage());
            }
        }
    }
}

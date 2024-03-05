/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MenuPrincipal;

import Practica01.Conexion;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static javax.swing.UIManager.getString;
import static javax.swing.UIManager.getInt;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Productos {
    
    int ID_PROD;
    String CODIGO_PROD;
    String SERIE_PROD;
    String CAT_PROD;
    String UM_PROD;
    String DES_PROD;
    double PRG_PROD;
    double PRT_PROD;
    String DIM_PROD;
    int STOCK_PROD;

    public int getID_PROD() {
        return ID_PROD;
    }

    public void setID_PROD(int ID_PROD) {
        this.ID_PROD = ID_PROD;
    }

    public String getCODIGO_PROD() {
        return CODIGO_PROD;
    }

    public void setCODIGO_PROD(String CODIGO_PROD) {
        this.CODIGO_PROD = CODIGO_PROD;
    }

    public String getSERIE_PROD() {
        return SERIE_PROD;
    }

    public void setSERIE_PROD(String SERIE_PROD) {
        this.SERIE_PROD = SERIE_PROD;
    }

    public String getCAT_PROD() {
        return CAT_PROD;
    }

    public void setCAT_PROD(String CAT_PROD) {
        this.CAT_PROD = CAT_PROD;
    }

    public String getUM_PROD() {
        return UM_PROD;
    }

    public void setUM_PROD(String UM_PROD) {
        this.UM_PROD = UM_PROD;
    }

    public String getDES_PROD() {
        return DES_PROD;
    }

    public void setDES_PROD(String DES_PROD) {
        this.DES_PROD = DES_PROD;
    }

    public double getPRG_PROD() {
        return PRG_PROD;
    }

    public void setPRG_PROD(double PRG_PROD) {
        this.PRG_PROD = PRG_PROD;
    }

    public double getPRT_PROD() {
        return PRT_PROD;
    }

    public void setPRT_PROD(double PRT_PROD) {
        this.PRT_PROD = PRT_PROD;
    }

    public String getDIM_PROD() {
        return DIM_PROD;
    }

    public void setDIM_PROD(String DIM_PROD) {
        this.DIM_PROD = DIM_PROD;
    }

    public int getSTOCK_PROD() {
        return STOCK_PROD;
    }

    public void setSTOCK_PROD(int STOCK_PROD) {
        this.STOCK_PROD = STOCK_PROD;
    }
    
    public void InsertarProducto(JTextField paramCB, JTextField paramSerie, JComboBox paramCat, JTextField paramUM, JTextArea paramDes, JTextField paramPRG, JTextField paramPRT, JTextField paramDIM, JTextField paramStock){
       
        setCODIGO_PROD(paramCB.getText());
        setSERIE_PROD(paramSerie.getText());
        setCAT_PROD(""+paramCat.getSelectedItem());
        setUM_PROD(paramUM.getText());
        setDES_PROD(paramDes.getText());
        double PRGDouble = Double.parseDouble(paramPRG.getText());
        setPRG_PROD(PRGDouble);
        double PRTDouble = Double.parseDouble(paramPRT.getText());
        setPRT_PROD(PRTDouble);
        setDIM_PROD(paramDIM.getText());
        int STOCKInt = Integer.parseInt(paramStock.getText());
        setSTOCK_PROD(STOCKInt);
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into productos (CODIGO_PROD, SERIE_PROD, CAT_PROD, UM_PROD, DES_PROD, PRG_PROD, PRT_PROD, DIM_PROD, STOCK_PROD) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            
            cs.setString(1, getCODIGO_PROD());
            cs.setString(2, getSERIE_PROD());
            cs.setString(3, getCAT_PROD());
            cs.setString(4, getUM_PROD());
            cs.setString(5, getDES_PROD());
            cs.setDouble(6, getPRG_PROD());
            cs.setDouble(7, getPRT_PROD());
            cs.setString(8, getDIM_PROD());
            cs.setInt(9, getSTOCK_PROD());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se agregó correctamen el producto");
                    
        } catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "No se pudo agregar el producto, error: "+ex.toString());
            
        }
    }
    public void MostrarProductos(JTable paramTablaTotalProductos){
        
        Conexion objetoConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalProductos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        modelo.addColumn("ID");
        modelo.addColumn("CODIGO DE BARRAS");
        modelo.addColumn("SERIE");
        modelo.addColumn("CATEGORIA");
        modelo.addColumn("UNIDAD DE MEDIDA");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("PRECIO GENERAL");
        modelo.addColumn("PRECIO TECNICO");
        modelo.addColumn("DIMENSIONES");
        modelo.addColumn("CANTIDAD");
        
        paramTablaTotalProductos.setModel(modelo);
        
        sql="select * from productos";
        
        String[] datos = new String [10];
        Statement st;
        
        try{
            st= objetoConexion.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                datos[8]=rs.getString(9);
                datos[9]=rs.getString(10);
                
                modelo.addRow(datos);
                
                paramTablaTotalProductos.setModel(modelo);
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No se pudo mostrar la tabla, error: "+ex.toString());
        }
        
    }
    
    
    /*public void SeleccionarProductos(JTable paramTablaProductos, JTextField paramCB, JTextField paramSerie, JComboBox paramCat, JTextField paramUM, JTextArea paramDes, JTextField paramPRG, JTextField paramPRT, JTextField paramDIM, JTextField paramStock){
        
        try{
            int fila = paramTablaProductos.getSelectedRow();
            if(fila >=0){
                paramCB.setText(paramTablaProductos.getValueAt(fila,0));
            }
        }catch(Exception ex){
            
        }
    }*/
}

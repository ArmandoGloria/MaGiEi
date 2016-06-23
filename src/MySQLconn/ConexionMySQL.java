/*
 * Clase y funciones de conexion a la base de datos
 */

package MySQLconn;

import Resources.MessageBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

//import javax.swing.JFrame;

//import Resources.GlobalClass1;

public class ConexionMySQL
{
    private String NombreBD = "magiei_db";
    private String DireccionBD = "jdbc:mysql://148.240.219.197/" + this.NombreBD;
    private String UsuarioBD = "InteliGeneDatabases";
    private String ClaveBD = "Databaseconn7";
    public String strQueryMySQL;
    public Connection mySqlConn;
    private Statement Instruccion;
    private ResultSet Resultado;
    
    public PreparedStatement ps = null;
    
//    GlobalClass1 global;
    

    public ConexionMySQL()
    {
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver");
//	    Class.forName("com.mysql.jdbc.Driver");
	    
            this.mySqlConn = DriverManager.getConnection(this.DireccionBD,this.UsuarioBD,this.ClaveBD);
            this.Instruccion = this.mySqlConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch(SQLException SQLE)
        {
		MessageBox.show("Error","","ERROR EN LA CONEXION CON BD\nERROR : " + SQLE.getMessage(),Alert.AlertType.ERROR);
           // JOptionPane.showMessageDialog(null,"ERROR EN LA CONEXION CON BD\nERROR : " + SQLE.getMessage());
        }
        catch(ClassNotFoundException CNFE)
        {
            MessageBox.show("Error","","ERROR DRIVER BD JAVA\nERROR : " + CNFE.getMessage(),Alert.AlertType.ERROR);
           //JOptionPane.showMessageDialog(null,"ERROR DRIVER BD JAVA\nERROR : " + CNFE.getMessage());
        }
    }
    
    public boolean updateTable(String SentenciaSQL)
    {   
	try
        {
            this.Instruccion.executeUpdate(SentenciaSQL);
            return true;
        }
        catch (SQLException SQLE)
        {
            return false;
        }
    }    

    
    public void InsertInsertar(String SentenciaSQL)
    {


        try
        {
            
		mySqlConn.setAutoCommit(false);
		ps = mySqlConn.prepareStatement(SentenciaSQL);
	
        }
        catch (SQLException SQLE)
        {
            MessageBox.show("Error","","ERROR AL INSERTAR EN LA BD \n ERROR : " + SQLE.getMessage(),Alert.AlertType.ERROR);
           //JOptionPane.showMessageDialog(null,"ERROR AL INSERTAR EN LA BD \n ERROR : " + SQLE.getMessage());
        }
    }
    
    public void InsertGuardar(String SentenciaSQL)
    {


        try
        {
            
		 ps.executeUpdate();
      mySqlConn.commit();
    } 
        catch (SQLException SQLE)
        {
            MessageBox.show("Error","","ERROR AL INSERTAR EN LA BD \n ERROR : " + SQLE.getMessage(),Alert.AlertType.ERROR);
           //JOptionPane.showMessageDialog(null,"ERROR AL INSERTAR EN LA BD \n ERROR : " + SQLE.getMessage());
        }finally {
		try {
			ps.close();
		} catch (SQLException ex) {
			Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
		}

	
        }
    }

    public void UpdateModificar(String SentenciaSQL)
    {
        this.strQueryMySQL = SentenciaSQL;

        try
        {
            this.Instruccion.executeUpdate(this.strQueryMySQL);
//            MessageBox.show("DB","LA TABLA SE MODIFICO CON EXITO A LA BD",JOptionPane.INFORMATION_MESSAGE);
           //JJOptionPane.showMessageDialog(null,"LA TABLA SE MODIFICO CON EXITO A LA BD");
        }
        catch (SQLException SQLE)
        {
            MessageBox.show("Error","","ERROR AL MODIFICAR LA TABLA DE LA BD \n ERROR : " + SQLE.getMessage(),Alert.AlertType.ERROR);
           //JOptionPane.showMessageDialog(null,"ERROR AL MODIFICAR LA TABLA DE LA BD \n ERROR : " + SQLE.getMessage());
        }
    }
/*
    public void DeleteEliminar(String SentenciaSQL)
    {
        this.strQueryMySQL = SentenciaSQL;

        try
        {
            this.Instruccion.executeUpdate(this.strQueryMySQL);
            JOptionPane.showMessageDialog(null,"LA TABLA SE ELIMINO CON EXITO A LA BD");
        }
        catch (SQLException SQLE)
        {
            JOptionPane.showMessageDialog(null,"ERROR AL ELIMINAR LA TABLA DE LA BD \n ERROR : " + SQLE.getMessage());
        }
    }*/
    

    public DefaultTableModel GetTable(String SentenciaSQL)
    {
        this.strQueryMySQL = SentenciaSQL;
	
	DefaultTableModel table= new DefaultTableModel();        
         AbstractTableModel table2; 
	
        try
        {
	     
	    
	ResultSet set = Instruccion.executeQuery(this.strQueryMySQL);
        ResultSetMetaData metaData = set.getMetaData();
        int totalColumn = metaData.getColumnCount();
        Object[] dataRow = new Object[totalColumn];
        if(set!= null)
        {
            for(int i=1;i<=totalColumn;i++)
            {
                table.addColumn(metaData.getColumnName(i));
		//table2.addColumn(metaData.getColumnName(i));
            }
            while(set.next())
            {
                for(int i=1;i<=totalColumn;i++)
                {
			dataRow[i-1] = set.getObject(i);
		   
                }
                table.addRow(dataRow);
            }

        }
	    
        }
        catch (SQLException SQLE)
        {
            MessageBox.show("Error","","ERROR AL CARGAR LOS DATOS DE LA BD \n ERROR : " + SQLE.getMessage(),Alert.AlertType.ERROR);
           //JOptionPane.showMessageDialog(null,"ERROR AL CARGAR LOS DATOS DE LA BD \n ERROR : " + SQLE.getMessage());
        }

        return table;
    }
    
    public void CerrarConexion(){
	try{
		this.mySqlConn.close();
	}
	catch(SQLException SQLE)
        {
            MessageBox.show("Error","","ERROR AL CARGAR LOS DATOS DE LA BD \n ERROR : " + SQLE.getMessage(),Alert.AlertType.ERROR);
           //JOptionPane.showMessageDialog(null,"ERROR AL CARGAR LOS DATOS DE LA BD \n ERROR : " + SQLE.getMessage());
	}
    }
    
    
//    public static void llenarInfoFromMySQL(Connection Conmexion, ObserbableList<String>){
//	    try{
//		    
//	    }
//	    catch(){
//		    
//	    }
//    }
    
    
    
    
    
    
    
    
    
    
//    protected void  MessageBox.show(String Titulo,String Mensaje,Integer Tipo)
//    {
//	   // String backupDir = "/Users/al/backups";
//     
//	// create a jframe
//	JFrame frame = new JFrame("JOptionPane showMessageDialog example");
//
//	// show a joptionpane dialog using showMessageDialog
//	JOptionPane.showMessageDialog(frame,
//        Titulo + ": '" + Mensaje + "'.",
//        Titulo,
//        Tipo);//JOptionPane.INFORMATION_MESSAGE);
//    
//	frame.dispose();
//    }
    
   
    
    
}

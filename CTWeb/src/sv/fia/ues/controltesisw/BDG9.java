package sv.fia.ues.controltesisw;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDG9 {
	private static final String[]camposAlumno = new String [] {"carnet","nombre","apellido","sexo", "direccion","telefono","email","idGrupo"};
	private static final String[]camposMaestro = new String [] {"idDocente","nombre","apellido","sexo", "direccion","telefono","email","dui"};
	private static final String[]camposGrupo=new String[]{"idgrupo","idDocente","nomgrupo"};
	private static final String[]camposPerfil=new String[]{"idperfil","idtrabajodegraduacion","idgrupo","descripciondeltema","objetivoperfil","alcanzesperfil","limitacioneperfil","resolusiondejuntadirectiva","temadeasignado"};
	private static final String[]camposEspecialidad=new String[]{"idespecialidad","nomespecialidad"};
	private static final String[]camposEtapa=new String[]{"idEtapa","fechaDeInicioEtapa","fechaDeFinalizacionEtapa"};
	private static final String[]camposTrabjoGraduacion=new String[] {"idtrabajodegraduacion", "tema", "fechadeinicio", "fechadefinalizacion"};
	private static final String[] camposNota = {"idetapa","carnet","notaalumno"};
	private static final String[]camposBitacora = new String [] {"idbitacora","idtrabajodegraduacion"};
	private static final String[]camposControlBitacora=new String[]{"carnet","idbitacora","idcontrol","asistencia","participasion","descripciondelorealizado"};
	private static final String[]camposEspDocente=new String[]{"idDocente","idespecialidad"};
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	//contructor
	public BDG9(Context ctx) {
	this.context = ctx;
	DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
	private static final String BASE_DATOS = "grupo0911.s3db";
	private static final int VERSION = 1;
	public DatabaseHelper(Context context) {
	super(context, BASE_DATOS, null, VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	try{
		//tablas de la base de datos
		
		db.execSQL("create table ETAPA (IDETAPA  VARCHAR(8) not null,FECHADEINICIOETAPA  VARCHAR(10)  not null,FECHADEFINALIZACIONETAPA VARCHAR(10)  not null,primary key (IDETAPA));");
		
		db.execSQL("create table ALUMNO (CARNET char(7) not null,NOMBRE char(40)not null,APELLIDO char(40)not null,SEXO VARCHAR(1) not null,DIRECCION char(100)not null,TELEFONO char(8)not null,EMAIL char(40)not null,IDGRUPO char(7)not null, primary key (CARNET));");
		
		db.execSQL("create table NOTA(IDETAPA char(8)not null,CARNET char(7) not null,NOTAALUMNO FLOAT(3) not null,primary key (IDETAPA,CARNET));");
	
		
		//triggers//13 triggers fk 
		
		db.execSQL("CREATE TRIGGER fk_nota_alumno BEFORE INSERT ON NOTA FOR EACH ROW BEGIN SELECT CASE WHEN((SELECT CARNET FROM ALUMNO WHERE CARNET=NEW.CARNET)IS NULL)THEN RAISE(ABORT,'No existe el alumno')END;END");
		
		db.execSQL("CREATE TRIGGER fk_nota_Etapa BEFORE INSERT ON NOTA FOR EACH ROW BEGIN SELECT CASE WHEN((SELECT IDETAPA FROM ETAPA WHERE IDETAPA=NEW.IDETAPA)IS NULL)THEN RAISE(ABORT,'No existe la etapa')END;END");
		
		
		
	  	     //triggers DELETE
		  
	    db.execSQL("CREATE TRIGGER dl_alumno_nota AFTER DELETE ON ALUMNO FOR EACH ROW   begin    delete from NOTA where CARNET=OLD.CARNET;  end");
		
	    db.execSQL("CREATE TRIGGER dl_etapa_nota AFTER DELETE ON ETAPA FOR EACH ROW   begin     delete from NOTA where IDETAPA=OLD.IDETAPA;  end");
	    
	  
	}catch(SQLException e){
	e.printStackTrace();
	}
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	}
	}
	
	public void abrir() throws SQLException{
	db = DBHelper.getWritableDatabase();
	return;
	}
	public void cerrar(){
	DBHelper.close();
	}
	
	
	
	public String insertar(Alumno alumno){
		String regInsertados="Numero de Registro Insertado =";
		long contador=-2;
		ContentValues con=new ContentValues();
		con.put("carnet",alumno.getCarnet());
		con.put("nombre", alumno.getNombre());
		con.put("apellido",alumno.getApellido());
		con.put("sexo",alumno.getSexo());
		con.put("telefono",alumno.getTelefono());
		con.put("direccion",alumno.getDireccion());
		con.put("email",alumno.getEmail());
		
		
		contador=db.insert("alumno",null,con);
		regInsertados=regInsertados+contador+" Correctamente";
		if(contador==-1 || contador==0)
			regInsertados="Error al Insertar el Registro, Registro duplicado, Verificar Datos ";
		
		
		return regInsertados;
	}
	public String eliminar(Alumno alumno){
		String id[]={alumno.getCarnet()};
		long contador=0;
		String regEliminado="Nota:En caso de que el Alumno posea Registros de Control de Bitacora y de Notas tambien se Eliminaran,Registro eliminado Correctamente...";
		
		contador+=db.delete("alumno","carnet=?",id);
		if(contador==-1 || contador==0)
			regEliminado="Verifique el Carnet del Alumno,No se encontro ningun registro a eliminar..."+contador;
		
		return regEliminado;
	}
	public Alumno consultar(String carnet){
		
		String id[]={carnet};
		Cursor cursor=db.query("alumno",camposAlumno,"carnet=?", id, null,null, null);
		if(cursor.moveToFirst()){
			Alumno alumno=new Alumno();
			alumno.carnet=cursor.getString(0);
			alumno.nombre=cursor.getString(1);
			alumno.apellido=cursor.getString(2);
			alumno.sexo=cursor.getString(3);
			alumno.direccion=cursor.getString(4);
			alumno.telefono=cursor.getString(5);
			alumno.email=cursor.getString(6);
		
			return alumno;
		}else{
			return null;
		}
		
	}
	public String actualizar(Alumno alumno){
		
		String id[]={alumno.getCarnet()};
		String r;
		
		ContentValues con=new ContentValues();
		con.put("nombre",alumno.getNombre());
		con.put("apellido",alumno.getApellido());
		con.put("sexo", alumno.getSexo());
		con.put("direccion",alumno.getDireccion());
		con.put("telefono", alumno.getTelefono());
		con.put("email",alumno.getEmail());
		
		try{
		long cont=db.update("alumno", con,"carnet=?",id);
		r="Registro Actualizado Correctamente";
		if(cont==0||cont==-1)
			r="Verifique que el carnet este Correcto";
		
		}
		catch(SQLiteConstraintException e){
			r="Verifique el ID del Grupo que este Referenciando ,puede que no Exista...Intentelo de Nuevo";
		}
		return r;

	}
	
	
//ETAPA

public String insertarE(Etapa etapa){
	
	String regInsertados="Registro Insertado Correctamnete, Nº= ";
	long contador=0;
	ContentValues TG = new ContentValues();
	TG.put("idEtapa", etapa.getIdEtapa());
	TG.put("fechaDeInicioEtapa", etapa.getFechaDeInicioEtapa());
	TG.put("fechaDeFinalizacionEtapa", etapa.getFechaDeFinalizacionEtapa());
	contador=db.insert("etapa", null, TG);
	regInsertados=regInsertados+contador;
	if(contador==-1 || contador==0)
	    regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
	
	return regInsertados;
}

public String actualizarEtapa(Etapa etapa){
	
		String[] id = {etapa.getIdEtapa()};
		ContentValues cv = new ContentValues();
		cv.put("fechaDeInicioEtapa", etapa.getFechaDeInicioEtapa());
		cv.put("fechaDeFinalizacionEtapa", etapa.getFechaDeFinalizacionEtapa());
		long contador=db.update("etapa", cv, " idEtapa = ?", id);
		String resultado="Registro Actualizado Correctamente";
		if(contador==-1 || contador==0)
		    resultado= "Error al Actualizar el registro,El ID de la Etapa  No existe,intentelo de nuevo";
		
		return resultado;
	}

public Etapa consultarEtapa(String idEtapa){
	String[] id = {idEtapa};
	Cursor cursor = db.query("etapa", camposEtapa, "idEtapa = ?", id, null, null, null);
	if(cursor.moveToFirst()){
	
		Etapa E = new Etapa();
	E.setIdEtapa(cursor.getString(0));
	E.setFechaDeInicioEtapa(cursor.getString(1));
	E.setFechaDeFinalizacionEtapa(cursor.getString(2));
	return E;
	}else
	return null;
	
	}

public String eliminarEtapa(String ide){
	String []idet={ide};
	int contador=0;
	contador+=db.delete("etapa","idetapa=?", idet);
	String resultado="Registro Eliminado Correctamente";
	if(contador==-1 || contador==0)
	    resultado= "Error al Eliminar el registro,El ID de la Etapa  No existe";
	
	return resultado;
}



//parte del tigre
public String insertarNota(Nota nota){
	
	String re ="Nota Insertada Correctamente Numero="; //la nota que retornara
	ContentValues contenedor= new ContentValues();
	contenedor.put("idetapa",nota.getIdEtapa());
	contenedor.put("carnet",nota.getCarnet());
	contenedor.put("notaAlumno",nota.getNotaalumno());
	long contador=0;
	Etapa e=this.consultarEtapa(nota.getIdEtapa());
	Alumno a=this.consultar(nota.getCarnet());
	if(a==null)
	{     if(e==null)
			re="Error,Verificar Ambos IDs(ID Alumno  y ID de la Etapa),Los Datos Referenciados No Existen";
		  else
			re="Error,Verificar ID del Alumno ";
	}
else{
	if(e==null)
		re="Error,Verificar El ID de la Etapa";
	else{
		contador=db.insert("nota",null, contenedor);
		re+=contador;
		if(contador==-1 || contador==0)
			re="error al ingresar el registro, registro duplicado verificar su ingreso";
	
	   }
}
	
	return re;
	

}

public Nota consultarNota(String idEtapa, String Carnet) {
	String[] id = {idEtapa, Carnet};
	
	Cursor cursor = db.query("nota", camposNota,"idEtapa =? AND carnet =? ", id, null, null, null);
	
	if(cursor.moveToFirst()){
	Nota nota = new Nota();
	nota.setIdEtapa(cursor.getString(0));
	nota.setCarnet(cursor.getString(1));
	nota.setNotaalumno(cursor.getFloat(2));
	return nota;
	}
	else
	return null;
	
}
public String actualizarNota(Nota nota){
	String[] id = {nota.getIdEtapa(), nota.getCarnet()};
	String re="Actualizacion Correctamente";
	ContentValues cv = new ContentValues();
	cv.put("notaAlumno", nota.getNotaalumno());
	Etapa e=this.consultarEtapa(nota.getIdEtapa());
	Alumno a=this.consultar(nota.getCarnet());
	if(a==null)
	{     if(e==null)
			re="Error,Verificar Ambos IDs(ID Alumno  y ID de la Etapa),Los Datos Referenciados No Existen";
		  else
			re="Error,Verificar ID del Alumno ";
	}
else{
	if(e==null)
		re="Error,Verificar El ID de la Etapa";
	else
		db.update("nota", cv, "idEtapa = ? AND Carnet = ?" , id);	   
}
	
	return re;
}

public String eliminarNota(Nota nota){
String regAfectados="Eliminacion Correctamente";
int val=0;
String where="idEtapa='"+nota.getIdEtapa()+"'";
where=where+" AND Carnet='"+nota.getCarnet()+"'";
val+=db.delete("nota", where, null);
if(val==-1 || val==0)
regAfectados="Error,Verifique bien los datos,No existe la Nota para los datos del Alumno referenciado en la Etapa";

return regAfectados; 
} 


public String llenarBDCarnet()	{

final String VETidEtapa[]={"Etapa001", "Etapa002", "Etapa003"};
final String VETfechaDeInicioEtapa[]={"01-05-2014", "02-06-2014", "03-07-2014"};
final String VETfechaDeFinalizacionEtapa[]={"01-06-2014", "02-07-2014", "03-08-2014"};

final String VAcarnet[]={"ML11052","CR05074","RM11010","MC09130","CO05014"};
final String VAnombre[]={"Josue Alexander", "Jorge Gilberto", "Melvin Rene","Salvador Vladymir","Wiguin Antonio"};
final String VAapellido[]={"Mejia Lopez", "Cardoza Rodriguez", "River Machado","Martinez Chicas","Campo Orellana"};
final String VAsexo[]={"M" ,"M", "M","M","M"};
final String VAdireccionAlumno[]={"Sensuntepeque Cabanas, col el centro #3", "Av.Jerusalen #33b, San Salvador", "Barrio El Centro #15, San Miguel", "Quezaltepeque, Av. 3de mayo #28","Colonia la Arboledas Casa #20, San Salvador"};
final String VAtelefonoAlumno[]={"77800001", "77800002", "77800003","77800005","78563392"};
final String VAemailAlumno[]={"ML11052@ues.edu.sv", "CR05074@ues.edu.sv", "RM11010@ues.edu.sv", "MC09130@ues.edu.sv","CO05014@ues.edu.sv"};
final String VAidgrupo[]={"GP00001", "GP00001", "GP00001", "GP00001","GP00001"};



final String VNcarnet[]={"ML11052","CR05074","RM11010","MC09130","CO05014"};
final String VNetapa[]={"Etapa001", "Etapa001", "Etapa001","Etapa001","Etapa001"};
final float VNnota[]={8,7,9,9,(float) 8.5};

     abrir();

     db.execSQL( "DELETE FROM alumno" );
     
     db.execSQL("DELETE FROM nota" );
 
     db.execSQL("DELETE FROM etapa" );
  
		
		Etapa et = new Etapa();
		for (int i = 0; i <3; i++) {
			et.setIdEtapa(VETidEtapa[i]);
			et.setFechaDeInicioEtapa(VETfechaDeInicioEtapa[i]);
			et.setFechaDeFinalizacionEtapa(VETfechaDeFinalizacionEtapa[i]);
		this.insertarE(et);
		}

		Alumno aA = new Alumno();
		for (int i = 0; i <5 ; i++) {
			aA.setCarnet(VAcarnet[i]);
			aA.setNombre(VAnombre[i]);
			aA.setApellido(VAapellido[i]);
			aA.setSexo(VAsexo[i]);
			aA.setDireccion(VAdireccionAlumno[i]);
			aA.setTelefono(VAtelefonoAlumno[i]);
			aA.setEmail(VAemailAlumno[i]);
			this.insertar(aA);
		}
	
		
		 Nota n = new Nota();
			for(int i=0;i<5;i++){
			   n.setCarnet(VNcarnet[i]);
			   n.setIdEtapa(VNetapa[i]);
			   n.setNotaalumno(VNnota[i]);
			   this.insertarNota(n);
			}
		
			
		
	
		cerrar();
		
		return "Guardo Correctamente";


}	








}

package sv.fia.ues.controltesisw;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NotaMenuActivity extends ListActivity {

	String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro","Actualizar Registro"};
	String[]activities={"NotaInsertarActivity","NotaEliminarActivity","NotaConsultarActivity","NotaActualizarActivity"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));
		
	}
  @Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);

	String nombreValue=activities[position];
	
	try{
		
		Class<?>gestorAlumno=Class.forName("sv.fia.ues.controltesisw."+nombreValue);
		Intent inte=new Intent(this,gestorAlumno);
		this.startActivity(inte);			
	}
	catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	
	
}


}

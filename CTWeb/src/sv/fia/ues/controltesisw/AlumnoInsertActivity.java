package sv.fia.ues.controltesisw;

import java.util.ArrayList;
import java.util.List;



import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AlumnoInsertActivity extends Activity {
      
	 private Spinner spinner;
	    private List<String> list;
	  //importante con sexo se manejara de una forma diferente con un spinner  
	    private EditText editCarnet;
	    private EditText editNombre;
	    private EditText editApellido;
	    private EditText editDireccion;
	    private EditText editTelefono;
	    private EditText editEmail;
	    private String sexo;
	    
	    BDG9 helper;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_alumno_insert);
			datosPorDefecto();
			
			helper=new BDG9(this);
		editCarnet=(EditText)findViewById(R.id.editCarnet);
		editNombre=(EditText)findViewById(R.id.editNombre);
		editApellido=(EditText)findViewById(R.id.editApellido);
		editDireccion=(EditText)findViewById(R.id.editDireccion);
		editTelefono=(EditText)findViewById(R.id.editTelefono);
		editEmail=(EditText)findViewById(R.id.editText1);
		
			
			
			
		}
		public void insertarAlumno(View v){
			
			
			
			Alumno alumno=new Alumno();
			String regResultado;
			alumno.carnet=editCarnet.getText().toString();
			alumno.nombre=editNombre.getText().toString();
			alumno.apellido=editApellido.getText().toString();
			alumno.direccion=editDireccion.getText().toString();
			alumno.telefono=editTelefono.getText().toString();
			alumno.email=editEmail.getText().toString();
			alumno.sexo=sexo;
			
			if(verificarDatos(alumno)){
				helper.abrir();
				regResultado=helper.insertar(alumno);
				helper.cerrar();	
				TextView mensaje=new TextView(this);
				mensaje.setText(regResultado);
				Dialog d=new Dialog(this);
				d.setTitle("Resultado de la Operacion");
				d.setContentView(mensaje);
				d.show();
				}else
					Toast.makeText(this,"Error al ingresar los datos, todos los campos deben estar completados",Toast.LENGTH_LONG).show();
			
			
			}
		
		@SuppressLint("NewApi")
		public boolean verificarDatos(Alumno alumno){
			if(alumno.getCarnet().isEmpty()|| alumno.getNombre().isEmpty() || alumno.getApellido().isEmpty()|| alumno.getDireccion().isEmpty() || alumno.getTelefono().isEmpty() || alumno.sexo.equals("seleccione" ) ){
				return false;
			}else
				return true;
		}
		public void limpiarTexto(View v){
			editApellido.setText("");
			editCarnet.setText("");
			editDireccion.setText("");
			editEmail.setText("");
			editNombre.setText("");
			editTelefono.setText("");
		
			
		}
		private void datosPorDefecto() {
			spinner=(Spinner)findViewById(R.id.spinner);
			list=new ArrayList<String>();
			list.add("seleccione");
			list.add("M");
			list.add("F");
			ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
			//Establece el recurso de diseño para crear el menú desplegable vistas. 
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spinner.setAdapter(adapter);
			
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
					sexo=arg0.getItemAtPosition(position).toString();
					Toast.makeText(arg0.getContext(),"seleccionado: "+arg0.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
					
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alumno_insert, menu);
		return true;
	}

}

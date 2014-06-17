package sv.fia.ues.controltesisw;


import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlumnoConsultarActivity extends Activity {
	 private EditText editCarnet;
	    private EditText editNombre;
	    private EditText editApellido;
	    private EditText editDireccion;
	    private EditText editTelefono;
	    private EditText editEmail;
	    private EditText editSexo;
	    private EditText editIdGrupo;

		BDG9 helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alumno_consultar);
	
		
	 editCarnet=(EditText)findViewById(R.id.editCarnet);
	 editNombre=(EditText)findViewById(R.id.editNombre);
	 editApellido=(EditText)findViewById(R.id.editApellido);
	 editSexo=(EditText)findViewById(R.id.editSexo);
	 editDireccion=(EditText)findViewById(R.id.editDireccion);
	 editEmail=(EditText)findViewById(R.id.editEmail);
	 editTelefono=(EditText)findViewById(R.id.editTelefono);

		helper=new BDG9(this);
	}
	public void consultarAlumno(View v){
		String carnet=editCarnet.getText().toString();
		if(verificarDatos(carnet)){
			helper.abrir();
			Alumno alumno=helper.consultar(carnet);
			helper.cerrar();
		if(alumno==null){
			TextView mensaje=new TextView(this);
			mensaje.setText("El Alumno con Carnet: "+editCarnet.getText().toString()+" No fue encontrado, Intentelo de Nuevo...");
			Dialog d=new Dialog(this);
			d.setTitle("Resultado de la Operacion");
			d.setContentView(mensaje);
			d.show();
		
		}else{
			editNombre.setText(alumno.getNombre());
			editApellido.setText(alumno.getApellido());
			editSexo.setText(alumno.getSexo());
			editDireccion.setText(alumno.getDireccion());
			editEmail.setText(alumno.getEmail());
			editTelefono.setText(alumno.getTelefono());
		}}else{
			Toast.makeText(this,"Error al ingresar los datos, todos los campor deben estar completados",Toast.LENGTH_LONG).show();
		}
	}
	
	public boolean verificarDatos(String carnet){
		if(carnet==null){
			return false;
		}else return true;
	}
	public void limpiarTexto(View v){
		editCarnet.setText("");
		editNombre.setText("");
		editApellido.setText("");
		editSexo.setText("");
		editDireccion.setText("");
		editEmail.setText("");
		editTelefono.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alumno_consultar, menu);
		return true;
	}

}

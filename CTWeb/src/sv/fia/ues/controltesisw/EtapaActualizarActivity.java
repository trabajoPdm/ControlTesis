package sv.fia.ues.controltesisw;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EtapaActualizarActivity extends Activity {

	EditText editidEtapa;
	EditText editfechaDeInicioEtapa;
	EditText editfechaDeFinalizacionEtapa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_etapa_actualizar);
		//helper = new ControlBDCarnet(this);
				editidEtapa = (EditText) findViewById(R.id.editIDEtapa);
				editfechaDeInicioEtapa = (EditText) findViewById(R.id.editFechaDeInicioEtapa);
				editfechaDeFinalizacionEtapa = (EditText) findViewById(R.id.editFechaDeFinalizacionEtapa);
						
	}

	public void actualizarEtapa(View v) {
		Etapa e = new Etapa();
		e.setIdEtapa(editidEtapa.getText().toString());
		e.setFechaDeInicioEtapa(editfechaDeInicioEtapa.getText().toString());
		e.setFechaDeFinalizacionEtapa(editfechaDeFinalizacionEtapa.getText().toString());
		
		if(verificarDatos(e))
		{

		TextView mensaje=new TextView(this);
		//mensaje.setText(resultado);
		Dialog d=new Dialog(this);
		d.setTitle("Resultado de la Operacion");
		d.setContentView(mensaje);
		d.show();
		}else
			Toast.makeText(this,"Error al ingresar los datos, todos los campos deben estar completados",Toast.LENGTH_LONG).show();

		}
		
	public void limpiarTexto(View v){
		editidEtapa.setText("");
		editfechaDeFinalizacionEtapa.setText("");
		editfechaDeInicioEtapa.setText("");
	}
	@SuppressLint("NewApi")
	public boolean verificarDatos(Etapa et){
		if(et.getIdEtapa().isEmpty()||et.getFechaDeInicioEtapa().isEmpty()|| et.getFechaDeFinalizacionEtapa().isEmpty()){
			return false;
		}else
			return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.etapa_actualizar, menu);
		return true;
	}

}

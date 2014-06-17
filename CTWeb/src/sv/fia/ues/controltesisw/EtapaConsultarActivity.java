package sv.fia.ues.controltesisw;


import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EtapaConsultarActivity extends Activity {

	EditText editidEtapa;
	EditText editfechaDeInicioEtapa;
	EditText editfechaDeFinalizacionEtapa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_etapa_consultar);
		editidEtapa = (EditText) findViewById(R.id.editIDEtapa);
		editfechaDeInicioEtapa = (EditText) findViewById(R.id.editFechaDeInicioEtapa);
		editfechaDeFinalizacionEtapa = (EditText) findViewById(R.id.editFechaDeFinalizacionEtapa);
		
			
	}

	
	public void consultarEtapa(View v) {
		
		Etapa e =null;
		if(e == null)
		{
		String regInsertados="Etapa con id: " +editidEtapa.getText().toString() +" No fue encontrada";
		TextView mensaje=new TextView(this);
		mensaje.setText(regInsertados);
		Dialog d=new Dialog(this);
		d.setTitle("Resultado de la Operacion");
		d.setContentView(mensaje);
		d.show();
		}
		else{
		editfechaDeInicioEtapa.setText(e.getFechaDeInicioEtapa());
		editfechaDeFinalizacionEtapa.setText(e.getFechaDeFinalizacionEtapa());
		}
		}
	
	public void limpiarTexto(View v){
		editidEtapa.setText("");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.etapa_consultar, menu);
		return true;
	}

}

package sv.fia.ues.controltesisw;


import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NotaConsultarActivity extends Activity {
	EditText idEtapa;
	EditText Carnet;
	EditText notaAlumno;
	BDG9 helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota_consultar);
			
		helper = new BDG9(this);
	idEtapa = (EditText) findViewById(R.id.editidEtapa);
	Carnet = (EditText) findViewById(R.id.editidCarnet);
	notaAlumno = (EditText) findViewById(R.id.editnotaAlumno);

	}
	public void consultarNota(View v) {

	helper.abrir();
	Nota nota = helper.consultarNota(idEtapa.getText().toString(),Carnet.getText().toString());
	helper.cerrar();
	if(nota==null){
		
		TextView mensaje=new TextView(this);
	    mensaje.setText("Error en la Busqueda,la Nota De la etapa: "+idEtapa.getText().toString()+"Del Alumno Con Carnet:"+Carnet.getText().toString()+" No fue se encuentra");
	    Dialog d=new Dialog(this);
	    d.setTitle("Resultado de la Operacion");
	    d.setContentView(mensaje);
	    d.show();
	}else{
		
		notaAlumno.setText(String.valueOf(nota.getNotaalumno()));
	}


	//Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
	}
	public void limpiarTexto(View v) {
	Carnet.setText("");
	idEtapa.setText("");
	Carnet.setText("");
	notaAlumno.setText("");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nota_consultar, menu);
		return true;
	}

}

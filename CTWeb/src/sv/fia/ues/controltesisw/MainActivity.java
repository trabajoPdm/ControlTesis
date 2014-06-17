package sv.fia.ues.controltesisw;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
	}

	public void loadAlumnos(View v){
		
		Intent intento=new Intent(this,AlumnoMenuActivity.class);
		startActivity(intento);
	}	
public void loadEtapas(View v){
		
		Intent intento=new Intent(this,EtapaMenuActivity.class);
		startActivity(intento);
	}public void loadNotas(View v){
		
		Intent intento=new Intent(this,NotaMenuActivity.class);
		startActivity(intento);
	}
	

}

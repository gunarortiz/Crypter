package com.example.crypter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CrearActivity extends Activity {
	EditText clave;
	EditText mensaje, salida;
	String a1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear);
		
		

		getActionBar().hide();

		clave=(EditText)findViewById(R.id.clave1);
		mensaje=(EditText)findViewById(R.id.mensaje1);
		salida=(EditText)findViewById(R.id.salida1);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear, menu);
		return true;
	}

	public void leer(View  v){
		new Thread(new Runnable(){
			public void run(){

				String cod=clave.getText().toString();

				String x=mensaje.getText().toString();


				String fin="";
				for(int i=0; i<x.length(); i++){
					String q=x.substring(i,i+1);
					fin=q+fin;
				}
				fin=fin+" ";


				int N=cod.length();
				int p=N-(N/2)+1;

				double u=0;
				for (int i = 0; i < cod.length(); i++) {
					if(i%2==0){
						u=cod.codePointAt(i)*3+u;
					}
					else{
						u=cod.codePointAt(i)*6+u;
					}
					if(i==cod.length()-1){
						u=u*2;
					}

				}
				u=u/(int)(Math.pow(N, 2));

				

				//int u=cod.codePointAt(p-1);

				a1="";

				String b="";
				for (int i = 0; i < fin.length(); i++) {
					String y=fin.substring(i,i+1);
					if(y.equals(" ")){

						String b1;
						if(b.length()%2==0){
							b1=b.substring(2, b.length());

						}
						else{
							b1=b.substring(1, b.length()-1);

						}
						int z=b1.length()+1;
						String a = "";

						for (int j = 0; j < b1.length(); j++) {
							double e=u/z;
							if(e!=(int)e)
								e=(e+2)*5;
							else{
								e=(e+2)*2;
							}
							int q=b1.codePointAt(j);
							e=q-e;

							while(e<33){
								e=e+92;
							}

							byte w=(byte)e;

							a= a+(char)w;
							z=z-1;

						}
						if(i!=fin.length()-1){
							a=a+" ";
						}

						a1=a1+a;
						b="";
					}
					else{
						b=b+y;
					}


				}


				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(getApplicationContext(), "Finalizo", Toast.LENGTH_SHORT).show();
						salida.setText(a1);
					}
				});

			}

		}).start();
	}

}

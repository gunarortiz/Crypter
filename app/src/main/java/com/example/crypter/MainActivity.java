package com.example.crypter;

import java.util.Random;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText clave;
	EditText mensaje, salida;
	String fin;
	String a1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().hide();

		clave=(EditText)findViewById(R.id.clave);
		mensaje=(EditText)findViewById(R.id.mensaje);
		salida=(EditText)findViewById(R.id.salida);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ir(View v){
		Intent i=new Intent(MainActivity.this, CrearActivity.class);



		startActivity(i);
	}

	public void codi(View v){






		new Thread(new Runnable(){
			public void run(){

				String cod=clave.getText().toString();

				String x=mensaje.getText().toString();


				int N=cod.length();
				int p=N-(N/2)+1;
				int u=0;
				for (int i = 0; i < cod.length(); i++) {
					if(i%2==0){
						u=cod.codePointAt(i)*3+u;
					}
					else{
						u=cod.codePointAt(i)*2+u;
					}
					if(i==cod.length()-1){
						u=u*2;
					}

				}
				
				



				//int u=cod.codePointAt(p-1);

				if(!x.subSequence(x.length()-1, x.length()).equals(" ")){
					x=x+" ";
				}
				String a = "";
				String b="";
				String a1="";
				
				for (int i = 0; i < x.length(); i++) {
					String y=x.substring(i,i+1);
					if(y.equals("�"))
						y="a";
					if(y.equals("�"))
						y="e";
					if(y.equals("�"))
						y="i";
					if(y.equals("�"))
						y="o";
					if(y.equals("�"))
						y="u";


					if(y.equals(" ")){
						int z=b.length()+1;

						a = "";

						for(int j=0; j<b.length(); j++){
							int e=u/z;
							
							int q=b.codePointAt(j);
							e=e+q;

							
							
							while(e<=33){
								e=e+92;
							}
							while(e>=125){
								e=e-92;
							}

							byte w=(byte)e;

							a= a+(char)w;
							z=z-1;
						}
						int o = 0,t=0;
						Random v1=new Random();
						if(a.length()%2==0){

							while(o<34 || o>124){

								o=(int)(v1.nextDouble()*100);
							}
							byte e=(byte)o;
							a= (char)e+a;

							while(t<34 || t>124){
								t=(int)(v1.nextDouble()*100);
							}
							a= (char)t+a;

						}
						else{
							while(o<34 || o>124){
								o=(int)(v1.nextDouble()*100);
							}
							byte e=(byte)o;
							a= (char)e+a;
							while(t<34 || t>124){
								t=(int)(v1.nextDouble()*100);
							}
							a= a+(char)t;

						}
						a=a+" ";
						a1=a1+a;
						b="";
					}
					else{
						b=b+y;
					}

				}
				fin="";
				for(int i=0; i<a1.length()-1; i++){
					String q=a1.substring(i,i+1);
					fin=q+fin;
				}



				runOnUiThread(new Runnable(){
					public void run(){
						Toast.makeText(getApplicationContext(), "Finalizo", Toast.LENGTH_SHORT).show();
						salida.setText(fin);
					}
				});

			}

		}).start();


	}
	
	public void copiar(View v){
		ClipData clip=ClipData.newPlainText("mensaje", salida.getText()+"");
		ClipboardManager clipboard=(ClipboardManager)this.getSystemService(CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip(clip);
		Toast.makeText(getApplicationContext(), "Copiado", Toast.LENGTH_SHORT).show();
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

				int u=0;
				for (int i = 0; i < cod.length(); i++) {
					if(i%2==0){
						u=cod.codePointAt(i)*3+u;
					}
					else{
						u=cod.codePointAt(i)*2+u;
					}
					if(i==cod.length()-1){
						u=u*2;
					}

				}
				

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
							int e=u/z;
							
							int q=b1.codePointAt(j);
							e=q-e;

							
							
							while(e<=33){
								e=e+92;
							}
							while(e>=125){
								e=e-92;
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
	
	public void reset(View v){
		clave.setText("");
		mensaje.setText("");
		salida.setText("");
	}

}

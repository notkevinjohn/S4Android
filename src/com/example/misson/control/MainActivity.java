package com.example.misson.control;


import Communication.SocketThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends Activity 
{	
	
	public SocketThread socketThread;
	static final int FAILURE_DIALOG = 0;  
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);          
        
        final Button button = (Button) findViewById(R.id.button1);        
        final EditText ipText = (EditText) findViewById(R.id.editText1);
        final EditText portText = (EditText) findViewById(R.id.editText2);  
        
        final Handler connectionHandler = new Handler() 
        {        	
        	@Override
	    	public void handleMessage(Message message) 
    		{       		      		
        		super.handleMessage(message);
        		if(message.obj.toString() == "success")
        		{
        			Intent intent = new Intent(getApplicationContext(), PayloadMonitorActivity.class);        			     			
        			startActivity(intent);  
        		}
        		else if(message.obj.toString() == "failure")
        		{
        			AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        			alertDialog.setMessage("Server Not Found");
        			alertDialog.show();        			
        		}
        		
    	    }
        };
        button.setOnClickListener(new View.OnClickListener() 
        { 
			public void onClick(View v) 
            {	    	
        		socketThread = SocketThread.getInstance();
	            socketThread.ip = ipText.getText().toString();         
	            socketThread.port = Integer.parseInt(portText.getText().toString());
	            socketThread.connectionHandler = connectionHandler;
	            socketThread.start();     
            }
        });
       
    }    
    @Override
    protected Dialog onCreateDialog(int id)
    {
    	Dialog dialog;    
		switch(id) 
		{
	    case  FAILURE_DIALOG:	       
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage("Server Not Found");	    	       
	    	AlertDialog alert = builder.create();
	    	dialog = alert;
	        break;
	    
	    default:
	        dialog = null;
	    }    	
    	return dialog;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
}


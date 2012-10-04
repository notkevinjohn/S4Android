package com.example.misson.control;

import java.util.ArrayList;

import Communication.SocketThread;
import Communication.TerminalThread;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PayloadMonitorActivity extends Activity {
	
	
	private ListView terminal;
	private ArrayList<String> terminalValues = new ArrayList<String>();
	private ArrayAdapter<String> terminalAdapter;	
	
    @SuppressLint("HandlerLeak")
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payload_monitor);         
        terminal = (ListView) findViewById(R.id.terminal); 
        terminal.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        terminalAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, terminalValues);
        terminal.setAdapter(terminalAdapter);   
        
        final Handler terminalHandler = new Handler()
        {
	    	@Override
	    	public void handleMessage(Message message) 
    		{      	  
    	      	terminalValues.add((String) message.obj);
    	      	terminalAdapter.notifyDataSetChanged();
    			super.handleMessage(message);
    	    }
        	  
        };
        TerminalThread terminalThread = new TerminalThread(terminalHandler);
        terminalThread.start();
        
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.payload_monitor, menu);
        return true;
    }
}

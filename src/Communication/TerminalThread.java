package Communication;


import android.os.Handler;
import android.os.Message;


public class TerminalThread extends Thread {

	/**
	 * @param args
	 */	
	private SocketThread socketThread;
	private Handler handler;
	
	public TerminalThread (Handler handler)
	{
		super();		
		this.handler = handler;
		socketThread = SocketThread.getInstance();
	}

	public void run () 
	{
		while(true)
		{			
			Message message = handler.obtainMessage();
			//message.obj = socketThread.StreamIn();
			handler.sendMessage(message);			
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{				
				e.printStackTrace();
			}
			
		}
	}	
	

}

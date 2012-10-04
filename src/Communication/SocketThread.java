package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class SocketThread extends Thread {

	
	/**
	 * @param args
	 */
	private static SocketThread instance;
	
	public String ip;	
	public int port;
	public Socket socket;
	public SocketAddress socketAddress;		
	public Handler connectionHandler;	

	
	public static synchronized SocketThread getInstance()
	{
		if (instance == null)
		{
			instance = new SocketThread();
		}
		return instance;
		
	}
	
	public void run() 
	{
		socketAddress = new InetSocketAddress(ip, port);
		socket = new Socket();	
		Message message = connectionHandler.obtainMessage();
		try 
		{
			socket.connect(socketAddress, 5000);			
		} 
		catch (IOException e) 
		{			
			message.obj = "failure";
			connectionHandler.sendMessage(message);
		}
		if(socket.isConnected())
		{
			message.obj = "success";
			connectionHandler.sendMessage(message);
		}		
	}
//	public String StreamIn()
//	{		
//		String recieve;
//		try 
//		{
//			int available = socket.getInputStream().available();
//			byte chunk[] = new byte[available];
//			socket.getInputStream().read(chunk, 0, available);
//			recieve = new String(chunk);
//	    } 
//		catch (IOException e) 
//		{
//			recieve = null;
//			e.printStackTrace();
//		}
//		return recieve;
//	}
	
	
}

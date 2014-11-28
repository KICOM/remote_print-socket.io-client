package com.kicomlab.remote_print;

import java.net.MalformedURLException;

import org.json.JSONObject;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

public class Service{

	private String URL = "http://kicom.eaeao.wo.tc:3000/";
	private SocketIO socket = null;
	private PrintImage pi = null;

	Service(){
		pi = new PrintImage();
		startSocket();
	}

	public void startSocket(){
		IOCallback ioCallBack = new IOCallback() {
			@Override
			public void onDisconnect() {
				// TODO Auto-generated method stub
				System.out.println("Connection terminated.");
				//socket.emit("disconnect", "");
			}

			@Override
			public void onConnect() {
				// TODO Auto-generated method stub
				System.out.println("Connection established");
			}

			@Override
			public void onMessage(String data, IOAcknowledge ack) {
				// TODO Auto-generated method stub
				System.out.println("Server said: " + data);
			}

			@Override
			public void onMessage(JSONObject json, IOAcknowledge ack) {
				// TODO Auto-generated method stub
				try {
					System.out.println("Server said:" + json.toString(2));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void on(String event, IOAcknowledge ack, Object... args) {
				// TODO Auto-generated method stub
				System.out.println("Server triggered event '" + event + "'");
				try {
					JSONObject jo = new JSONObject(args[0].toString());

					if (event.equals("print")){
						String uid = jo.getString("uid");
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@ >> "+uid);
						pi.printer();
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onError(SocketIOException socketIOException) {
				// TODO Auto-generated method stub
				System.out.println("an Error occured");

				System.out.println("error: " + socketIOException.getMessage());
				socketIOException.printStackTrace();
				if (socketIOException.getMessage().endsWith("+0")) {
					socket.disconnect();
					try {
						socket = new SocketIO(URL, this);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		try {
			if (socket == null) {
				socket = new SocketIO(URL, ioCallBack);
			}else if(!socket.isConnected()){
				socket = new SocketIO(URL, ioCallBack);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

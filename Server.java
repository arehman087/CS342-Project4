import java.io.*;
import java.net.*;


public class Server {
	private boolean turn;	//returns true f its this players turn.
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	private ServerSocket server;
	private Socket connect;
	
	//guiStuffs;
	
	public Server() {
		//guiStuffs
	}

	public void StartRun(){
		try{
			this.server = new ServerSocket(6789, 100);
			while(true){
				try{
					wait4Connection();
					setupStreams();
					whileChatting();
					
				}catch(EOFException eofException){
					System.out.println("\n Server ended the connection!!!");
				}finally{
					closeCrap();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	//wait 4 connection\
	public void wait4Connection() throws IOException {
		System.out.println("WAITNG FOR CONNECTION");
		this.connect = server.accept();
		System.out.println("CONNECTED!" + this.connect.getInetAddress().getHostName());
	}
	
	public void setupStreams() throws IOException{
		this.output = new ObjectOutputStream(this.connect.getOutputStream());
		this.output.flush();
		
		this.input = new ObjectInputStream(this.connect.getInputStream());
		System.out.println("\n Streams are set up");
	}
	
	public void whileChatting() throws IOException{
		String message = "you are now Connected";
		System.out.println(message);
		
		do {
			try{
				message  = (String) this.input.readObject();
				System.out.println(message);
			}catch (ClassNotFoundException CNFE){
				System.out.println("WTH DID U DO");
				
			}
		}while(!message.equals("CLIENT - END"));
	}
	
	public void closeCrap(){
		System.out.println("CLOSING CONNECTIONS");
		//stop users from having input.
		try{
			this.output.close();
			this.input.close();
			this.connect.close();
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	//send hit
	private void sendHit(String m){
		try{
			this.output.writeObject("SERVER - " + m);
			this.output.flush();
		}catch(IOException ioe){
			
		}
	}
}

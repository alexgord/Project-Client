import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientApp
{

	final private static String DEFAULT_HOST = "127.0.0.1";
	final private static int DEFAULT_PORT = 9999;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Game game1 = new Game();
		//System.out.println("Welcome to Knight's Watch!!!");
		//while(true)
		//{
		//System.out.println(game1.getBoard().toString());
		//game1.makeMoveForCurrentPlayer();
		//}
		
		String host = args.length > 0 ? args[0] : DEFAULT_HOST;
		int port = args.length > 1 ? Integer.parseInt(args[1]) :  DEFAULT_PORT;
		
		try
		{
			// connect to the server
			Socket socket = new Socket(host, port);
			
			// create a scanner & printstream out of the socket's I/O streams
			Scanner socketIn = new Scanner(socket.getInputStream());
			PrintStream socketOut = new PrintStream(socket.getOutputStream());
			
			String playerNum = socketIn.nextLine();
			System.out.println("You are player number : " + playerNum);
			// prepare STDIN for reading
			Scanner stdin = new Scanner(System.in);
			String input = "";// = stdin.nextLine();
			String echo;
			String oppositePlayerNum = (playerNum.equals("1"))?"2":"1";
			if (playerNum.equals("2"))
			{
				System.out.println("Waiting for player" + oppositePlayerNum + " to say something...");
				echo = socketIn.nextLine();
				// print to STDOUT
				System.out.println(echo);
			}
			
			while(!input.equals("quit"))
			{
				//Get the text
				System.out.print("Say something: ");
				input = stdin.nextLine();
				// send the text
				socketOut.println("Player number " + playerNum + " says: " + input);
				
				// receive the echo
				System.out.println("Waiting for player" + oppositePlayerNum + " to say something...");
				echo = socketIn.nextLine();

				// print to STDOUT
				System.out.println(echo);
				
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

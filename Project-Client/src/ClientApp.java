import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException
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
			ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream socketOut = new ObjectOutputStream(new PrintStream(socket.getOutputStream()));

			String playerNum = (String) socketIn.readObject();
			System.out.println("You are player number : " + playerNum);
			// prepare STDIN for reading
			Scanner stdin = new Scanner(System.in);
			String input = "";// = stdin.nextLine();
			String echo;
			String oppositePlayerNum = (playerNum.equals("1"))?"2":"1";
			if (playerNum.equals("2"))
			{
				System.out.println("Waiting for player" + oppositePlayerNum + " to say something...");
				echo = (String) socketIn.readObject();
				// print to STDOUT
				System.out.println(echo);
			}

			while(!input.equals("quit"))
			{
				//Get the text
				System.out.print("Say something: ");
				input = stdin.nextLine();
				// send the text
				socketOut.writeObject("Player number " + playerNum + " says: " + input);

				// receive the echo
				System.out.println("Waiting for player" + oppositePlayerNum + " to say something...");
				echo = (String) socketIn.readObject();

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

/**************************************ID BLOCK*******************************************************

Due Date:			May 11th, 2012
Software Designers:	Alexandre Simard & Peter Johnston
Course:				420-603  Winter 2012
Deliverable:		Project --- Knight's Watch --- Client
Description:		This program creates a game, connects to the server, and if it is the first of 
					two players, it waits until the server sends the number of players.  When this 
					occurs, the game commences.  Depending on who connected first, the client is 
					either assigned White (P1), or Black (P2).  The Client then sends their move, or
					waits to receive the opponent's move from the server.  When the client sends a 
					move, it is then checked by the client to see if it is a valid move.  If so, 
					the move is transmitted to the server.  When the current client determines that 
					the game is over, it broadcasts the outcome, and the program is ended.  
					
******************************************************************************************************/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


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
		Game game1 = new Game();
		Move currMove;
		Move enemyMove;
		System.out.println("Welcome to Knight's Watch!!!");
		System.out.println("Waiting for another player to connect...");

		String host = args.length > 0 ? args[0] : DEFAULT_HOST;
		int port = args.length > 1 ? Integer.parseInt(args[1]) :  DEFAULT_PORT;

		try
		{
			// connect to the server
			Socket socket = new Socket(host, port);

			// create a scanner & printstream out of the socket's I/O streams
			ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
			socketOut.flush();

			String playerNum = (String) socketIn.readObject();
			System.out.println("You are player number : " + playerNum);
			// prepare STDIN for reading
			String oppositePlayerNum = (playerNum.equals("1"))?"2":"1";

			System.out.println("You are player" + playerNum + ", your color is: " + ((playerNum.equals("1"))?"white":"black"));
			game1.setCurrPlayer((playerNum.equals("1"))?ColorEnum.WHITE:ColorEnum.BLACK);

			System.out.println(game1.getBoard().toString());

			if (playerNum.equals("2"))
			{
				System.out.println("Waiting for player" + oppositePlayerNum + " to make his move...");
				enemyMove = (Move) socketIn.readObject();
				// print to STDOUT
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("Enemy has made this move: From " + enemyMove.getA().toString() + " to " + enemyMove.getB().toString());
				game1.getBoard().movePiece(enemyMove);
				System.out.println(game1.getBoard().toString());
			}

			while(game1.getStatus() == GameStatus.CONTINUE)
			{
				//Get the move
				currMove = game1.makeMoveForCurrentPlayer();
				game1.getBoard().movePiece(currMove);
				currMove.setResultingGameStatus(game1.getStatus());
				// send the move
				socketOut.writeObject(currMove);
				socketOut.flush();

				System.out.println(game1.getBoard().toString());
				if (game1.getStatus() == GameStatus.CONTINUE)
				{
					// receive the enemy move
					System.out.println("Waiting for player" + oppositePlayerNum + " to make his move...");
					enemyMove = (Move) socketIn.readObject();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					System.out.println("Enemy has made this move: From " + enemyMove.getA().toString() + " to " + enemyMove.getB().toString());
					game1.getBoard().movePiece(enemyMove);
					System.out.println(game1.getBoard().toString());
				}
				else
				{
					break;
				}
			}

			System.out.println("The Game is finished!");
			switch (game1.getStatus())
			{
			case WHITEWINS:
				System.out.println("White has won!");
				break;
			case BLACKWINS:
				System.out.println("Black has won!");
				break;
			case DRAW:
				System.out.println("it was a draw!");
				break;
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

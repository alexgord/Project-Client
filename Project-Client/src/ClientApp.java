
public class ClientApp
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Game game1 = new Game();
		System.out.println("Welcome to Knight's Watch!!!");
		while(true)
		{
			System.out.println(game1.getBoard().toString());
			game1.makeMoveForCurrentPlayer();
		}
	}
}

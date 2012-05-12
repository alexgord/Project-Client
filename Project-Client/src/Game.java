/**********************************Game CLASS INFO************************************************

This class is responsible for setting the current player, getting and setting the players, 
starting the game, getting the board, making the player moves, and determining the current status
of the game.  

**************************************************************************************************/

public class Game
{
	
	private Board board;
	
	private Player player1;
	private Player player2;
	private Player currPlayer;
	
	public void setCurrPlayer(ColorEnum color)
	{
		if (color == ColorEnum.WHITE)
		{
			this.currPlayer = player1;
		}
		else
		{
			this.currPlayer = player2;
		}
	}

	public Player getPlayer1()
	{
		return player1;
	}

	public void setPlayer1(Player player1)
	{
		this.player1 = player1;
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public void setPlayer2(Player player2)
	{
		this.player2 = player2;
	}

	public Game()
	{
		board = new Board();
		player1 = new Player(ColorEnum.WHITE, board);
		player2 = new Player(ColorEnum.BLACK, board);
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public Move makeMoveForCurrentPlayer()
	{
		return currPlayer.makeMove();
	}
	
	public GameStatus getStatus()
	{
		GameStatus r = GameStatus.CONTINUE;
		
		//Have any pawns reached the enemy wall?
		for (int i = 0; i < 6; i++)
		{
			//Check the White pawns
			if (board.getBoard()[0][i] != null)
			{
				if (board.getBoard()[0][i].getPiece() == PieceEnum.PAWN)
				{
					r = GameStatus.WHITEWINS;
					break;
				}
			}
			
			//And the Black pawns
			if (board.getBoard()[5][i] != null)
			{
				if (board.getBoard()[5][i].getPiece() == PieceEnum.PAWN)
				{
					r = GameStatus.BLACKWINS;
					break;
				}
			}
		}
		
		
		//Do both players still have pawns?
		int numDeadBlackPawns = 0;
		int numDeadWhitePawns = 0;
		for (int i = 0; i < board.getGraveyard().size(); i++)
		{
			
			if (board.getGraveyard().get(i).getColor() == ColorEnum.BLACK && board.getGraveyard().get(i).getPiece() == PieceEnum.PAWN)
			{
				numDeadBlackPawns++;
			}
			else
			{
				if (board.getGraveyard().get(i).getColor() == ColorEnum.WHITE && board.getGraveyard().get(i).getPiece() == PieceEnum.PAWN)
				{
					numDeadWhitePawns++;
				}
			}
		}
		
		if (numDeadWhitePawns == 6 && numDeadBlackPawns == 6)
		{
			r = GameStatus.DRAW;
		}
		
		int numDeadBlackPieces = 0;
		int numDeadWhitePieces = 0;
		
		//Do both players have at least one piece?
		for (int i = 0; i < board.getGraveyard().size(); i++)
		{
			if (board.getGraveyard().get(i).getColor() == ColorEnum.BLACK)
			{
				numDeadBlackPieces++;
			}
			else
			{
				if (board.getGraveyard().get(i).getColor() == ColorEnum.WHITE)
				{
					numDeadWhitePieces++;
				}
			}
		}
		
		if (numDeadWhitePieces == 8)
		{
			r = GameStatus.BLACKWINS;
		}
		else
		{
			if (numDeadBlackPieces == 8)
			{
				r = GameStatus.WHITEWINS;
			}
		}
		
		return r;
	}
}

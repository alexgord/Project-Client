import java.util.ArrayList;


public class Board
{
	private Piece[][] board;
	private ArrayList<Piece> graveyard;
	
	public Board()
	{
		board = new Piece[6][6];
		graveyard = new ArrayList<Piece>();
		
		int count = 0;
		Piece temp;
		while (count < 6)
		{
			temp = new Piece(ColorEnum.BLACK, PieceEnum.PAWN);
			board[1][count] = temp;
			temp = new Piece(ColorEnum.WHITE, PieceEnum.PAWN);
			board[4][count] = temp;
			count++;
		}
		temp = new Piece(ColorEnum.BLACK, PieceEnum.KNIGHT);
		board[0][1] = temp;
		board[0][4] = temp;
		temp = new Piece(ColorEnum.WHITE, PieceEnum.KNIGHT);
		board[5][1] = temp;
		board[5][4] = temp;
	}

	public ArrayList<Piece> getGraveyard()
	{
		return graveyard;
	}

	public void setGraveyard(ArrayList<Piece> graveyard)
	{
		this.graveyard = graveyard;
	}

	public String toString()
	{
		String output;
		output = " -------------------\n";

		for (int cx = 0; cx < 6; cx++)
		{
			output += 6-cx;
			output += "|";
			for (int cy = 0; cy < 6; cy++)
			{
				if (board[cx][cy] == null) 
				{
					output += "  |";
				}
				else
				{
					output += board[cx][cy].toString() + "|";
				}
			}
			output += "\n";
			output += " -------------------\n";
		}
		output += "  a  b  c  d  e  f\n";
		return output;
	}

	public Piece[][] getBoard()
	{
		return board;
	}

	public void setBoard(Piece[][] board)
	{
		this.board = board;
	}

	public void movePiece(Move move)
	{
		if (board[move.getB().getRow()][move.getB().getCollumn()] != null)
		{
			graveyard.add(board[move.getB().getRow()][move.getB().getCollumn()]);
		}
		board[move.getB().getRow()][move.getB().getCollumn()] = board[move.getA().getRow()][move.getA().getCollumn()];
		board[move.getA().getRow()][move.getA().getCollumn()] = null;
	}
}

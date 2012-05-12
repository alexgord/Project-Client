/**********************************Piece CLASS INFO************************************************

This class is responsible for creating and getting a piece, getting the color, and getting the 
string of the piece for the board (ex. WK).  

**************************************************************************************************/

public class Piece
{
	private ColorEnum color;
	private PieceEnum piece;
	
	public Piece(ColorEnum color, PieceEnum piece)
	{
		this.color = color;
		this.piece = piece;
	}
	
	public String toString()
	{
		String output;
		output = color.toString() + piece.toString();
		return output;
	}

	public ColorEnum getColor()
	{
		return color;
	}

	public PieceEnum getPiece()
	{
		return piece;
	}
	
}

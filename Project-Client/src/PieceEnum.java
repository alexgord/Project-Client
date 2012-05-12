/**********************************PieceEnum ENUM INFO********************************************

This is an enum for the different kinds of pieces.  It also returns a string of the piece.  

**************************************************************************************************/

public enum PieceEnum {
	KNIGHT ("K"), PAWN ("P");
	
	private String pieceStr;

	PieceEnum(String pieceStr) {
		this.pieceStr = pieceStr;
	}
	
	public String toString() {
		return pieceStr;
	}
}

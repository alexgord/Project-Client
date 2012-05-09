import java.util.ArrayList;
import java.util.Scanner;


public class Player
{
	private ColorEnum color;
	private Board board;

	public Player(ColorEnum color, Board board)
	{
		this.color = color;
		this.board = board;
	}

	public void makeMove()
	{
		Move move;

		//Keep looping and getting moves 'till we get a valid move
		while (true)
		{
			move = getMove();
			//If we're trying to move from positions that are actually in the board
			if (!(move.getA().isOutOfBounds() || move.getB().isOutOfBounds()))
			{
				//If we're trying to move a nonexistant piece
				if (board.getBoard()[move.getA().getRow()][move.getA().getCollumn()] != null)
				{
					//If we're trying to move a piece that isn't ours
					if (board.getBoard()[move.getA().getRow()][move.getA().getCollumn()].getColor() == this.color)
					{
						if (PieceCanMove(board.getBoard()[move.getA().getRow()][move.getA().getCollumn()], move, board))
						{
							System.out.println("Move is valid, hooraayy!");
							break;
						}
						else
						{
							System.out.println("Sorry, that piece cannot move there");
						}
					}
					else
					{
						System.out.println("Sorry, that piece is not yours to move");
					}
				}
				else
				{
					System.out.println("Sorry, there is no piece in the specified starting position");
				}
			}
			else
			{
				System.out.println("Sorry, the position isn't within the board");
			}
			System.out.println("You must re-enter your move");
		}
	}

	public Move getMove()
	{
		Move r;
		BoardPosition a;
		BoardPosition b;
		Scanner sc = new Scanner(System.in);
		int row;
		String collumn;

		System.out.print("Enter the row of the position of the piece you would like to move: ");		
		row = sc.nextInt();

		System.out.print("Enter the collumn of the position of the piece you would like to move: ");
		collumn = sc.next();

		a = new BoardPosition(6 - row, getNumForCollumn(collumn));

		System.out.print("Enter the row of the position you would like to move this piece to: ");		
		row = sc.nextInt();

		System.out.print("Enter the collumn of the position you would like to move this piece to: ");		
		collumn = sc.next();

		b = new BoardPosition(row, getNumForCollumn(collumn));

		r = new Move(a, b);

		return r;
	}

	private int getNumForCollumn(String i)
	{
		ArrayList<String> colNames = new ArrayList<String>();

		colNames.add("a");
		colNames.add("b");
		colNames.add("c");
		colNames.add("d");
		colNames.add("e");
		colNames.add("f");

		int r = colNames.indexOf(i);

		return r;
	}

	public ColorEnum getColor()
	{
		return color;
	}

	public boolean PieceCanMove(Piece piece, Move move, Board board)
	{
		//TODO:
		boolean r = false;
		return r;
	}
}

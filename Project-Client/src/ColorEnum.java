/**********************************ColorEnum ENUM INFO*****************************************

This is an enum for the colors of the player.  It also returns a string of the color.  

**************************************************************************************************/

public enum ColorEnum {
	WHITE ("W"), BLACK ("B");
	
	private String colorStr;

	ColorEnum(String colorStr) {
		this.colorStr = colorStr;
	}
	
	public String toString() {
		return colorStr;
	}
}

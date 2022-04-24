
/**
 * Boardable interface for creating elements of type Boardable
 * @author Andrew Buirge
 *
 */
public interface Boardable {

	/**
	 * isVisible method for determining if Boardable element is visible
	 * @return true for visible, false for not visible
	 */
	public boolean isVisible();
	
	/**
	 * share method for determining if the passed Boardable can be in the same cell as the current Boardable object
	 * @param elem Boardable object to be shared
	 * @return true for sharable false for unsharable
	 */
	public boolean share(Boardable elem);

}

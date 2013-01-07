package fr.la.jproductbase.metier;

public class ToolsProduct {
	/**
	 * Delete ahead characters from data.
	 * 
	 * @param ahead Ahead characters to delete.
	 * 
	 * @param data
	 */
	public static String deleteAhead(String ahead, String data) {
		String _data = data;
		int _aheadIndex = _data.indexOf(ahead);
		while (0 == _aheadIndex) {
			_data = _data.substring(_aheadIndex + ahead.length());

			_aheadIndex = _data.indexOf(ahead);
		}

		return _data;
	}
}

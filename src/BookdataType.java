/**
 * 家計簿に登録されるデータタイプの列挙型。
 * 収入(INCOME)または支出(EXPEND)の値を取る。
 */
public enum BookdataType {
	INCOME, EXPEND;

	/**
	 * String型の文字列をBookdataType型に変換するメソッド。
	 * @param str 文字列
	 * @return データタイプ
	 * @exception IllegalArgumentException
	 				引数の文字列が収入(INCOME)または支出(EXPEND)以外のとき。
	 */
	public static BookdataType toBookdataType (int type) throws IllegalArgumentException {
		BookdataType result = EXPEND;
		if (type == 1) {
			result = INCOME;
		} else if (type == 0) {
			/* 何もしない */
		} else {
			throw new IllegalArgumentException("不正な文字列を変換しようとしています：" + type);
		}
		return result;
	}
}

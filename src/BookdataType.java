/* 家計簿に登録されるデータタイプ */
public enum BookdataType {
	INCOME, EXPEND;	/* 収入, 支出 */

	/* String型文字列をBookdataType型に変換するメソッド */
	public static BookdataType toBookdataType (String str) throws IllegalArgumentException {
		BookdataType result = EXPEND;
		if (str.equals("INCOME")) {
			result = INCOME;
		} else if (str.equals("EXPEND")) {
			/* 何もしない */
		} else {
			throw new IllegalArgumentException("不正な文字列を変換しようとしています：" + str);
		}
		return result;
	}
}

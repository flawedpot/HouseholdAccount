/**
 * 家計簿データクラス
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Bookdata {
	/** データタイプ(収入 / 支出) */
	private BookdataType type;
	/** 金額 */
	private int amount;
	/** 入力日 */
	private Date day;

	public Bookdata(BookdataType type, int amount, Date day) {
		this.type = type;
		this.amount = amount;
		this.day = day;
	}

	public Bookdata() {
		this(BookdataType.EXPEND, 0, new Date());
	}

	/**
	 * インスタンス生成直後に呼び出す、家計簿データの各フィールドの値を入力させるメソッド。
	 */
	public void AddBookdata() {
		boolean inputFlag = true;

		/* 各フィールドを入力させるメソッドを呼び出す */
		try {
			while (true) {
				inputType();
				inputAmount();
				inputDay();
				break;
			}
		} catch (IllegalArgumentException iae) {
			System.err.println("不正な値が入力されました。始めからやり直してください");
			iae.printStackTrace();
		} catch (ParseException pe) {
			System.err.println("型変換に失敗しました。始めからやり直してください");
			pe.printStackTrace();
		}
	}

	/**
	 * データタイプを入力するメソッド。
	 * @exception IllegalArgumentException
	  				不正な値が入力されたとき
	 */
	private void inputType() {
		int type;
		System.out.println("データタイプを選択してください");
		System.out.println("支出:0, 収入：1");
		Scanner scType = new Scanner(System.in);
		type = scType.nextInt();
		this.type = BookdataType.toBookdataType(type);
	}

	/**
	 * 金額を入力するメソッド。
	 * @exception IllegalArgumentException
	  				不正な値が入力されたとき
	 */
	private void inputAmount() {
		int i;
		System.out.println("金額を入力してください");
		Scanner scAmount = new Scanner(System.in);
		i = scAmount.nextInt();
		if (i > 0)
			this.amount = i;
		else
			throw new IllegalArgumentException("不正な値が入力されました：" + i);
	}

	/**
	 * 入力日を入力するメソッド。
	 * @exception ParseException
	 				入力された文字列のString型からDate型への変換に失敗したとき
	 * @exception IllegalArgumentException
	  				不正な値が入力されたとき
	 */
	private void inputDay() throws ParseException {
		String str;
		System.out.println("日付を入力してください");
		System.out.println("yyyyy/mm/dd");
		Scanner scDay = new Scanner(System.in);
		str = scDay.nextLine();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");
		this.day = (Date)f.parse(str);
	}

	/**
	 * データタイプのゲッターメソッド。
	 * @return データタイプ
	 */
	public BookdataType getBookdataType() {
		return this.type;
	}

	/**
	 * 金額のゲッターメソッド。
	 * @return 金額
	 */
	public int getAmount() {
		return this.amount;
	}

	/**
	 * 入力日のゲッターメソッド。
	 * @return 入力日
	 */
	public Date getDay() {
		return this.day;
	}

	/**
	 * toStringメソッドのオーバライド。
	 * @return データタイプ,金額,入力日
	 */
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");
		return this.type + "," + this.amount + "," + f.format(this.day);
	}

	/**
	 * equalsメソッドにオーバライド。
	 * データタイプ、金額、入力年月日が同じときにtrueを返す
	 * @param o オブジェクト型変数
	 * @return 真偽値
	 */
	@Override
	public boolean equals(Object o) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(this.day);

		if (o == this) return true;
		if (o == null) return false;
		if (!(o instanceof Bookdata)) return false;
		Bookdata data = (Bookdata)o;
		c2.setTime(data.day);
		if (!this.type.equals(data.type))return false;
		if (this.amount != data.amount)return false;
		if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))return false;
		if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))return false;
		if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH))return false;
		return true;
	}

	/**
	 * hashcodeメソッドのオーバライド。
	 * データタイプ、金額、入力日からハッシュ値を生成する。
	 * @return ハッシュ値
	 */
	@Override
	public int hashCode() {
		int result = 37;
		result = result * 31 + this.type.hashCode();
		result = result * 31 + ((Integer)(this.amount)).hashCode();
		result = result * 31 + this.day.hashCode();
		return result;
	}
}

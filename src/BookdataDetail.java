/**
 * 家計簿データ(詳細)クラス。
 * 家計簿データクラスにカテゴリフィールドとメモフィールドを追加したクラス。
 * 家計簿データクラスに最初から上記フィールドを持たせてもよかったが、
 * クラス継承の練習のために作成した。
 */

import java.util.Date;
import java.util.Scanner;

public class BookdataDetail extends Bookdata {
	/** カテゴリ */
	private String category;
	/** メモ */
	private String memo;

	public BookdataDetail (BookdataType type,
							int amount,
							Date day,
							String category,
							String memo) {
		super(type, amount, day);
		this.category = category;
		this.memo = memo;
	}

	public BookdataDetail () {
		super();
		this.category = "";
		this.memo = "";
	}

	/**
	 * インスタンス生成直後に呼び出す、家計簿データの各フィールドの値を入力させるメソッド。
	 */
	@Override
	public void AddBookdata() {
		super.AddBookdata();
		boolean inputFlag = true;
		try {
			while (inputFlag) {
				inputCategory();
				inputMemo();
				inputFlag = false;
			}
		} catch (IllegalArgumentException iae) {
			System.err.println("不正な値が入力されました。始めからやり直してください");
			iae.printStackTrace();
		}
	}

	/**
	 * カテゴリを入力するメソッド。
	 */
	private void inputCategory() {
		String str;
		System.out.println("カテゴリーを入力してください");
		Scanner scCategory = new Scanner(System.in);
		str = scCategory.nextLine();
		this.category = str;
	}

	/**
	 * メモを入力するメソッド。
	 */
	private void inputMemo() {
		String str;
		System.out.println("メモを入力してください");
		Scanner scMemo = new Scanner(System.in);
		str = scMemo.nextLine();
		this.memo = str;
	}

	/**
	 * カテゴリのゲッターメソッド
	 * @return カテゴリ
	 */
	public String getCategory () {
		return this.category;
	}

	/**
	 * メモのゲッターメソッド
	 * @return メモ
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * toStringメソッドのオーバライド。
	 * @return データタイプ,金額,入力日,カテゴリ,メモ
	 */
	@Override
	public String toString() {
		return super.toString()
				+ "," + this.category
				+ "," + this.memo;
	}

	/**
	 * equalsメソッドにオーバライド。
	 * データタイプ、金額、入力年月日、カテゴリ、メモが同じときにtrueを返す
	 * @param o オブジェクト型変数
	 * @return 真偽値
	 */
	@Override
	public boolean equals(Object o) {
		BookdataDetail data = (BookdataDetail)o;
		if (super.equals(o)) {
			if (!this.category.equals(data.category))return false;
			if (!this.memo.equals(data.memo))return false;
		} else {
			return false;
		}
		return true;
	}

	/**
	 * hashcodeメソッドのオーバライド。
	 * データタイプ、金額、入力日からハッシュ値を生成する。
	 * @return ハッシュ値
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = result * 31 + this.category.hashCode();
		result = result * 31 + this.memo.hashCode();
		return result;
	}
}

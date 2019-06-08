import java.util.Date;
import java.util.Scanner;

public class BookdataDetail extends Bookdata {
	private String category;		/* 収支のカテゴリ */
	private String memo;			/* 収支に関するメモ書き */

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

	/* インスタンス生成時に呼び出す初期化用メソッド */
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
		}
	}

	/* 各フィールドの入力用メソッド */
	public void inputCategory() {
		String str;
		System.out.println("カテゴリーを入力してください");
		Scanner scCategory = new Scanner(System.in);
		str = scCategory.nextLine();
		this.category = str;
	}

	public void inputMemo() {
		String str;
		System.out.println("メモを入力してください");
		Scanner scMemo = new Scanner(System.in);
		str = scMemo.nextLine();
		this.memo = str;
	}

	/* 各フィールドのゲッターメソッド */
	public String getCategory () {
		return this.category;
	}

	public String getMemo() {
		return memo;
	}

	/* 各フィールドのセッターメソッド */
	public void setCategory(String category) {
		this.category = category;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/* toStringメソッドのオーバライド */
	@Override
	public String toString() {
		return super.toString()
				+ "," + this.category
				+ "," + this.memo;
	}

	/* equalsメソッドのオーバライド */
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

	/* hashcodeメソッドのオーバライド */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = result * 31 + this.category.hashCode();
		result = result * 31 + this.memo.hashCode();
		return result;
	}
}

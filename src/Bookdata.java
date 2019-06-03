import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/* 家計簿に登録されるデータのクラス */
public class Bookdata {
	private BookdataType type;		/* 収入か支出か */
	private int amount;			/* 金額 */
	private Date day;				/* 支出日 */

	public Bookdata() {
		this.type = BookdataType.EXPEND;
		this.amount = 0;
		this.day = new Date();
	}

	/* インスタンス生成時に呼び出す初期化用メソッド */
	public void AddBookdata() {
		boolean inputFlag = true;
		try {
			while (true) {
				inputType();
				inputAmount();
				inputDay();
				break;
			}
		} catch (IllegalArgumentException iae) {
			System.err.println("不正な値が入力されました。始めからやり直してください");
		} catch (ParseException pe) {
			System.err.println("型変換に失敗しました。始めからやり直してください");
		}
	}

	/* 各フィールドの入力用メソッド */
	public void inputType() {
		int i;
		System.out.println("データタイプを選択してください");
		System.out.println("収入：0, 支出:1");
		Scanner scType = new Scanner(System.in);
		i = scType.nextInt();
		if (i == 0)
			this.type = BookdataType.INCOME;
		else if (i == 1)
			this.type = BookdataType.EXPEND;
		else {
			throw new IllegalArgumentException("不正な値が入力されました：" + i);
		}
	}

	public void inputAmount() {
		int i;
		System.out.println("金額を入力してください");
		Scanner scAmount = new Scanner(System.in);
		i = scAmount.nextInt();
		if (i > 0)
			this.amount = i;
		else
			throw new IllegalArgumentException("不正な値が入力されました：" + i);
	}

	public void inputDay() throws ParseException {
		String str;
		System.out.println("日付を入力してください");
		System.out.println("yyyyy/mm/dd");
		Scanner scDay = new Scanner(System.in);
		str = scDay.nextLine();
		SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");
		this.day = (Date)f.parse(str);
	}

	/* 各フィールドのゲッターメソッド */
	public BookdataType getBookdataType() {
		return this.type;
	}

	public int getAmount() {
		return this.amount;
	}

	public Date getDay() {
		return this.day;
	}

	/* 各フィールドのセッターメソッド */
	public void setBookdataType(BookdataType type) {
		this.type = type;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	/* toStringメソッドのオーバライド */
	@Override
	public String toString() {
		String str;
		SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");

		if (this.type == BookdataType.INCOME) str = "収入";
		else str = "支出";
		return "タイプ："+ str
				+ ", 金額：" + this.amount
				+ ", 日付：" + f.format(this.day);
	}

	/* equalsメソッドのオーバライド */
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
		if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR) )return false;
		if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH) )return false;
		if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH) )return false;
		return true;
	}

	/* hashcodeメソッドのオーバライド */
	@Override
	public int hashCode() {
		int result = 37;
		result = result * 31 + this.type.hashCode();
		result = result * 31 + ((Integer)(this.amount)).hashCode();
		result = result * 31 + this.day.hashCode();
		return result;
	}
}

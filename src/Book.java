import java.util.ArrayList;
import java.util.Iterator;

public class Book {
	private ArrayList<Bookdata> book;

	public Book() {
		this.book = new ArrayList<Bookdata>();
	}

	/* 生成したBookdataインスタンスを日付順に整列されたLinkedListに追加するメソッド */
	public void addDataToList(Bookdata data) {
		if (this.book.isEmpty() == true) {
			this.book.add(data);
		} else if (data.getDay().after(this.book.get(this.book.size()-1).getDay())) {
			this.book.add(data);
		} else {
			for (int i = 0;i < this.book.size();i++) {
				if (data.getDay().before(this.book.get(i).getDay())) {
					this.book.add(i, data);
				}
			}
		}
	}

	/* ArrayListのデータ一覧を表示するメソッド */
	public void showDataAll () {
		if (book.isEmpty())
			System.out.println("データがありません");
		else {
			Iterator it = book.iterator();
			while (it.hasNext()) {
				System.out.println((Bookdata)it.next());
			}
		}
	}

	/* ゲッターメソッド */
	public ArrayList<Bookdata> getBook() {
		return this.book;
	}

	/* toStringメソッドのオーバライド */
	@Override
	public String toString() {
		showDataAll();
		return "";
	}

	/* equalsメソッドのオーバライド */
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null) return false;
		if (!(o instanceof Book)) return false;
		Book b = (Book)o;
		if (!this.book.equals(b.book))return false;
		return true;
	}

	/* hashcodeメソッドのオーバライド */
	@Override
	public int hashCode() {
		int result = 37;
		result = result * 31 + this.book.hashCode();
		return result;
	}
}

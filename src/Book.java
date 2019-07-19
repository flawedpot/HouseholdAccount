/**
 * 家計簿クラス。
 * 家計簿データをまとめて処理を行う。
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Book {
	/** 家計簿 */
	private ArrayList<Bookdata> book;

	public Book() {
		this.book = new ArrayList<Bookdata>();
	}

	/**
	 *  生成した家計簿データ(Bookdataインスタンス)を、日付順に整列された家計簿(book)に追加するメソッド。
	 *  @param data 家計簿データ
	 */
	public void addDataToList(Bookdata data) {
		/* bookが空のとき、末尾にデータを追加する */
		if (this.book.isEmpty() == true) {
			this.book.add(data);
		/* データの日付がbookの末尾のデータより新しい場合、末尾にデータを追加する */
		} else if (data.getDay().after(this.book.get(this.book.size()-1).getDay())) {
			this.book.add(data);
		/* 上記以外の場合、自分より新しいデータの前に自身を追加する */
		} else {
			for (int i = 0;i < this.book.size();i++) {
				if (data.getDay().before(this.book.get(i).getDay())) {
					this.book.add(i, data);
					break;
				}
			}
		}
	}

	/**
	 * CSVファイルからすべての家計簿データを読み込んでBookdataインスタンスを生成し、bookに格納するメソッド。
	 * CSVファイルに記載されているデータは、日付順に整列されている前提で処理している。
	 */
	public void inputData () {
		File file = new File("Book.csv");

		/* 読み込むファイル有無チェック */
		if (file.exists() == true) {
			FileReader fr = null;
			String str = null;
			String[] strs = null;
			SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");

			/* ファイルから1行読み込み、Bookdataインスタンスを生成してBookに格納する */
			try {
				fr = new FileReader("Book.csv");
				BufferedReader br = new BufferedReader(fr);
				while ((str = br.readLine()) != null) {
					strs = str.split(",");
					/* Bookdataインスタンス生成 */
					if (strs.length == 3) {
						this.book.add(new Bookdata(BookdataType.toBookdataType(Integer.parseInt(strs[0])),
											Integer.parseInt(strs[1]),
											(Date)f.parse(strs[2])));
					/* BookdataDetailインスタンス生成 */
					} else if (strs.length == 5) {
						this.book.add(new BookdataDetail(BookdataType.toBookdataType(Integer.parseInt(strs[0])),
											Integer.parseInt(strs[1]),
											(Date)f.parse(strs[2]),
											strs[3],
											strs[4]));
					}
				}
				System.out.println("ファイルの読み込みが完了しました");
			} catch (ParseException pe) {
				System.err.println("型変換に失敗しました");
				pe.printStackTrace();
			} catch (IOException ioe) {
				System.err.println("ファイル読み込み処理に失敗しました");
				ioe.printStackTrace();
			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (IOException ioe) {
						System.err.println("ファイルのクローズ処理に失敗しました");
						ioe.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Book.csvがありません");
		}
	}

	/**
	 * bookに格納されているすべてのデータをCSVファイルに書き出すメソッド。
	 */
	public void outputData () {
		FileWriter fw = null;
		Iterator it = this.book.iterator();

		try {
			fw = new FileWriter("Book.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			while (it.hasNext()) {
				bw.write(it.next().toString() + System.getProperty("line.separator"));
			}
			bw.flush();
			System.out.println("ファイルの書き込みが完了しました");
		} catch (IOException ioe) {
			System.err.println("ファイルの出力に失敗しました");
			ioe.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException ioe) {
					System.err.println("ファイルのクローズに失敗しました");
					ioe.printStackTrace();
				}
			}
		}
	}

	/**
	 * 家計簿(book)のゲッターメソッド。
	 * @return 家計簿
	 */
	public ArrayList<Bookdata> getBook() {
		return this.book;
	}

	/**
	 * toStringメソッドのオーバライド。
	 * 家計簿(book)に格納されている全データを文字列として返す。
	 * @return データタイプ,金額,入力日(,カテゴリ,メモ)・・・
	 */
	@Override
	public String toString() {
		StringBuilder sb  = new StringBuilder();
		if (book.isEmpty())
			sb.append("データがありません");
		else {
			Iterator it = book.iterator();
			while (it.hasNext()) {
				sb.append(it.next().toString());
				sb.append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();
	}

	/**
	 * equalsメソッドにオーバライド。
	 * @param o オブジェクト型変数
	 * @return 真偽値
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null) return false;
		if (!(o instanceof Book)) return false;
		Book b = (Book)o;
		if (!this.book.equals(b.book))return false;
		return true;
	}

	/**
	 * hashcodeメソッドのオーバライド。
	 * @return ハッシュ値
	 */
	@Override
	public int hashCode() {
		int result = 37;
		result = result * 31 + this.book.hashCode();
		return result;
	}
}

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int i = 4;
		Book book = new Book();

		while(i != 0) {
			System.out.println("実行したい操作を選択してください：");
			System.out.println("1：入力  2:一覧を表示する  3:結果を出力する 0:終了する");
			Scanner scMode = new Scanner(System.in);
			switch (i = scMode.nextInt()) {
			case 1:
				Bookdata data = null;
				data = selectInputType();		// Bookdataインスタンス生成
				data.AddBookdata();				// インスタンスの各フィールド値を入力
				book.addDataToList(data);		// インスタンスをArrayListに追加
				break;
			case 2:
				book.showDataAll();
				break;
			case 3:
				/* 後でつくる */
				break;
			default:
				System.out.println("プログラムを終了します");
			}
		}
	}

	/* 家計簿データを作成するときのフォーマットを選択するメソッド */
	public static Bookdata selectInputType() {
		Bookdata result = null;

		System.out.println("登録する項目のタイプを選択してください：");
		System.out.println("シンプル：0, カスタム：1");
		Scanner scInputType = new Scanner(System.in);
		if (scInputType.nextInt() != 1) {
			result = new Bookdata();
		} else {
			result = new BookdataDetail();
		}
		return result;
	}
}

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int i = 4;

		while(i != 0) {
			System.out.println("実行したい操作を選択してください：");
			System.out.println("1：入力  2:一覧を表示する  3:結果を出力する 0:終了する");
			Scanner sc = new Scanner(System.in);
			i = sc.nextInt();
			switch (i) {
			case 1:
				Bookdata data = new Bookdata();
				data.AddBookdata();
				System.out.println(data);
				break;
			case 2:
				/* 後でつくる */
				break;
			case 3:
				/* 後でつくる */
				break;
			default:
				System.out.println("プログラムを終了します");
			}
		}
	}
}

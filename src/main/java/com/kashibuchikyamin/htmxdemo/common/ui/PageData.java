package com.kashibuchikyamin.htmxdemo.common.ui;

import java.util.List;

import com.kashibuchikyamin.htmxdemo.common.enums.ValidType;

public record PageData<T>(
		int total,
		int startRowNumber,
		int onePageLimit,
		int currentPage,
		int totalPage,
		List<T> list) {
	/**
	 * 未検索であるか
	 * @return
	 */
	public boolean isUnsearched() {
		if (list == null) {
			return true;
		}
		return false;
	}

	/**
	 * 未検索時の表のデータを取得
	 * @return
	 */
	public static PageData<Object> get未検索データ() {
		return new PageData<Object>(0, 0, 0, 0, 0, null);
	}

	// ページのリンクのリストを作成するメソッドを作る。
	// ルールは以下の通り。
	// - totalPageが0以下の場合は、無効なリンクのみで<< < 1 > >>のリンクを作る。
	// - totalPageが1の場合は、<< < 1 > >>のリンクを作る。
	// - totalPageが2以上4以下の場合は、<< < 1からtotalPage > >>のリンクを作る。
	// - totalPageが5以上の場合は以下のルールとする
	//   - currentPageが3以下の場合は、<< < 1 2 3 4 … > >>のリンクを作る。
	//   - currentPageがtotalPage-2以上の場合は、<< < … totalPage-3 totalPage-2 totalPage-1 totalPage > >>のリンクを作る。
	//   - 上記2つの両方に一致する場合は、<< < 1 2 3 4 … > >>のリンクを作る。
	//   - 上記2つのどちらにも一致しない場合は、<< < … currentPage-1 currentPage currentPage+1 … > >>のリンクを作る。
	// なお、currentPageが1未満、またはtotalPageを超える場合は、currentPageを1またはtotalPageに設定する。
	// また、currentPageが1の場合は、<<と<のリンクを無効にする。
	// currentPageがtotalPageの場合は、>>と>のリンクを無効にする。
	// ループを使ってリンクを作成すること。
	/**
	 * ページリンクを取得
	 * @return
	 */
	public List<PageLink> getPageLinks() {
		return List.of(
				PageLink.getFirst(ValidType.有効),
				PageLink.getPrev(currentPage <= 1 ? 1 : currentPage - 1, ValidType.有効),
				PageLink.getPage(1, currentPage == 1 ? ValidType.無効 : ValidType.有効),
				PageLink.getPage(2, currentPage == 2 ? ValidType.無効 : ValidType.有効),
				PageLink.getPage(3, currentPage == 3 ? ValidType.無効 : ValidType.有効),
				PageLink.getPage(4, currentPage == 4 ? ValidType.無効 : ValidType.有効),
				PageLink.getOmit(),
				PageLink.getPage(totalPage - 1, currentPage == totalPage - 1 ? ValidType.無効 : ValidType.有効),
				PageLink.getPage(totalPage, currentPage == totalPage ? ValidType.無効 : ValidType.有効),
				PageLink.getNext(currentPage >= totalPage ? totalPage : currentPage + 1, ValidType.有効),
				PageLink.getLast(totalPage, ValidType.有効)
		);
	}
	
	// ページのリンク用の値を保持するinner static クラスを作る。
	// メンバーは表示用の文字("<<", "<", "1", "2", "3", "…", ">", ">>")と、リンク先に指定するページ番号を保持する。
	// なお、"…"は省略記号であり、リンク先には指定しない。
	// また、リンク先に指定するページ番号は、1からtotalPageまでの値を保持する。
	// これにより、リンク先に指定するページ番号が1未満、またはtotalPageを超える場合は、リンク先に指定しない。
	// このinner static クラスのインスタンスを持つListを返すメソッドを作る。
	// フィールドにリンクを作らないことを示すboolean型のフィールドを追加する。
	/**
	 * ページリンク
	 */
	public static class PageLink {
		private String text;
		private int page;
		private boolean isLink;

		private PageLink(String text, int page, boolean isLink) {
			this.text = text;
			this.page = page;
			this.isLink = isLink;
		}

		/**
		 * 表示用の文字を取得
		 * @return
		 */
		public String getText() {
			return text;
		}

		/**
		 * ページ番号を取得
		 * @return
		 */
		public int getPage() {
			return page;
		}

		/**
		 * リンクを作成するか
		 * @return
		 */
		public boolean isLink() {
			return isLink;
		}

		// 以下のインスタンスを返すstaticメソッドをそれぞれ作る
		// - "<<"と1を返すメソッド
		// - "<"と指定したページ番号を返すメソッド
		// - ">"と指定したページ番号を返すメソッド
		// - ">>"と指定したページ番号を返すメソッド
		// - "…"とリンクを作らないことを指定した状態を返すメソッド
		// - カレントのページ番号で無効なリンクを作成するメソッド
		/**
		 * 最初のページ「<<」へのリンクを取得
		 * @param validType 有効/無効区分
		 * @return PageLink インスタンス
		 */
		public static PageLink getFirst(ValidType validType) {
			return new PageLink("<<", 1, validType.is有効());
		}

		/**
		 * 前のページ「<<」へのリンクを取得
		 * @param page ページ番号
		 * @param validType 有効/無効区分
		 * @return PageLink インスタンス
		 */
		public static PageLink getPrev(int page, ValidType validType) {
			return new PageLink("<", page, validType.is有効());
		}

		/**
		 * 次のページ「>」へのリンクを取得
		 * @param page ページ番号
		 * @param validType 有効/無効区分
		 * @return	PageLink インスタンス
		 */
		public static PageLink getNext(int page, ValidType validType) {
			return new PageLink(">", page, validType.is有効());
		}

		/**
		 * 最後のページ「>>」へのリンクを取得
		 * @param page ページ番号
		 * @param validType 有効/無効区分
		 * @return PageLink インスタンス
		 */
		public static PageLink getLast(int page, ValidType validType) {
			return new PageLink(">>", page, validType.is有効());
		}

		/**
		 * 省略記号「…」を取得
		 * @return
		 */
		public static PageLink getOmit() {
			return new PageLink("…", 0, false);
		}

		/**
		 * 指定のページ番号で無効なリンクを作成
		 * @param page ページ番号
		 * @return
		 */
		public static PageLink getInvalid(int page) {
			return new PageLink(String.valueOf(page), page, false);
		}

		/**
		 * ページ番号を取得
		 * @param page ページ番号
		 * @param validType 有効/無効区分
		 * @return
		 */
		public static PageLink getPage(int page, ValidType validType) {
			return new PageLink(String.valueOf(page), page, validType.is有効());
		}
	}
}

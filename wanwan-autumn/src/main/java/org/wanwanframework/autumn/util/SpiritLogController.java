package org.wanwanframework.autumn.util;

import java.util.logging.Logger;

/**
 * 添加log的工具类
 * @author coco
 *
 */
@SuppressWarnings("unused")
public class SpiritLogController {

	private static final Logger logger = Logger.getLogger("" + SpiritLogController.class);

	private String className;

	private String word = "private static final Logger logger = Logger.getLogger(\"\" + @className.class);";

	private String getContent(String className, String word) {
		return word.replaceAll(className, word);
	}

	private boolean isHave(String word, String content) {
		if (content.indexOf(word) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

	}
}

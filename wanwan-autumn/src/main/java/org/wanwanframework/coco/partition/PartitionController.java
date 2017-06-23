package org.wanwanframework.coco.partition;

import org.wanwanframwork.file.core.FileController;

/**
 * 切分神器：用于将一些复杂的构建切分成单独的元件
 * 	这些元件最后可以组合成一个有用的模块。
 * 切分只是在逻辑意义上的切分、最后的效果是一些单独的元件能够逐步组合，比如
 * 	datasource + mybatis = jdbc 
 * @author coco
 *
 */
public class PartitionController extends FileController<String>{

	public PartitionController() {

	}
	
	@Override
	protected void process() {
		content = "...vvv...";
	}
	
	public static void main(String[] args) {
		PartitionController.call(new PartitionController());
	}
}

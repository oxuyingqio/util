package cn.xuyingqi.utils.tool.operate.file.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import cn.xuyingqi.utils.tool.operate.file.OperateFile;

/**
 * 复制文件内容到指定的文件中
 * 
 * @author Administrator
 *
 */
public class CopyFileContent2SpecifiedFile implements OperateFile {

	// 日志
	private Logger logger = Logger.getLogger(OperateFile.class);

	// 指定的文件
	private File specifiedFile;

	/**
	 * 复制文件内容到指定的文件中
	 * 
	 * @param specifiedFile
	 *            指定的文件
	 */
	public CopyFileContent2SpecifiedFile(File specifiedFile) {
		this.specifiedFile = specifiedFile;
	}

	@Override
	public void operateFile(File file) {
		// 文件不为空,且存在
		if (file != null && file.exists()) {
			try {
				// 输入流
				FileInputStream fis = new FileInputStream(file);
				// 输出流,且为追加
				FileOutputStream fos = new FileOutputStream(this.specifiedFile, true);
				// 读取字节
				int temp;
				while ((temp = fis.read()) != -1) {
					fos.write(temp);
				}
				// 写入一个换行
				fos.write(System.getProperty("line.separator").getBytes());
				// 关闭
				fis.close();
				fos.close();
			} catch (FileNotFoundException e) {
				this.logger.warn("文件：" + file.getName() + "，未找到");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

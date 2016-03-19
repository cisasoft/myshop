package net.shopxx.util;

import java.io.File;

/**
 * 本工具目的是将非mvn项目转换成maven管理项目 使用方法，将jar包放入对应路径，生成call
 * mvn的Windows批处理命令，放入Windows中执行 第二步，将生成pom的引用dependency脚本复制入
 * 
 * @author Daniel
 * 
 */
public class GenerateMvnShell {

	public static void main(String[] args) {
		String path = "C:/Users/Niexl/Workspaces/MyEclipse for Spring 2014/myshop/src/main/webapp/WEB-INF/lib";// jar包的存放路径
		ListFile(path);
	}

	public static void ListFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						// System.out.println("文件夹:" + file2.getAbsolutePath());
						ListFile(file2.getAbsolutePath());
					} else {
						// System.out.println("文件:" + file2.getAbsolutePath());

						String cmd = "call mvn install:install-file \"-Dfile="
								+ file2.getAbsolutePath()
								+ "\" \"-DgroupId=net.shopxx\" \"-DartifactId="
								+ file2.getName().substring(0,
										file2.getName().lastIndexOf(".jar"))
								+ "\" \"-Dversion=3.0\" \"-Dpackaging=jar\"";
						System.out.println(cmd);

						// String pom = "<dependency>\n"
						// + " <groupId>net.shopxx</groupId>\n"
						// + " <artifactId>"
						// + file2.getName().substring(0,
						// file2.getName().lastIndexOf(".jar"))
						// + "</artifactId>\n"
						// + " <version>3.0</version>\n" + "</dependency>";
						// System.out.println(pom);
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
	}

}

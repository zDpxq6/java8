package ch08.ex10;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Demo {
	public static void main(String[] args) throws IOException {
		Path destPath = unzip("./src.zip", "./javasrc");
		try(Stream<Path> entries = Files.walk(destPath, FileVisitOption.FOLLOW_LINKS)){
			Stream<Path> result = entries.filter(e -> {
				File file = e.toFile();
				try{
					boolean hasTransient = false;
					boolean hasVolatile = false;
					BufferedReader br = new BufferedReader(new FileReader(file));
					String str = br.readLine();
					while(str != null){
						if(str.contains("transient")){
							hasTransient = true;
						}
						if(str.contains("volatile")){
							hasVolatile = true;
						}
						if(hasTransient && hasVolatile){
							return true;
						}
						str = br.readLine();
					}
					return false;
				}catch(IOException ex){
					return false;
				}
			});
		}
	}

	/**
	 * Zipファイルの
	 *
	 * @param zipFilePath
	 *            zipファイル
	 * @param destiationPath
	 *            出力先ディレクトリ
	 * @throws IOException
	 */
	public static Path unzip(String zipFilePath, String destiationPath) throws IOException {

		File dest = new File(destiationPath);
		dest.mkdirs();

		File src = new File(zipFilePath);
		if (!src.exists()) {
			throw new IOException();
		}

		try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(src)))) {

			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					String relativePath = entry.getName();
					dest = new File(dest, relativePath);
					dest.mkdirs();
				} else {
					String relativePath = entry.getName();
					File outFile = new File(dest, relativePath);
					// 出力先のディレクトリを作成する
					File parentFile = outFile.getParentFile();
					parentFile.mkdirs();
					try (OutputStream os = new BufferedOutputStream(new FileOutputStream(outFile))) {
						byte[] buf = new byte[256];
						int size = 0;
						while ((size = zis.read(buf)) > 0) {
							os.write(buf, 0, size);
						}
					}
				}
				zis.closeEntry();
			}
		}
		return dest.toPath();
	}
}

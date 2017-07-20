package top.lrshuai.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class UploadUtil {

	/**
	 * 创建文件，如果文件夹不存在将被创建
	 * 
	 * @param destFileName
	 *            文件路径
	 */
	public static File createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists())
			return null;
		if (destFileName.endsWith(File.separator))
			return null;
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs())
				return null;
		}
		try {
			if (file.createNewFile())
				return file;
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 保存图片通过url
	 * 
	 * @param urlString
	 * @param filename
	 * @throws Exception
	 */
	public static void saveImgByUrl(String urlString, String filename) throws Exception {
		createFile(filename);
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		OutputStream os = new FileOutputStream(filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 切割图片
	 * 
	 * @param input
	 * @param result
	 *            目标路径
	 * @param x
	 * @param y
	 * @param w
	 *            目标宽度
	 * @param h
	 *            目标高
	 * @param isPNG
	 *            是否生成.png
	 * @throws Exception
	 */
	public static void crop(InputStream input, String result, int x, int y, int w, int h, boolean isPNG)
			throws Exception {
		try {
			createFile(result);
			BufferedImage srcImg = ImageIO.read(input);
			int tmpWidth = srcImg.getWidth();
			int tmpHeight = srcImg.getHeight();
			int xx = Math.min(tmpWidth - 1, x);
			int yy = Math.min(tmpHeight - 1, y);

			int ww = w;
			if (xx + w > tmpWidth) {
				ww = Math.max(1, tmpWidth - xx);
			}
			int hh = h;
			if (yy + h > tmpHeight) {
				hh = Math.max(1, tmpHeight - yy);
			}

			BufferedImage dest = srcImg.getSubimage(xx, yy, ww, hh);

			BufferedImage tag = new BufferedImage(w, h,
					isPNG ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(dest, 0, 0, null);
			ImageIO.write(tag, isPNG ? "png" : "jpg", new FileOutputStream(new File(result)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	/**
	 * 上图文件
	 * @param filePath 文件名 
	 * @param in   io流
	 * @return  返回最终的路径
	 * @throws IOException 
	 */
	public static String uploadImg(String filePath,InputStream in) throws IOException{
		//获取tomcat 的根目录（webapps 下的路径），editormd.root 在web.xml 中配置
		String tomcatRootPath = System.getProperty("editormd.root")+"../";
		String resultPath = tomcatRootPath + filePath;
		createFile(resultPath);
		File realFile =new File(resultPath);
		FileUtils.copyInputStreamToFile(in, realFile);
		return resultPath;
	}

	public static void main(String[] args) {
		try {
			long bet2 = System.currentTimeMillis();
			saveImgByUrl("http://lrshuai.top/upload/user/default.png", "d://ddd/aaa/" + Myutil.random(8) + ".jpg");
			System.out.println("end=" + (System.currentTimeMillis() - bet2) + " ms");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
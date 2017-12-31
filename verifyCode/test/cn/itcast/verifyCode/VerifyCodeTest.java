package cn.itcast.verifyCode;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.yp.verifyCode.VerifyCode;

public class VerifyCodeTest {

	@Test
	public void test() throws FileNotFoundException {
		VerifyCode vc = new VerifyCode();
		
		BufferedImage image = vc.getImage();
		System.out.println(vc.getText());
		VerifyCode.imageOutput(image, new FileOutputStream("D:/aa.jpg"));
		
	}

}

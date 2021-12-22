package imagedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

@RestController
public class Chessboard {

	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		arrayHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.IMAGE_PNG));
		return arrayHttpMessageConverter;
	}

	@GetMapping
	@ResponseBody
	public void getImageAsByteArray(HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.IMAGE_PNG_VALUE);

		final var bufferedImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		for (var x = 0; x < 1000; x++) {
			for (var y = 0; y < 1000; y++) {
				var color = (Math.round(0.1d * x) + Math.round(0.1d * y)) % 2 == 0 ? Color.YELLOW : Color.BLACK;
				bufferedImage.setRGB(x, y, color.getRGB());
			}
		}

		response.getOutputStream().write(writeBufferedImageToByteArray(bufferedImage));
	}

	private static byte[] writeBufferedImageToByteArray(final BufferedImage bufferedImage) throws IOException {
		var byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}

}

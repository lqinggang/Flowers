package com.lqinggang.common.controller;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LQingGang
 * @time 2018年3月28日 - 下午9:47:28
 */
@Controller
public class RandomCodeController {

	public RandomCodeController() {
	}

	// @Resource
	@Autowired()
	@Qualifier("imageCodeUtil")
	private ImageCodeUtil imageCodeUtil;

	@RequestMapping("/ran/random")
	public void genericRandomCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/png");
		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			ImageIO.write(imageCodeUtil.getImage(request), "png", sos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sos.close();
	}
}

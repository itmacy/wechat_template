package com.itmacy.dev.module.officialaccount.controller;

import com.itmacy.dev.base.response.GlobalEnum;
import com.itmacy.dev.base.response.ResponseData;
import com.itmacy.dev.base.response.ResponseDataUtil;
import com.itmacy.dev.module.officialaccount.AccessTokenThread;
import com.itmacy.dev.module.officialaccount.service.CoreService;
import com.itmacy.dev.module.officialaccount.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号入口控制器
 *
 * 当微信公众号使用开发者模式时，需要在后台配置暴露出去的接口
 *
 * Created by itmacy on 2019/9/25.
 */
@RestController
@Slf4j
public class IndexController extends BaseController {

	@Resource
	private CoreService coreService;
	@Resource
	private AccessTokenThread accessTokenThread;


	/**
	 * 暴露给公众号接口
	 * 验证数据是否来自公众号后台
	 * @param paramMap
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/wechat")
	public void index(@NotNull @RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response){

		logger.error("收到请求，请求数据为："+paramMap.toString());

		String signature = (String) paramMap.get("signature");
		String timestamp = (String) paramMap.get("timestamp");
		String nonce = (String) paramMap.get("nonce");
		String echostr = (String) paramMap.get("echostr");

		try {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				PrintWriter out = response.getWriter();
				out.print(echostr);
				out.close();
			} else {
				System.out.println("这里存在非法请求！");
				log.error("checkSignature失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("checkSignature失败：{}",e.getMessage());
		}
	}

	/**
	 * 暴露给公众号接口
	 * 调用核心业务类接收消息、处理消息跟推送消息
	 * @param req
	 * @return
	 */

    @PostMapping(value = "/wechat")
    public String post(HttpServletRequest req){

        String respMessage = coreService.processRequest(req);
		logger.info("respMessage: "+respMessage);

        return respMessage;
    }



	/**
	 * 判断用户是否订阅公众号
	 * @param openId
	 * @return
	 */
	@GetMapping("/getUserIsSubscribe/{openId}")
	public ResponseData getUserIsSubscribe(@PathVariable String openId){
		logger.info("openId:"+openId);
		if (StringUtils.isEmpty(openId)){
			return ResponseDataUtil.response(GlobalEnum.parameter_error);
		}

		Boolean userSubscribe = coreService.isUserSubscribe(openId);
		if (userSubscribe != null) {
			Map<String, Object> data = new HashMap<>();

			if (userSubscribe) {
				data.put("isSubscribe",true);
			}else {
				data.put("isSubscribe",false);
			}

			return ResponseDataUtil.response(GlobalEnum.success,data);
		}

		return ResponseDataUtil.response(GlobalEnum.query_fail);

	}

	/**
	 * 手动刷新accessToken
	 * @return
	 */
	@GetMapping("/refreshAccessToken")
	public String refreshAccessToken(){
		return accessTokenThread.getRefreshToken();
	}
}

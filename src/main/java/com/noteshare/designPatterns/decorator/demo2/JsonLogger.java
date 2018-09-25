package com.noteshare.designPatterns.decorator.demo2;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 以看到，在JsonLogger中，对于Logger的各种接口，我都用JsonObject对象进行一层封装。在打印的时候，最终还是调用原生接口logger.
 * error(string)， 只是这个string参数已经被我们装饰过了。如果有额外的需求，我们也可以再写一个函数去实现。
 * 比如error(Exception e)，只传入一个异常对象，这样在调用时就非常方便了。
 * 另外，为了在新老交替的过程中尽量不改变太多的代码和使用方式。我又在JsonLogger中加入了一个内部的工厂类JsonLoggerFactory
 * （这个类转移到DecoratorLogger中可能更好一些），他包含一个静态方法，用于提供对应的JsonLogger实例。最终在新的日志体系中，
 * 使用方式如下：
 * private static final Logger logger = JsonLoggerFactory.getLogger(Component.class); logger.error(string);
 * 他唯一与原先不同的地方，就是LoggerFactory -> JsonLoggerFactory，这样的实现，也会被更快更方便的被其他开发者接受和习惯。
 */
public class JsonLogger extends DecoratorLogger {
	public JsonLogger(Logger logger) {
		super(logger);
	}

	@Override
	public void info(String msg) {
		JSONObject result = composeBasicJsonResult();
		result.put("MESSAGE", msg);
		logger.info(result.toString());
	}

	@Override
	public void error(String msg) {
		JSONObject result = composeBasicJsonResult();
		result.put("MESSAGE", msg);
		logger.error(result.toString());
	}

	public void error(Exception e) {
		JSONObject result = composeBasicJsonResult();
		result.put("EXCEPTION", e.getClass().getName());
		String exceptionStackTrace = ExceptionUtils.getStackTrace(e);
		result.put("STACKTRACE", exceptionStackTrace);
		logger.error(result.toString());
	}

	public static class JsonLoggerFactory {
		@SuppressWarnings("rawtypes")
		public static JsonLogger getLogger(Class clazz) {
			Logger logger = LoggerFactory.getLogger(clazz);
			return new JsonLogger(logger);
		}
	}

	private JSONObject composeBasicJsonResult() {
		// 拼装了一些运行时信息
		return null;
	}
}

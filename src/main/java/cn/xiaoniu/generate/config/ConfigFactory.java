package cn.xiaoniu.generate.config;

import cn.xiaoniu.generate.utils.ConfigFileUtils;

import com.rits.cloning.Cloner;

public class ConfigFactory {

	public static Config config = null;

	private static Config primitive = null;
	
	public static void init(String configFile){
		primitive = ConfigFileUtils.getConfig(configFile);
		reset();
	}

	public static void reset() {
		Cloner cloner = new Cloner();
		config = cloner.deepClone(primitive);
	}

}

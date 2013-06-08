package org.cxt.lt.util;
import java.io.File;

public class LT
{
	public static String getFansUrl(String weiboId)
	{
		return "http://weibo.com/" + weiboId + "/fans";
	}

	public static String getFollowsUrl(String weiboId)
	{
		return "http://weibo.com/" + weiboId + "/follow";
	}

	public static String getCurrentFolderPath()
	{
		ClassLoader classLoader = LT.class.getClassLoader();
		File classpathRoot = new File(classLoader.getResource("").getPath());
		return classpathRoot.getAbsolutePath();
	}

	public static void notNull(Object url)
	{
		if (null == url)
		{
			throw new RuntimeException("object is null.");
		}
	}
	
	public static void assertTrue(boolean bool)
	{
		if (!bool)
		{
			throw new RuntimeException();
		}
	}
}

package com.shaklee.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * Loads and caches files from the classpath.
 * 
 * @author Elli Albek
 */
public class ClasspathFileLoader {

	private static final Charset utf8 = Charset.forName("utf8");

	private final Class<?> clazz;
	private Map<String, String> fileCache = new ConcurrentHashMap<String, String>();

	public ClasspathFileLoader(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String get(String name) {
		String res = fileCache.get(name);
		if (res == null) {
			try {
				res = load(name, clazz);
				res = removeComments(res);
			} catch (IOException e) {
				throw new RuntimeException("Error loading " + name, e);
			}
			fileCache.put(name, res);
		}
		return res;
	}

	public String load(String name) throws IOException {
		return load(name, clazz);
	}

	public static String load(String name, Class<?> c) throws IOException {
		InputStream in = c.getResourceAsStream(name);
		if (in == null)
			throw new FileNotFoundException(name);
		try {
			InputStreamReader r = new InputStreamReader(in, utf8);
			StringWriter out = new StringWriter();
			char[] buff = new char[1024];
			int i = -1;

			while ((i = r.read(buff)) > -1) {
				out.write(buff, 0, i);
			}
			return out.toString();
		} finally {
			in.close();
		}
	}

	public static final String removeComments(String s) {
		return s.replaceAll("\\s*//.*", "");
	}

	private static final Pattern READ_ONLY_PATTERN = Pattern.compile(
			"\\s+for\\s+read\\s+only$", Pattern.MULTILINE
					| Pattern.CASE_INSENSITIVE);

	public static String loadSqlFile(Class<?> c, String name) {
		String sql;
		try {
			sql = load(name, c);
		} catch (IOException e) {
			throw new RuntimeException("Error loading " + name, e);
		}
		// remove comments
		sql = cleanSql(sql);

		return sql;
	}

	public static String cleanSql(String sql) {
		sql = sql.replaceAll("\\s*//.*", "").replaceAll("\\s*--.*", "").trim();
		// Add for read only on selects
		if (sql.regionMatches(true, 0, "select", 0, 6)
				&& (READ_ONLY_PATTERN.matcher(sql).find() == false))
			sql += "\nFOR READ ONLY";
		return sql;
	}
}

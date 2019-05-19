package FirstGrammar.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public abstract class InputStreamUtils {
	
	public static InputStream streamFromString(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}
}

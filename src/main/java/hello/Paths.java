package hello;

import java.util.ArrayList;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class Paths {

	public static Predicate<String> paths(final String... paths) {
		switch (paths.length) {
		case 0:
			return Predicates.alwaysFalse();
		case 1:
			return path(paths[0]);
		default:
			// many
			ArrayList<Predicate<String>> predicates = new ArrayList<Predicate<String>>(paths.length);
			for (String path : paths)
				predicates.add(path(path));
			return Predicates.or(predicates);
		}
	}

	static Predicate<String> path(final String path) {
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + path);
		if (path.endsWith("/*"))
			return new Predicate<String>() {
				final String prefix = path.substring(0, path.length() - 1);

				@Override
				public boolean apply(String input) {
					return input.startsWith(prefix);
				}
			};
		if (path.indexOf('*') > 0)
			throw new IllegalArgumentException("Path can either be static or end with /*");

		return Predicates.equalTo(path);
	}
}

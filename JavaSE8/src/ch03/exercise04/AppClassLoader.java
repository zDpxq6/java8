package ch03.exercise04;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class AppClassLoader {
	public static Set<Class<?>> listClasses(String packageName) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		JavaFileManager jfm = compiler.getStandardFileManager(new DiagnosticCollector<>(), null, null);

		Set<JavaFileObject.Kind> kind = new HashSet<JavaFileObject.Kind>() {
			{
				add(JavaFileObject.Kind.CLASS);
			}
		};

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		Iterable<JavaFileObject> iterable = jfm.list(StandardLocation.CLASS_PATH, packageName, kind, false);
		Stream<JavaFileObject> stm = StreamSupport.stream(iterable.spliterator(), false);
		Stream<String> stm2 = stm.map(javaFileObject -> javaFileObject.toUri().toString());
		Stream<String> stm3 = stm2.map(uri -> uri.replace("/", "."));
		Stream<String> stm4 = stm3.map(dottedUri -> dottedUri.substring(dottedUri.indexOf(packageName)).replaceAll(".class$", ""));
		Stream<Class<?>> stm5 = stm4.map(fqcn -> uncheckCall(() -> classLoader.loadClass(fqcn)));
		return stm5.collect(Collectors.toSet());
		return StreamSupport.stream(iterable.spliterator(), false).map(javaFileObject -> javaFileObject.toUri().toString()).map(uri -> uri.replace("/", ".")).map(dottedUri -> dottedUri.substring(dottedUri.indexOf(packageName)).replaceAll(".class$", ""))
				.map(fqcn -> uncheckCall(() -> classLoader.loadClass(fqcn))).collect(Collectors.toSet());
	}
}
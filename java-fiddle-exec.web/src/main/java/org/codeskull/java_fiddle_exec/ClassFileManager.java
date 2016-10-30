package org.codeskull.java_fiddle_exec;

import java.io.IOException;
import java.security.SecureClassLoader;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaFileObject.Kind;

@SuppressWarnings("rawtypes")
public class ClassFileManager extends ForwardingJavaFileManager {

    private JavaClassObject jclassObject;

    @SuppressWarnings("unchecked")
	public ClassFileManager(StandardJavaFileManager
            standardManager) {
        super(standardManager);
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return new SecureClassLoader() {
            @Override
            protected Class<?> findClass(String name)
            throws ClassNotFoundException {
                byte[] b = jclassObject.getBytes();
                return super.defineClass(name, jclassObject
                        .getBytes(), 0, b.length);
            }
        };
    }

    public JavaFileObject getJavaFileForOutput(Location location,
            String className, Kind kind, FileObject sibling)
    throws IOException {
        jclassObject = new JavaClassObject(className, kind);
        return jclassObject;
    }
    
}
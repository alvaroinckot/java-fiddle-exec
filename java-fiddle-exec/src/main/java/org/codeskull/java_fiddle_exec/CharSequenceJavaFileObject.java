package org.codeskull.java_fiddle_exec;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.tools.SimpleJavaFileObject;

public class CharSequenceJavaFileObject extends SimpleJavaFileObject {

    private CharSequence content;

    public CharSequenceJavaFileObject(String className,
        CharSequence content) throws UnsupportedEncodingException {
        super(URI.create("string:///" + URLEncoder.encode(  className.replace('.', '/') , "UTF8" )
            + Kind.SOURCE.extension), Kind.SOURCE);
        this.content = content;
    }

    public CharSequence getCharContent(
        boolean ignoreEncodingErrors) {
        return content;
    }
}
package com.e.hika.ex.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {
//    public PluginClassLoader(URL[] urls) {
//        super(urls);
//    }


    public PluginClassLoader(URL jarUrl) {
        super(new URL[]{jarUrl},null);
    }
}

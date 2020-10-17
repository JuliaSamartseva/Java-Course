package org.example;

import org.example.loader.CustomClassLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        CustomClassLoader loader = new CustomClassLoader(App.class.getClassLoader());
        Class testClass = loader.loadClass("org.example.Test");
    }
}

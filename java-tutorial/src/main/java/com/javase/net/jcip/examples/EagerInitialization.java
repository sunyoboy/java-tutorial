package com.javase.net.jcip.examples;

import com.javase.net.jcip.annotations.*;

/**
 * EagerInitialization
 * <p/>
 * Eager initialization
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
        public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }

    static class Resource {
    }
}

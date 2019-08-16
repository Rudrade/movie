package com.example.movie;

import com.example.movie.resources.MovieResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

// Root url for the application
@ApplicationPath("/")
public class App extends Application {

    // Method to map the restful resources
    // @return the set of mapped resources
    @Override
    public Set<Class<?>> getClasses() {
        HashSet hashSet = new HashSet<Class<?>>();
        hashSet.add(MovieResource.class);
        return hashSet;
    }
}
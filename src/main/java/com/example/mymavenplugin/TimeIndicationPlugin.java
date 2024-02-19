package com.example.mymavenplugin;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.time.LocalDateTime;
import java.util.List;

@Mojo(name = "TimeAndDependencyCountIndicationPlugin", defaultPhase = LifecyclePhase.COMPILE)
public class TimeIndicationPlugin extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "scope")
    String scope;

    @Override
    public void execute() {
        List<Dependency> dependencies = project.getDependencies();
        long numDependencies = dependencies.stream()
                .filter(d -> (scope == null || scope.isEmpty()) || scope.equals(d.getScope()))
                .count();
        getLog().info("Number of dependencies: " + numDependencies);
        System.out.println("*** current time:" + LocalDateTime.now());
    }

}

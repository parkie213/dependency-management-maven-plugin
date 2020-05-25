package io.jimothy;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import javax.inject.Inject;
import java.util.List;

@Mojo(name = "slim-dependency-management", defaultPhase = LifecyclePhase.INITIALIZE)
public class SlimDependencyManagementMojo extends AbstractMojo {

    @Inject
    private DependencyManagementComparisonService service;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<Dependency> extraDependencies = service.compareDependencyManagement(
                project.getDependencyManagement().getDependencies(), project.getDependencies());
        getLog().info("Extra Dependencies: ");
        extraDependencies.forEach(d -> getLog().info(d.toString()));
    }
}

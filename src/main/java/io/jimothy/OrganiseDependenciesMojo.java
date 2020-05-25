package io.jimothy;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "organise-dependencies", defaultPhase = LifecyclePhase.INITIALIZE)
public class OrganiseDependenciesMojo extends AbstractMojo {

    @Parameter( property = "project.groupId", required = true)
    private String groupId;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<Dependency> organisedDependencies = new OrganisedDependencyListBuilder(groupId)
                .add(project.getDependencies())
                .build();
        getLog().info("Dependencies: \n" + DependencyPrinter.printDependencies(organisedDependencies));
    }
}

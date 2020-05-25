package io.jimothy;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.logging.Log;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static io.jimothy.DependencyUtil.DEPENDENCY_COMPARATOR;

/**
 * Compares two lists of Dependencies, returning the extra Dependencies in the first list.
 *
 * TODO its not particularly efficient
 */
public class DependencyManagementComparisonService {

    @Inject
    public DependencyManagementComparisonService() {

    }

    public List<Dependency> compareDependencyManagement(List<Dependency> dependencyManagement, List<Dependency> dependencies) {
        List<Dependency> dependencyManagementCopy = newArrayList(dependencyManagement);
        dependencies.forEach(d -> removeIfMatches(dependencyManagementCopy, d));
        return dependencyManagementCopy;
    }

    public void removeIfMatches(List<Dependency> dependencyManagement, Dependency dependency) {
        dependencyManagement.removeIf(d -> DEPENDENCY_COMPARATOR.compare(d, dependency) == 0);
    }
}

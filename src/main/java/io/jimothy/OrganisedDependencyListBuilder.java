package io.jimothy;

import org.apache.maven.model.Dependency;
import org.jooq.lambda.Seq;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static io.jimothy.DependencyUtil.DEPENDENCY_COMPARATOR;
import static io.jimothy.DependencyUtil.isTestScope;

/**
 * Builds a list of dependencies by:
 * <p>
 * Internal Libraries
 * Internal Test Libraries
 * External Libraries
 * External Test Libraries
 * <p>
 * Inside each of those are organised alphabetically
 */
public class OrganisedDependencyListBuilder {

    private String groupId;

    private List<Dependency> internalLibraries;
    private List<Dependency> internalTestLibraries;
    private List<Dependency> externalLibraries;
    private List<Dependency> externalTestLibraries;

    public OrganisedDependencyListBuilder(String groupId) {
        this.groupId = groupId;
        internalLibraries = newArrayList();
        internalTestLibraries = newArrayList();
        externalLibraries = newArrayList();
        externalTestLibraries = newArrayList();
    }

    public OrganisedDependencyListBuilder add(Dependency dependency) {
        if (dependency.getGroupId().startsWith(groupId)) {
            if (isTestScope(dependency)) {
                internalTestLibraries.add(dependency);
            } else {
                internalLibraries.add(dependency);
            }
        } else {
            if (isTestScope(dependency)) {
                externalTestLibraries.add(dependency);
            } else {
                externalLibraries.add(dependency);
            }
        }
        return this;
    }

    public OrganisedDependencyListBuilder add(List<Dependency> dependencies) {
        dependencies.forEach(d -> add(d));
        return this;
    }

    public List<Dependency> build() {
        return Seq.ofType(sortedStream(internalLibraries), Dependency.class)
                .append(sortedStream(internalTestLibraries))
                .append(sortedStream(externalLibraries))
                .append(sortedStream(externalTestLibraries))
                .collect(Collectors.toList());
    }

    private Stream<Dependency> sortedStream(List<Dependency> toSort) {
        return toSort.stream().sorted(DEPENDENCY_COMPARATOR);
    }
}

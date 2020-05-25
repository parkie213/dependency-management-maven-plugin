package io.jimothy;

import com.google.common.base.Strings;
import org.apache.maven.model.Dependency;

import java.util.Comparator;


public class DependencyUtil {
    public static final String TEST_SCOPE = "test";

    public static final Comparator<Dependency> DEPENDENCY_COMPARATOR = Comparator.comparing(Dependency::getGroupId)
            .thenComparing(Dependency::getArtifactId)
            .thenComparing(Dependency::getVersion);

    public static boolean isTestScope(Dependency dependency) {
        return !Strings.isNullOrEmpty(dependency.getScope()) && dependency.getScope().equals(TEST_SCOPE);
    }
}

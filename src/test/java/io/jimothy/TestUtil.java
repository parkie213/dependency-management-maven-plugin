package io.jimothy;

import org.apache.maven.model.Dependency;

public class TestUtil {

    public static final Dependency DEPENDENCY_1 = getDependency("com.abc", "second", "1.5");
    public static final Dependency DEPENDENCY_2 = getDependency("org.def", "hello", "1.2");
    public static final Dependency DEPENDENCY_3 = getDependency("co.uk.xyz", "test", "2.0");
    public static final Dependency DEPENDENCY_4 = getDependency("com.abc", "first", "1.0", "test");
    public static final Dependency DEPENDENCY_5 = getDependency("com.yuv", "third", "1.0", "test");
    public static final Dependency DEPENDENCY_6 = getDependency("com.yuv", "chicken", "1.0");

    public static Dependency getDependency(String group, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(group);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        return dependency;
    }

    public static Dependency getDependency(String group, String artifactId, String version, String scope) {
        Dependency dependency = getDependency(group, artifactId, version);
        dependency.setScope(scope);
        return dependency;
    }
}

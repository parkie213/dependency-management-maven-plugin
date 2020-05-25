package io.jimothy;

import org.apache.maven.model.Dependency;

import java.util.List;

import static io.jimothy.DependencyUtil.isTestScope;

/**
 * Prints the dependencies of the POM.
 *
 */
public class DependencyPrinter {

    public static final String DEPENDENCY_OPENING_TAG = "<dependency>";
    public static final String DEPENDENCY_CLOSING_TAG = "</dependency>";
    public static final String GROUP_ID_OPENING_TAG = "<groupId>";
    public static final String GROUP_ID_CLOSING_TAG = "</groupId>";
    public static final String ARTIFACT_ID_OPENING_TAG = "<artifactId>";
    public static final String ARTIFACT_ID_CLOSING_TAG = "</artifactId>";
    public static final String VERSION_OPENING_TAG = "<version>";
    public static final String VERSION_CLOSING_TAG = "</version>";
    public static final String SCOPE_OPENING_TAG = "<scope>";
    public static final String SCOPE_CLOSING_TAG = "</scope>";
    public static final String FOUR_SPACES = "    ";
    public static final String NEWLINE = "\n";

    public static String printDependencies(List<Dependency> dependencies) {
        StringBuilder builder = new StringBuilder();
        dependencies.forEach(dependency -> {
            appendDependency(builder, dependency);
        });

        return builder.toString();
    }

    private static StringBuilder appendDependency(StringBuilder builder, Dependency dependency) {
        builder.append(DEPENDENCY_OPENING_TAG + NEWLINE)
                .append(FOUR_SPACES + GROUP_ID_OPENING_TAG + dependency.getGroupId() + GROUP_ID_CLOSING_TAG + NEWLINE)
                .append(FOUR_SPACES + ARTIFACT_ID_OPENING_TAG + dependency.getArtifactId() + ARTIFACT_ID_CLOSING_TAG + NEWLINE)
                .append(FOUR_SPACES + VERSION_OPENING_TAG + dependency.getVersion() + VERSION_CLOSING_TAG + NEWLINE);
        if (isTestScope(dependency)) {
            builder.append(FOUR_SPACES + SCOPE_OPENING_TAG + dependency.getScope() + SCOPE_CLOSING_TAG + NEWLINE);
        }
        return builder.append(DEPENDENCY_CLOSING_TAG + NEWLINE);
    }
}

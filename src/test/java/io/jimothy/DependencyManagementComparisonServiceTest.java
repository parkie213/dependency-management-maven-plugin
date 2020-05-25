package io.jimothy;

import com.google.common.collect.ImmutableList;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.jimothy.TestUtil.DEPENDENCY_1;
import static io.jimothy.TestUtil.DEPENDENCY_2;
import static io.jimothy.TestUtil.DEPENDENCY_3;
import static io.jimothy.TestUtil.DEPENDENCY_4;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class DependencyManagementComparisonServiceTest {

    private DependencyManagementComparisonService service = new DependencyManagementComparisonService(new SystemStreamLog());

    private List<Dependency> dependencyManagement = ImmutableList.of(DEPENDENCY_1, DEPENDENCY_2, DEPENDENCY_3, DEPENDENCY_4);
    private List<Dependency> dependencies = ImmutableList.of(DEPENDENCY_1, DEPENDENCY_2);

    @Test
    public void returnsExtraDependencies() {
        List<Dependency> extraDependencies = service.compareDependencyManagement(dependencyManagement, dependencies);

        assertThat(extraDependencies, containsInAnyOrder(DEPENDENCY_4, DEPENDENCY_3));
    }
}
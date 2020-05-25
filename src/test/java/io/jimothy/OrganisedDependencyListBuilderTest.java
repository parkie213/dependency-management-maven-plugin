package io.jimothy;

import com.google.common.collect.ImmutableList;
import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.jimothy.TestUtil.DEPENDENCY_1;
import static io.jimothy.TestUtil.DEPENDENCY_2;
import static io.jimothy.TestUtil.DEPENDENCY_3;
import static io.jimothy.TestUtil.DEPENDENCY_4;
import static io.jimothy.TestUtil.DEPENDENCY_5;
import static io.jimothy.TestUtil.DEPENDENCY_6;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrganisedDependencyListBuilderTest {

    private List<Dependency> expectedOrder = ImmutableList.of(DEPENDENCY_1, DEPENDENCY_4, DEPENDENCY_3,
            DEPENDENCY_6, DEPENDENCY_2, DEPENDENCY_5);
    private List<Dependency> expectedOrderWithoutGroup = ImmutableList.of(DEPENDENCY_3,
            DEPENDENCY_6, DEPENDENCY_2, DEPENDENCY_5);

    @Test
    public void organisesDependencies() {
        List<Dependency> actualOrder = new OrganisedDependencyListBuilder("com.abc")
                .add(DEPENDENCY_1)
                .add(DEPENDENCY_2)
                .add(DEPENDENCY_3)
                .add(DEPENDENCY_4)
                .add(DEPENDENCY_5)
                .add(DEPENDENCY_6)
                .build();

        assertThat(actualOrder, is(expectedOrder));
    }

    @Test
    public void organisesDependenciesPassingList() {
        List<Dependency> actualOrder = new OrganisedDependencyListBuilder("com.abc")
                .add(ImmutableList.of(DEPENDENCY_1, DEPENDENCY_2, DEPENDENCY_3, DEPENDENCY_4, DEPENDENCY_5, DEPENDENCY_6))
                .build();

        assertThat(actualOrder, is(expectedOrder));
    }

    @Test
    public void organisesDependenciesWithoutGroup() {
        List<Dependency> actualOrder = new OrganisedDependencyListBuilder("com.abc")
                .add(ImmutableList.of(DEPENDENCY_2, DEPENDENCY_3, DEPENDENCY_5, DEPENDENCY_6))
                .build();

        assertThat(actualOrder, is(expectedOrderWithoutGroup));
    }

}
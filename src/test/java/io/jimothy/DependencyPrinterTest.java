package io.jimothy;

import com.google.common.collect.ImmutableList;
import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.jimothy.TestUtil.DEPENDENCY_1;
import static io.jimothy.TestUtil.DEPENDENCY_2;
import static io.jimothy.TestUtil.DEPENDENCY_3;
import static io.jimothy.TestUtil.DEPENDENCY_4;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DependencyPrinterTest {

    private List<Dependency> dependencies;
    private String expectedXmlString = xmlString();

    @Test
    public void printsXmlListOfDependencies() {
        dependencies = ImmutableList.of(DEPENDENCY_1, DEPENDENCY_2, DEPENDENCY_3, DEPENDENCY_4);

        assertThat(DependencyPrinter.printDependencies(dependencies), is(expectedXmlString));
    }

    private String xmlString() {
        return "<dependency>\n" +
                "    <groupId>com.abc</groupId>\n" +
                "    <artifactId>second</artifactId>\n" +
                "    <version>1.5</version>\n" +
                "</dependency>\n" +
                "<dependency>\n" +
                "    <groupId>org.def</groupId>\n" +
                "    <artifactId>hello</artifactId>\n" +
                "    <version>1.2</version>\n" +
                "</dependency>\n" +
                "<dependency>\n" +
                "    <groupId>co.uk.xyz</groupId>\n" +
                "    <artifactId>test</artifactId>\n" +
                "    <version>2.0</version>\n" +
                "</dependency>\n" +
                "<dependency>\n" +
                "    <groupId>com.abc</groupId>\n" +
                "    <artifactId>first</artifactId>\n" +
                "    <version>1.0</version>\n" +
                "    <scope>test</scope>\n" +
                "</dependency>\n";
    }
}
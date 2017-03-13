package io.t28.json2java.idea.naming;

import com.intellij.openapi.project.Project;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.squareup.javapoet.TypeName;
import io.t28.json2java.idea.IdeaProjectTest;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParameterNamePolicyTest extends IdeaProjectTest {
    private ParameterNamePolicy underTest;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        final Project project = getProject();
        final JavaCodeStyleManager codeStyleManager = JavaCodeStyleManager.getInstance(project);
        underTest = new ParameterNamePolicy(codeStyleManager);
    }

    @Test
    public void convertShouldReturnLowerCamelText() throws Exception {
        // exercise
        final String actual = underTest.convert("Foo", TypeName.OBJECT);

        // verify
        assertThat(actual)
                .isEqualTo("foo");
    }

    @Test
    public void convertShouldAppendPrefixWhenReserved() throws Exception {
        // exercise
        final String actual = underTest.convert("Class", TypeName.OBJECT);

        // verify
        assertThat(actual)
                .isEqualTo("aClass");
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void convertShouldThrowExceptionWhenInvalidText() throws Exception {
        // verify
        assertThatThrownBy(() -> {
            // exercise
            underTest.convert("+", TypeName.OBJECT);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
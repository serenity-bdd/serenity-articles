package net.serenitybdd.tutorials.todomvctests.tasks;


import com.google.common.collect.Lists;
import net.serenitybdd.core.steps.Instrumented;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class StartWith {

    public static StartWithAnEmptyList anEmptyTodoList() {
        return instrumented(StartWithAnEmptyList.class);
    }

    public static StartWithATodoListContaining aTodoListContaining(String... todos) {
        return Instrumented.instanceOf(StartWithATodoListContaining.class)
                           .withProperties(Lists.newArrayList(todos));
    }
}

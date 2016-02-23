package net.serenitybdd.tutorials.todomvctests.tasks;

import com.google.common.collect.Lists;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class AddTodoItems implements Task {

    private final List<String> itemNames;

    @Step("{0} adds the items #itemNames")
    @Override
    public <T extends Actor> void performAs(T actor) {
        itemNames.forEach(
           item -> actor.attemptsTo(AddATodoItem.called(item))
        );
    }

    public AddTodoItems(List<String> itemNames) {
        this.itemNames = itemNames;
    }

    public static Task called(String... itemName) {
        return Instrumented.instanceOf(AddTodoItems.class).withProperties(Lists.newArrayList(itemName));
    }

}

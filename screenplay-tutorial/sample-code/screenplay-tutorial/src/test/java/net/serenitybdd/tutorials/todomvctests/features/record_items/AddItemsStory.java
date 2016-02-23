package net.serenitybdd.tutorials.todomvctests.features.record_items;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.tutorials.todomvctests.questions.TheTodoItems;
import net.serenitybdd.tutorials.todomvctests.tasks.AddATodoItem;
import net.serenitybdd.tutorials.todomvctests.tasks.StartWith;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@RunWith(SerenityRunner.class)
public class AddItemsStory {

    Actor justin = Actor.named("Justin");

    @Managed
    public WebDriver hisBrowser;

    @Before
    public void annaCanBrowseTheWeb() {
        justin.can(BrowseTheWeb.with(hisBrowser));
    }

    @Test
    public void should_be_able_to_add_an_item_to_the_todo_list() {

        givenThat(justin).wasAbleTo(StartWith.anEmptyTodoList());

        when(justin).attemptsTo(AddATodoItem.called("Feed the cat"));

        then(justin).should(seeThat(TheTodoItems.displayed(), hasItem("Feed the cat")));
    }

    @Test
    public void should_be_able_to_add_an_item_to_a_todo_list_with_existing_items() {

        givenThat(justin).wasAbleTo(StartWith.aTodoListContaining("Feed the cat","take out the garbage"));

        when(justin).attemptsTo(AddATodoItem.called("Walk the dog"));

        then(justin).should(seeThat(TheTodoItems.displayed(), contains("Feed the cat","take out the garbage","Walk the dog")));
    }

}
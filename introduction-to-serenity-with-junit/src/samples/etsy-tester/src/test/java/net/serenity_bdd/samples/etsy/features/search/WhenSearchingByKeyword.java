package net.serenity_bdd.samples.etsy.features.search;

import net.serenity_bdd.samples.etsy.steps.BuyerSteps;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.lessThan;

// tag::searchByKeyword[]
@RunWith(SerenityRunner.class)                                                   // <1>
public class WhenSearchingByKeyword {

    @Managed(driver="chrome", uniqueSession = true)                              // <2>
    WebDriver driver;

    @Steps                                                                       // <3>
    BuyerSteps buyer;

    @Test
    public void should_see_a_list_of_items_related_to_the_specified_keyword() {  // <4>
        // GIVEN
        buyer.opens_etsy_home_page();
        // WHEN
        buyer.searches_for_items_containing("wool");
        // THEN.
        buyer.should_see_items_related_to("wool");
    }
// end::searchByKeyword[]
// tag::filterResults[]
    @Test
    public void should_be_able_to_filter_by_item_type() {
        // GIVEN
        buyer.opens_etsy_home_page();
        // WHEN
        buyer.searches_for_items_containing("wool");
        int unfilteredItemCount = buyer.get_matching_item_count();
        // AND
        buyer.filters_results_by_type("Handmade");
        // THEN
        buyer.should_see_items_related_to("wool");
        // AND
        buyer.should_see_item_count(lessThan(unfilteredItemCount));
    }

    @Test
    public void should_be_able_to_view_details_about_a_searched_item() {
        // GIVEN
        buyer.opens_etsy_home_page();
        // WHEN
        buyer.searches_for_items_containing("wool");
        buyer.selects_item_number(5);
        // THEN
        buyer.should_see_matching_details();
    }
// end::filterResults[]
// tag::tail[]
}
// end::tail[]
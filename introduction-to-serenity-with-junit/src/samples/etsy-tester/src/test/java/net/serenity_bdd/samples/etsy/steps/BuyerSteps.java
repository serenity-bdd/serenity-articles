package net.serenity_bdd.samples.etsy.steps;

import net.serenity_bdd.samples.etsy.pages.HomePage;
import net.serenity_bdd.samples.etsy.pages.ItemDetailsPage;
import net.serenity_bdd.samples.etsy.pages.SearchResultsPage;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matcher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by john on 7/12/14.
 */
// tag::header[]
public class BuyerSteps {
// end::header[]
// tag::searchByKeywordSteps[]

    HomePage homePage;                                          // <1>
    SearchResultsPage searchResultsPage;

    @Step                                                       // <2>
    public void opens_etsy_home_page() {
        homePage.open();
    }

    @Step
    public void searches_for_items_containing(String keywords) {
        homePage.searchFor(keywords);
    }

    @Step
    public void should_see_items_related_to(String keywords) {
        List<String> resultTitles = searchResultsPage.getResultTitles();
        resultTitles.stream().forEach(title -> assertThat(title.contains(keywords)));
    }
// end::searchByKeywordSteps[]
// tag::filterByType[]
    public void filters_results_by_type(String type) {
        searchResultsPage.filterByType(type);
    }

    public int get_matching_item_count() {
        return searchResultsPage.getItemCount();
    }

    @Step
    public void should_see_item_count(Matcher<Integer> itemCountMatcher) {
        itemCountMatcher.matches(searchResultsPage.getItemCount());
    }


// end::filterByType[]

    ItemDetailsPage detailsPage;

    @Step
    public void selects_item_number(int number) {
        searchResultsPage.selectItem(number);
    }

    @Step
    public void should_see_matching_details() {
        String itemName = detailsPage.getItemName();
        assertThat(itemName.toLowerCase()).contains("wool");
    }

// tag::tail[]
}
//end:tail

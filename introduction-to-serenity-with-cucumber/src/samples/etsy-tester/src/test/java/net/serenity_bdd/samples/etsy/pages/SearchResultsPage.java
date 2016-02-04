package net.serenity_bdd.samples.etsy.pages;

import com.google.common.base.Optional;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

// tag::header[]
public class SearchResultsPage extends PageObject {
// end::header[]
    public static final String SCROLL_TO_FILTERS = "$('a.red')[0].scrollIntoView(false);";
// tag::searchByKeyword[]

    @FindBy(css=".listing-card")
    List<WebElement> listingCards;

    public List<String> getResultTitles() {
        return listingCards.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
// end::searchByKeyword[]

    public void selectItem(int itemNumber) {
        listingCards.get(itemNumber - 1)
                .findElement(By.tagName("a")).click();
    }

    public void filterByType(String type) {
        showFilters();
        findBy("#search-filter-reset-form").then(By.partialLinkText(type)).click();
    }

    public int getItemCount() {
        String resultCount = $(".result-count").getText()
                .replace("We found ","")
                .replace(" item","")
                .replace("s","")
                .replace("!","")
                .replace(",","")
                ;
        return Integer.parseInt(resultCount);
    }

    public Optional<String> getSelectedType() {
        List<WebElementFacade> selectedTypes = findAll("#search-filter-reset-form a.radio-label.strong");
        return (selectedTypes.isEmpty()) ? Optional.absent() : Optional.of(selectedTypes.get(0).getText());
    }

    public void showFilters() {
        evaluateJavascript(SCROLL_TO_FILTERS);
    }
// tag::tail[]
}
// end:tail[]

package net.serenity_bdd.samples.etsy.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

// tag::header[]
public class SearchResultsPage extends PageObject {
// end::header[]
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
        $("#filter-marketplace").find(By.partialLinkText(type)).click();
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
// tag::tail[]
}
// end:tail[]

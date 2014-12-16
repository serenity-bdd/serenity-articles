Feature: Adding items to the shopping cart
  In order to buy multiple items at the same time
  As a buyer
  I want to be able to add multiple items to the shopping cart

  Background:
    Given I have searched for items containing 'wool'
    And I have selected an item

  Scenario: Should see total price including tax
    When I add the item to the shopping cart
    Then I should see the total price including tax


@tag
Feature: Purchase the order from Ecommerce
  I want to use this template for my feature file

  Background: 
    Given I landed on ecommerce page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given I Logged in with username <username> and password <password>
    When I add the product <product> to cart
    And Check out <product>
    And submit the Order
    And Order number is displayed
    Then "THANKYOU FOR THE ORDER." is displayed in the confimration page.

    Examples: 
      | username           | password    | product       |
      | 1dummy@account.com | T3$tdummy   | ZARA COAT 3   |
      | anshika@gmail.com  | Iamking@000 | IPHONE 13 PRO |

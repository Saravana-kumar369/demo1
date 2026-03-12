Feature: product purchase
Scenario: user places order 
Given user is logged into application with valid credentials
When user select the product
And user adds the product into cart
And user navigates into cart
And user checkout
And user enters customer details
And user completes the order
Then user order placed successfully
And logged out from the application

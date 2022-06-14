@amazon @web
Feature: Sample Amazon search feature validation

  Background:
    Given Launch the browser

  Scenario: Search for TV and filter based on price high to low and read 2nd highest
    When search for TV
    Then select range filter high to low
    And check product with 2nd highest price
    Then verify about product and log details
Feature: Authors and author page

  As a user
  so that I can track my authors
  I want to add a new author to the list

  Scenario: Add an author
    Given I'm on the author creation page
    When I add a new author
    Then I should be able to see the new author's page
    
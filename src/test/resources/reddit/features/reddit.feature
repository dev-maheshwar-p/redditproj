Feature: Sanity Suite for Reddit

  Background: Verify that the authorized user is able to login.
    Given The user lands on "https://www.reddit.com/".
#    Then The user tries to login with username "mavdabbler" and password "mavdabblerismavdabbler" as credentials.
    Then The user tries to login with credentials for the user "mavdabbler".

  Scenario: The User lands on the Reddit home page post login.
    And The user should land on the home page.

  Scenario: Verify that the authorized user is able to view subscribed reddits.
    And The user verifies that the subscribed subreddits is displayed.

  Scenario: Verify that the user is able to view details of any one of the sub reddits.
    Given The user verifies he is able to view any random subreddit that is displayed.

  Scenario: Verify that the user is able to view and up vote any one of the random sub reddits.
    Given The user verifies he is able to view any random subreddit that is displayed.
    Then The user up votes the post.

  Scenario: Verify that the user is able to view and down vote any one of the random sub reddits.
    Given The user verifies he is able to view any random subreddit that is displayed.
    Then The user down votes the post.

  Scenario: Verify that the user is able to comment on any one of the random sub reddits.
    Given The user verifies he is able to view any random subreddit that is displayed.
    Then The user provides a comment for the post.

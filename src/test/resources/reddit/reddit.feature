Feature: Sanity Suite for Reddit

  #precondition:CLEANUP
  Scenario Outline: Verify that the authorized user is able to login
    #OMS PO creation
    Given The user lands on the <reddit-page>.
    And The user tries to login with the <user-name> and <password> as credentials.
    Then The user should land on the home page.

    Examples:
      | reddit-page               | user-name    | password                 |
      | "https://www.reddit.com/" | "mavdabbler" | "mavdabblerismavdabbler" |

#  @LastScenario
#  Scenario: Gate out Trailer
#    When user gates out the Trailer from YMS
#    And user verifies the load status as "Finalized" and Container status as "Billed" in Loading
#    And orderTacker has order status "SHIPPED" for those orderTracking lines
#    Then user verifies the sales details in DCFinancials
#    And user verifies ASN creation

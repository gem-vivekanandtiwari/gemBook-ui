Feature: Gembook -> News Feed -> Awards

  Background: User Login into the Gembook Application
    When User clicks on signIn Button
    Then User enters the "username"
    Then User enters the "password"
    And User logins into the application
    Then Verify user is logged into the application or not
    When User navigates to tab "News Feeds"


  @demo123987
  Scenario Outline: Gembook_Awards_ScrollThroughListOfWinnersIn<desiredYear>
    Given Check if Award section is present in News Feeds
    When User selects "<desiredYear>" and "<desiredEvent>" from award section
    Then Verify if data is present in Awards Section for "<desiredEvent>"
    Then Scroll through the list of winners in Awards section for "<desiredYear>" and "<desiredEvent>"
    Examples:
      | desiredYear | desiredEvent      |
      | 2022        | Rising Star Award |


  @demo123987
  Scenario Outline: Gembook_OtherPortals_NavigationToNewPageOnClicking<desiredPage>
    Given Check if Other Portals is present in side bar
    When Check if all sub items are present
    Then User clicks on the "<desiredPage>" menu item
    Then Verify if user is navigated to "<desiredPage>" having "<URL>" with "<title>"
    Examples:
      | desiredPage  | URL                                     | title               |
      | Jenkins      | https://jenkins-np.geminisolutions.com/ | Dashboard [Jenkins] |
      | Service Desk | https://helpdesk.geminisolutions.com/   | Helpdesk            |
#      | Azure         | https://azure.microsoft.com/en-us/products/devops/?nav=min |  | Azure DevOps Services |
#      | LMS          | https://geminisolutions.talentlms.com/   | L&D Gemini                |
#      | Github       | https://github.com/Gemini-Solutions      | Gemini Solutions · GitHub |
#      | Athena       | https://athena.geminisolutions.com/login | Athena                    |



  @demo123987
  Scenario: Gembook_PostInteraction_ValidateTheProfileOfPostOwner
    Then User checks the presence of posts on the News Feeds page
    And User verifies the presence of necessary elements of the posts
    When User hits the "Profile Name" of the post
    Then Check if the profile of the clicked user opens up or not

  @demo123987
  Scenario: Gembook_PostInteraction_ExpandImageOnClickInsidePost
    Then User checks the presence of posts on the News Feeds page
    And User verifies the presence of necessary elements of the posts
    When User hits the "Active Image" of the post
    Then Verify if the image gets expanded when user clicks on the image

  @demo123987
  Scenario: Gembook_Profile_EnterSkillsData
    When Click on ViewProfile icon
    Then Verify if Any "Skills" are existing
    Then Click on Add new "Skills"
    And Save added "Skills" information
    Then Verify if "Skills" data is updated
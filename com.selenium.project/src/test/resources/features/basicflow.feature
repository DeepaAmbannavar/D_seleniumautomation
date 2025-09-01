#Author: deepa.61079268@ltimindtree.com

@tag
Feature: Create Declaration for Import to Local From ROW
  

  @Test
  Scenario: Create a declaration
    Given I am logged in to Dubai Trade portal
    When I navigate to the Integrated Clearance page
    Then the user clicks on Start Journey in the description iframe
    #And I select "Import" from the Regime Type dropdown
    And I select "Transit" from the Regime Type dropdown
    When I select "FZ Transit Out" from the "Declaration Type" dropdown
    And I select "Sea" from "Cargo Channel" dropdown
    And I enter DO Number
    And I click on Proceed
    When I search Exporter Code
    And I upload a Inovice File
    #And I add invoice manually
    #When I fill the form with the following data:
    #| Field Name         | Value           |
    #| Invoice No         | Automation01    |
    #| Buyer Name         | Acme Corp       |
    #| No of Pages        | 10              |
    #| Invoice Type       | Invoice      	 |
    #| Invoice Date 			 | CURRENT_DATE  	 |
    #| Payment Terms      | Others          |
    #| Terms of Delivery  | FOB             |
    #| Invoice Value      | 50000           |
    #| Invoice Currency   | USD             |
	#And I click on Save
	And I click on Yes
	When I click on the "Proceed" button
	When I click on the "Proceed To Review & Submit Declaration" button
	
	
	
	#CHC-PR-82012
#	AgentCode-AE-8122443
#PC 2500000353
    
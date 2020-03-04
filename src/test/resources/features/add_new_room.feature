Feature: add new room feature
  when hotelier add new room

  Scenario: there is no room
    Given it doesn't defined any room for Hotel yet
    When add new economy room to hotel
    Then return countEconomy: 1, countFreeEconomy: 1, countFreePremium: 0, countPremium: 0


  Scenario: there is one economy room
    Given defined one economy room
    When add new economy room to hotel
    Then return countEconomy: 2, countFreeEconomy: 2, countFreePremium: 0, countPremium: 0


  Scenario: there is two economy room
    Given defined two economy room
    When add new premium room to hotel
    Then return countEconomy: 2, countFreeEconomy: 2, countFreePremium: 1, countPremium: 1


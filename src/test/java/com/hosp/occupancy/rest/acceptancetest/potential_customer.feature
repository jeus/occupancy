Feature: calculating income from free home and potential customer
  when add new potential array

#  Less than 100£

  Scenario: there is one economy and potential customer will pay less than 100£
    Given add potential customers list <potentials>
      | potentials                                   |
      | 99                                           |
      | 99                                           |
      | 99                                           |
      | 100                                          |
      | 100                                          |
      | 100                                          |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 |
    When add free room
      | countEconomy | countPremium |
      | 1            | 0            |
      | 0            | 1            |
      | 1            | 1            |
      | 1            | 0            |
      | 0            | 1            |
      | 1            | 1            |
      | 3            | 3            |
      | 5            | 7            |
      | 7            | 2            |
      | 1            | 7            |
    Then calculate
      | countEconomy | countPremium | countFreeEconomy | countFreePremium | economyIncome | premiumIncome |
      | 1            | 0            | 0                | 0                | 99            | 0             |
      | 0            | 1            | 0                | 0                | 0             | 99            |
      | 1            | 1            | 0                | 1                | 99            | 0             |
      | 1            | 0            | 1                | 0                | 0             | 0             |
      | 0            | 1            | 0                | 0                | 0             | 100           |
      | 1            | 1            | 1                | 0                | 0             | 100           |
      | 3            | 3            | 0                | 0                | 167           | 738           |
      | 5            | 7            | 3                | 0                | 189           | 1054          |
      | 7            | 2            | 3                | 0                | 189           | 583           |
      | 1            | 7            | 0                | 0                | 45            | 1153          |

#       they question has a conflict
#       374,209,155,115,101,100      99,45,23,22
#       374+209+155+115+101+100 = 1054   99+45+23+22 = 189 <-----|  or this have to become 1153
#       374+209 = 583  99+45+23+22 = 189                         |
#       374+209+155+115+101+100+[99] = 1054  45      <-----------|  or this have to become 1153 and 99




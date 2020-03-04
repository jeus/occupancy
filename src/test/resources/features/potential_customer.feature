Feature: calculating income from free home and potential customer
  when add new potential array

#  Less than 100£

  Scenario Outline: add some potentional and then define free room
    Given add potential customers "<potentials>"
    When add free room <countEconomy> <countPremium>
    Then calculate <countFreeEconomy> <countFreePremium> <economyIncome> <premiumIncome>


    Examples:
      | potentials                                   | countEconomy | countPremium | countEconomy | countPremium | countFreeEconomy | countFreePremium | economyIncome | premiumIncome |
      | 99                                           | 1            | 0            | 1            | 0            | 0                | 0                | 99            | 0             |
      | 99                                           | 0            | 1            | 0            | 1            | 0                | 0                | 0             | 99            |
      | 99                                           | 1            | 1            | 1            | 1            | 0                | 1                | 99            | 0             |
      | 100                                          | 1            | 0            | 1            | 0            | 1                | 0                | 0             | 0             |
      | 100                                          | 0            | 1            | 0            | 1            | 0                | 0                | 0             | 100           |
      | 100                                          | 1            | 1            | 1            | 1            | 1                | 0                | 0             | 100           |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 | 3            | 3            | 3            | 3            | 0                | 0                | 167           | 738           |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 | 5            | 7            | 5            | 7            | 3                | 0                | 189           | 1054          |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 | 7            | 2            | 7            | 2            | 3                | 0                | 189           | 583           |
      | 23, 45, 155, 374, 22, 99, 100, 101, 115, 209 | 1            | 7            | 1            | 7            | 0                | 0                | 45            | 1153          |

#       they question has a conflict
#       374,209,155,115,101,100      99,45,23,22
#       374+209+155+115+101+100 = 1054   99+45+23+22 = 189 <-----|  or this have to become 1153
#       374+209 = 583  99+45+23+22 = 189                         |
#       374+209+155+115+101+100+[99] = 1054  45      <-----------|  or this have to become 1153 and 99




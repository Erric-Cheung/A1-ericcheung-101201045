Feature: Quests Game

  Scenario: A1_scenario
    Given a new game of Quests starts rigged
    When "P1" draws a quest of 4 stages and "P2" sponsors with input "N, Y"
    Then "P2" builds stages of the quest with a total value of 130 with input "0, 6, quit, 1, 4, quit, 1, 2, 4, quit, 1, 2, quit"
    Then Players who accept to participate are "P1, P3, P4" with input "Y, Y, Y"
    Then Participant's attack who pass stage 1 are "Participants: P1, P3, P4," with input "\n\n\n0\n\n0\n\n0\n\n4\n4\nquit\n\n4\n3\nquit\n\n4\n5\nquit\n\n"
    Then Participant's attack who pass stage 2 are "Participants: P3, P4," with input "\n\n\n\n\n\n6\n5\nquit\n\n8\n3\nquit\n\n5\n5\nquit\n\n"
    Then Participant's attack who pass stage 3 are "Participants: P3, P4," with input "\n\n\n\n8\n6\n4\nquit\n\n6\n4\n6\nquit\n\n"
    Then Participant's attack who pass stage 4 are "Participants: P4," with input "\n\n\n\n6\n5\n5\nquit\n\n3\n3\n3\n4\nquit\n\n"
    Then "P1" has 0 shields with the hand "0.F5   1.F10   2.F15   3.F15   4.F30   5.H10   6.B15   7.B15   8.L20" in order
    Then "P3" has 0 shields with the hand "0.F5   1.F5   2.F15   3.F30   4.S10" in order
    Then "P4" has 4 shields with the hand "0.F15   1.F15   2.F40   3.L20" in order
    Then "P2" draws and trims down to 12 cards with input "0, 0, 0, 0"

  Scenario: 2winner_game_2winner_quest
    Given 2. a new game of Quests starts rigged
    When "P1" draws a quest of 4 stages and "P1" sponsors with input "Y"
    Then "P1" builds stages of the quest with a total value of 55 with input "0, quit, 0, 2, quit, 0, quit, 0, 0, quit"
    Then Players who accept to participate are "P2, P3, P4" with input "Y, Y, Y"
    Then Participant's attack who pass stage 1 are "Participants: P2, P4," with input "\n\n\n0\n\n0\n\n0\n\n5\nquit\n\nquit\n\n3\nquit\n\n"
    Then Participant's attack who pass stage 2 are "Participants: P2, P4," with input "\n\n\n\n6\nquit\n\n4\nquit\n\n"
    Then Participant's attack who pass stage 3 are "Participants: P2, P4," with input "\n\n\n\n8\nquit\n\n8\nquit\n\n"
    Then Participant's attack who pass stage 4 are "Participants: P2, P4," with input "\n\n\n\n11\nquit\n\n11\nquit\n\n"
    Then "P2" has 4 shields with the hand "0.F5   1.F10   2.F15   3.F15   4.F30   5.F40   6.S10   7.H10   8.H10   9.B15   10.L20" in order
    Then "P4" has 4 shields with the hand "0.F15   1.F15   2.F30   3.F40   4.D5   5.H10   6.H10   7.B15   8.B15   9.L20   10.L20" in order
    Then "P1" draws and trims down to 12 cards with input "0, 0, 0, 0"

    When "P2" draws a quest of 3 stages and "P3" sponsors with input "N, Y"
    Then "P3" builds stages of the quest with a total value of 30 with input "0, quit, 0, quit, 0, quit"
    Then Players who accept to participate are "P2, P4" with input "N, Y, Y"
    Then Participant's attack who pass stage 1 are "Participants: P2, P4," with input "\n\n\n\n6\nquit\n\n4\nquit\n\n"
    Then Participant's attack who pass stage 2 are "Participants: P2, P4," with input "\n\n\n\n6\nquit\n\n4\nquit\n\n"
    Then Participant's attack who pass stage 3 are "Participants: P2, P4," with input "\n\n\n\n9\nquit\n\n10\nquit\n\n"
    Then "P2" has 7 shields with the hand "0.F5   1.F10   2.F15   3.F15   4.F30   5.F40   6.S10   7.H10   8.H10   9.L20   10.L20" in order
    Then "P4" has 7 shields with the hand "0.F15   1.F15   2.F30   3.F40   4.S10   5.S10   6.H10   7.H10   8.B15   9.B15   10.L20" in order
    Then The winners of the game are "Game Winners: P2, P4"

  Scenario: 1winner_game_with_events
    Given 3. a new game of Quests starts rigged
    When "P1" draws a quest of 4 stages and "P1" sponsors with input "Y"
    Then "P1" builds stages of the quest with a total value of 55 with input "0, quit, 0, 2, quit, 0, quit, 0, 0, quit"
    Then Players who accept to participate are "P2, P3, P4" with input "Y, Y, Y"
    Then Participant's attack who pass stage 1 are "Participants: P2, P3, P4," with input "\n\n\n0\n\n0\n\n0\n\n5\nquit\n\n3\nquit\n\n3\nquit\n\n"
    Then Participant's attack who pass stage 2 are "Participants: P2, P3, P4," with input "\n\n\n\n\n\n6\nquit\n\n3\nquit\n\n4\nquit\n\n"
    Then Participant's attack who pass stage 3 are "Participants: P2, P3, P4," with input "\n\n\n\n\n\n6\n7\nquit\n\n3\n6\nquit\n\n4\n6\nquit\n\n"
    Then Participant's attack who pass stage 4 are "Participants: P2, P3, P4," with input "\n\n\n\n\n\n6\n7\nquit\n\n3\n6\nquit\n\n4\n6\nquit\n\n"
    Then "P2" has 4 shields with the hand "0.F5   1.F10   2.F15   3.F15   4.F30   5.F40   6.B15   7.L20   8.E30" in order
    Then "P3" has 4 shields with the hand "0.F5   1.F10   2.F15   3.S10   4.S10   5.H10   6.B15   7.L20   8.L20" in order
    Then "P4" has 4 shields with the hand "0.F15   1.F15   2.F30   3.F40   4.H10   5.B15   6.L20   7.L20   8.E30" in order
    Then "P1" draws and trims down to 12 cards with input "6, 6, 6, 6"
    Then "P2" draws the event card "Plague"
    Then "P3" draws the event card "Prosperity"
    Then "P4" draws the event card "Queen's favor" and trims hand with input "8\n"
    Then "P1" draws a quest of 3 stages, possibly trimming hand, and "P1" sponsors with input "6, 6, Y"
    Then "P1" builds stages of the quest with a total value of 30 with input "0, quit, 0, quit, 0, quit"
    Then Players who accept to participate are "P2, P3, P4" with input "Y, Y, Y"
    Then Participant's attack who pass stage 1 are "Participants: P2, P3," with input "\n\n\n\n\n0\n\n6\nquit\n\n6\nquit\n\nquit\n\n"
    Then Participant's attack who pass stage 2 are "Participants: P2, P3," with input "\n\n\n\n6\nquit\n\n6\nquit\n\n"
    Then Participant's attack who pass stage 3 are "Participants: P2, P3," with input "\n\n\n\n6\nquit\n\n6\nquit\n\n"
    Then "P2" has 5 shields
    Then "P3" has 7 shields
    Then "P4" has 4 shields
    Then The winners of the game are "Game Winners: P3"

  Scenario: 0_winner_quest
    Given 4. a new game of Quests starts rigged
    When "P1" draws a quest of 2 stages and "P2" sponsors with input "N, Y"
    Then "P2" builds stages of the quest with a total value of 105 with input "3, 5, 5, 6, quit, 3, 3, 3, quit"
    Then Players who accept to participate are "P1, P3, P4" with input "Y, Y, Y"
    Then Participant's attack who pass stage 1 are "Participants:" with input "\n\n\n0\n\n0\n\n0\n\n11\nquit\n\n11\nquit\n\n11\nquit\n\n"
    Then "P1" has 0 shields with the hand "0.F5   1.F15   2.F15   3.F30   4.D5   5.S10   6.S10   7.H10   8.H10   9.B15   10.B15" in order
    Then "P3" has 0 shields with the hand "0.F5   1.F10   2.F15   3.D5   4.S10   5.S10   6.S10   7.S10   8.H10   9.H10   10.B15" in order
    Then "P4" has 0 shields with the hand "0.F15   1.F15   2.F40   3.D5   4.D5   5.S10   6.H10   7.H10   8.B15   9.B15   10.L20" in order
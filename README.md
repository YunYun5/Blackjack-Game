# CPSC210 BlackJack Game - Term Project

Want to play some risk-free BlackJack? This is the perfect application

## Overview:

**What will the application do?**

This application aims to provide users with the traditional BlackJack experience in a risk-free environment where money is irrelevant.
In this application the user will be able to bet "money" and play BlackJack according to their own decisions.
The users will also be able to save their progress (their balance) whenever they want.

**Who will use it?**

This application is intended for people who like to play BlackJack. 
Also, for people who have never played BlackJack and would want to play it without real money for their first time.


**Why is it of interest to me?**

BlackJack is one of my favourite games to play when I am at the casino. 
I find that I have the best odds of winning when I play it compared to games such as roulette.
Even though the game is played with betting real money I wanted to create a risk-free environment where I can play this game without being worried about losing money.
Being in this risk-free environment also lets me practice Basic Strategy without the pressure of being in the casino.

## User Stories:

- As a user, I want to be able to "hit" to have a Card added to my Hand
- As a user, I want to be able to view the Cards in my Hand and view the dealers Hand to see if I won
- As a user, I want to be able to "stand" if I am happy with the value of my hand
- As a user, I want to be able to increase or decrease my Bet between rounds to my liking
- As a user, I want to be able to save the state of the game (the deck and hands) and my balance aswell as my current bet when I want to
- As a user, I want to be able to load the state of the game (the deck and hands) and my balance aswell as my old bet when I want to

## Instructions for Grader:
- Note: Once the application is launched the Hit and Stand button will be disabled this is because the user needs to first place a bet. So when the application is launched just choose a bet you want by clicking on the chips. You can increase your bet by continuously clicking on different chips. Your current bet will be displayed on the bottom right and to submit the bet you just have to click stand or hit.


- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the hit button which will add another card to your "the players" hand.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the stand button which will add cards to the dealers hand until the games conditions are met.
- You can locate my visual component by just looking at the screen. Both the cards appearing after pressing hit, or stand and the chips are all visual components
- You can save the state of my application by clicking the save button at any time.
- You can reload the state of my application by clicking the load button at any time. (Note: The fields and the state of the application gets reloaded when you click load. But to see the effects of the reload you will need to click on one of the chips and place a bet. This was designed like this because I did not want a user to place a bet after seeing their cards because that is not how the game is traditionally played. But like staed this is completely visual the game gets reloaded just not displayed until a bet is placed.)

## Event Logging

### Below is a sample of how the log is printed after the close of the application

The Log prints the key events in the application such as when a card is added to either the dealers or players hand and if the player wins or loses the round

Sun Apr 07 20:05:09 PDT 2024 - Added TEN of CLUBS to PlayerHand 

Sun Apr 07 20:05:09 PDT 2024 - Added SEVEN of HEARTS to DealerHand

Sun Apr 07 20:05:09 PDT 2024 - Added JACK of HEARTS to PlayerHand

Sun Apr 07 20:05:09 PDT 2024 - Added JACK of HEARTS to DealerHand

Sun Apr 07 20:05:11 PDT 2024 - Player won with a 10$ bet!

Sun Apr 07 20:05:16 PDT 2024 - Added EIGHT of SPADES to PlayerHand

Sun Apr 07 20:05:16 PDT 2024 - Added ACE of DIAMONDS to DealerHand

Sun Apr 07 20:05:16 PDT 2024 - Added JACK of SPADES to PlayerHand

Sun Apr 07 20:05:16 PDT 2024 - Added NINE of HEARTS to DealerHand

Sun Apr 07 20:05:17 PDT 2024 - Player lost with a 25$ bet!
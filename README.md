# BlackJack Game

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

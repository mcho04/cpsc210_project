# Voting System

## By Paul Cho (93098499)

- This application is a voting system. 
  It has number of candidates that voters can vote to. 
  When the voting is done, it collects the data and announces who won the vote.
  This application also statistically shows which candidate is receiving how much portion of the vote (in percentage) in real-time.
  I am going to start off by implementing **Plurality Voting** (candidate who gets the most votes wins) which is what we are most familiar with.
  However, if time is allowed, I'm thinking to implement other types of voting such as **Instant Runoff Voting** (voters are allowed to rank the candidates in order of preference) and **Electoral College Voting** (special voting method used for election in USA).


- This application can be used in a various way. 
  From executing a *simple poll* done in a classroom to executing *president election* in a nation.
  This application can be used for any types of voting situation with any types of desired method.


- This project interested me because I have a huge interest in politics, and I recently saw breaking news on Brazil.
  The content was that the former president Bolsonaro's *supporters attacked Presidential Palace* after he loses election.
  And Brazil's Supreme Court agreed to *investigate* former Brazil president Jair Bolsonaro for riot. 
  Reading these news articles, I decided to make a fair voting system on my own for the better.

## User Stories

- As a user, I want to be able to create a candidate and add it to a list of candidates
- As a user, I want to be able to create a province and add it to a list of provinces
- As a user, I want to be able to control how many people are voting
- As a user, I want to be able to view how much seating (ridings) each party has earned
- As a user, I want to be able to view statistically how much portion of votes specific candidate received out of whole
- As a user, I want to be able to view how many people chose not to vote (statistically)
- As a user, I want to be able to control voting power per state (**for Electoral College Voting only**)
- As a user, I want to be able to save my provinces (If I so choose)
- As a user, I want to be able to save my candidates (If I so choose)
- As a user, I want to be able to load my saved provinces (If I so choose)
- As a user, I want to be able to load my saved candidates (If I so choose)

# Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by clicking "Add province" or "Add candidate" button
- You can generate the second required action related to adding Xs to a Y by clicking "Remove province" or "Remove province" button
- You can locate my visual component if you start the app.
- You can save the state of my application by clicking "save" button.
- You can reload the state of my application by clicking "load" button.

# Phase 4 : Task 2
- A representative sample of when event(button) *Generate province* is clicked:
- Tue Apr 11 19:25:26 PDT 2023
  Province added
- A representative sample of when event(button) *Remove province* is clicked:
- Tue Apr 11 19:25:27 PDT 2023
  Province removed
- A representative sample of when event(button) *Generate candidate* is clicked:
- Tue Apr 11 19:25:39 PDT 2023
  Candidate added
- A representative sample of when event(button) *Remove candidate* is clicked:
- Tue Apr 11 19:25:42 PDT 2023
  Candidate removed

# Phase 4 : Task 3
- If I were to refactor my project, I would create a new class called AddRemove, new interface called Voting and have candidates and provinces 
extend and implement those class. Provinces and candidates both has adding/removing X from Y methods (addProvince, addCandidate, removeProvince, removeCandidate)
, therefore I would create add and remove method in AddRemove class and make sure the remove method has a parameter that takes in arraylist
and that add method has a parameter that takes in arraylist and a Voting class.
Then, I can just simply use those methods to add/remove X to/from Y and this would reduce lots of duplication in my code.

 


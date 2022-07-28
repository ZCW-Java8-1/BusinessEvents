# BusinessEvents

build a handler for consuming asynchronous business events.

This labs gets you to think about business event objects.

How can an event be an object? How do you handle an event? Log it?

A business event is an actual happening in the real world of some kind of consequence.
Like "deposit event at timestamp-t into acct #123, amount $150.00"

A consumer (or handler) of this event might:

1) log the event into a event file (maybe a logfile.txt)
2) cause some kind of processing to occur because of it
  a) debit account 123 with 150.0 (with a note on the timestamp)
  b) generate another event which confirms the action?
3) goes back to input stream and gets another event...

There is a queue of random business events which gets started up when you
create a EventSource object and call its `run()` method. 

The EventConsumer class should `fetch()` the next event, and then decompose it and handle the event.

The EventSource has 2 types of event formats available:

- String, which needs to be broken into fields
  
  -- `DEPOSIT | 2022-06-30T09:53:40.202435-04:00[America/New_York] | acct-2004 |    95.49 `
  
-- `WITHDRAW | 2022-06-30T09:53:40.197550-04:00[America/New_York] | acct-2004 |    25.28 `

- JSON, which provides a json string, so a Java object can be created

  -- `{"origin":"2022-06-30T11:08:36.355-04:00[America/New_York]",
       "evtype":"DEPOSIT",
       "amount":26.82,
       "account":"2001",
       "evid":"c89dcbe4-e44f-438f-a4c7-e87cd3eb3841"}
  `

## Goal: A Report

Produce a text output report after some non-trivial numbers of events (like thousands?) showing each account and it's
ending balance.

Somethign like:

```
*** Accounts at end
           balance  |  number of deposit | number of withdraw | number of overdrafts 
acct-2004    124.55           15                22                    4
acct-2007   3456.77           24                7                     0
etc....
```
### Implementation

You should create a Java object (a POJO), that can contain the fields which the events supplies.

If there are errors in the events, the program should not halt, it should recover gracefully and take the corrupted event and place it into an error log file.

Look carefully at the App class.

Add to the projects classes:

## Account class

- create an Account class which
- has a series of instance variables
- an account should have a balance,
- and a list of all the transactions it has seen.
- an account should start with an initial balance of $100.00
- the account class should have a `credit(ATMEvent e)` method
  - which ADDs the amount to the account 
- and a `debit(ATMEvent e)` method
  - which SUBTRACTs the amount from the account
- track when the account goes negative, and how many times
- create a Test class and 3 test methods for each `public` method in the class

## BankService

- create a BankService class that tracks many accounts.
- each entry in the class has a account name mapped to an Account object
- track only accounts you see transactions for
- create an method `recordEvent(ATMEvent e)` which takes an event and tells the correct Account object what to do
- create a Test class and a few test methids for each method you make `public`

## LogService

- takes events and puts them out into a text file
- constructor `LogService(String pathname)` `pathname` is the name of the log file to be created
- each event gets dumped into the file.
- method `log(BizEvent e)` is called to do the logging.
- create a Test class and 3 test methods for each `public` method in the class

# ANTLR4-Comapailer-HTML-QUERY

## Before starting FinalProgram.java build gradle project 
* It will create Antlr4 classes inside `\main\hr\fer\ilj\antlr`
* Later you can use them in `hr.tel.fer.dz1.htmlregex` package where main Program is

## Antlr4 grammar
* `Inside main\antlr\hr\fer\ilj\antlr`
* `Request.g4 file`

```
grammar Request;

@header {
package hr.fer.ilj.antlr; 
}

request:   stat ;

stat:   'ALL'    #All
    |   'ALL' tag   #AllTag
    |   'ALL' 'EMAIL' #AllEmail
    |   'ALL' 'IP'   #AllIp
    |   'ALL' 'DATE' #AllDate
    |   'ALL' 'TEL'   #AllTel
    |   'ONLY' tag value  #AllTagID
    |   'ONLY' 'EMAIL' value  #AllEmailID
    |   'ONLY' 'IP' value  #AllIpID
    |   'ONLY' 'DATE' value  #AllDateID
    |   'ONLY' 'TEL' value  #ALLTelID
    |   'HELP'  #HELP
    |   'EXIT'  #EXIT
    |   'REGEX ID' value  #RegexID
    |   NEWLINE  #NEWLINE
    ;

tag: '<'TAG'>' ;
value: BROJ ;

BROJ: [0-9]+ ;
TAG: [a-z]+ ;
NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
WS  :   [ \t]+ -> skip ; // toss out whitespace
```

## Main
* Inside package: `hr.tel.fer.dz1.htmlregex`
* Class: `FinalProgram`

## Example
```
> ALL IP
252.255.255.255
> ALL <li>
email: marko.maric@fer.hr
IP: 252.255.255.255
DOB: 11/9/2001
tel: 385-1-222-2222
> ONLY <li> 2
email: marko.maric@fer.hr
IP: 252.255.255.255
> ALL DATE
11/9/2001
> EXIT
Goodbye!
```

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
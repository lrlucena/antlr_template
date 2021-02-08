grammar Language; 

program: expression EOF; 

expression: INT                          # int
          | expression '+' expression    # sum
          ;

INT: ('0'..'9')+;

Space: [ \t\n\r] -> skip; 

//Comment: '#' .*? '\n' -> skip;
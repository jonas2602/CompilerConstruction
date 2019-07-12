program gototest;

var
    test: integer;

label
    1;

begin
    writeln('READY');
    writeln(test);
    goto 1;
    test := test + 1;
    1:
    test := test + 2;
    writeln(test);
end.

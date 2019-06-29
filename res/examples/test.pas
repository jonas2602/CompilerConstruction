program test;

procedure test(test2: integer);
label 1;
begin
    goto 1;
    test2 := test2 + 1;
    1:
    test2 := test2 + 2;
    writeln(test2);
end;
begin
    writeln(5);
    test(6);
end.
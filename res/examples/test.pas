program test;

procedure test(test2: integer);
label 1;
begin
    test2 := test2 + 1;
    1:
    test2 := test2 + 2;
    writeln(test2);
    goto 1;
end;
begin
    writeln(5);
    test(6);
end.
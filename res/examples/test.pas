program test;

procedure test(test2: integer);
label 1;
begin
    test2 := test2 + 1;
    goto 1;
    test2 := test2 + 2;
end;
begin
    writeln(5);
    test(6);
    if(TRUE) then
    begin
        writeln(5);
    end;
end.

program test;

var
    int: integer;

procedure test(var i: integer);
begin
    i := i + 1;
end;

begin
    int := -4;
    test(int);
    writeln(int);
end.
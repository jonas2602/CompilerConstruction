program test;

function fact(n: integer): integer;
begin
    fact := 1 + n
end;

var
    n: integer;

begin
    n := fact(n);
    n := fact(n);
    writeln(n);
end.
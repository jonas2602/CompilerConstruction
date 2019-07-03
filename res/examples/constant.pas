program helloworld;

procedure funccall(n: integer; var x: integer);
var
    i: integer;
begin
    for i := 0 to n do
        writeln(i);
end;

const
   intconst = 5;

var
    myint: integer;

begin
    myint := intconst;
    funccall(intconst, myint); {second parameter triggers error if const var is used: can't pass const variable by non const reference}
    writeln(intconst);

    {intconst := myint;}    {triggers error: cant assign to const variable}
end.


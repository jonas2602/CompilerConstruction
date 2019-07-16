program factorial;

function fact(n: integer): integer;
begin
    if (n = 0) then
        fact := 1
    else
        fact := n * fact(n - 1);
end;

var
    n: integer;

begin
    writeln('READY');
    for n := 0 to 16 do
        writeln(n, '!=', fact(n));
end.

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
    result: integer;

begin
    for n := 0 to 16.0 do
        result := fact(n);
end.
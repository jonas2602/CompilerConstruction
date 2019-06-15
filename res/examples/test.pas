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
    upper: (red, blue, green);
    x: 1 .. 4;
    arr: array [1..5] of integer;

begin
    for n := 0 to 16 do
        result := fact(n);

    x := n;
    arr[0] := x;

end.
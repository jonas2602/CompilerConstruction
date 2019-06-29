program loops;

procedure forloop(n: integer);
var
    i: integer;
begin
    for i := 0 to n do
        writeln(i);
end;


procedure whileloop(n: integer);
var
    i: integer;
begin
    while i < n do
    begin
        writeln(i);
        i := i + 1;
    end;
end;

procedure repeatloop(n: integer);
var
    i: integer;
begin
    repeat
        writeln(i);
        i := i + 1;
    until i > n;
end;

begin
    repeatloop(12);
end.

program test;

function fact(n: integer): integer;
begin
    writeln(n + 1);
end;

procedure loops(n: integer);
var
    i: integer;
begin
    for i := 0 to n do
        writeln(i);
end;

var
    cc: array[0..2] of array[0..2] of char;
    x: char;
    y: integer;
    z: real;
    n: integer;
begin
    y := 10;
    x := 'x';
    cc[0][0] := 'a';
    cc[0][1] := 'b';
    cc[0][2] := 'c';

    if y < 2 then
    begin
        cc[0][0] := 'w';
    end;

    x := cc[0][0];
    cc[0][2] := x;
    writeln(x, ' ', cc[0][0], cc[0][1], cc[0][2], ' ', 1.0);
    fact(y);
    loops(12);
end.

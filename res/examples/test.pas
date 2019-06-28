program test;

function fact(n: integer): integer;
begin
    writeln(n + 1.0);
end;

var
    cc: array[0..2] of char;
    x: char;
    y: integer;
    z: real;
begin
    x := 'x';
    cc[0] := 'a';
    cc[1] := 'b';
    cc[2] := 'c';

    if TRUE then
    begin
        cc[0] := 'w';
    end;

    x := cc[0];
    cc[2] := x;
    writeln(x, ' ', cc[0], cc[1], cc[2], ' ', 1.0);
    fact(y);
end.

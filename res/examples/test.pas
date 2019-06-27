program test;

function fact(n: integer): integer;
begin
    writeln(n);
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

    x := cc[0];
    cc[2] := x;
    writeln(x, ' ', cc[0], cc[1], cc[2], 1.0);
    fact(y);
end.
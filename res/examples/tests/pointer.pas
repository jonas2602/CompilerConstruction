program pointer;

procedure increment(var i: array[0..2] of integer);
begin
    i[0] := i[0] + 1;
end;

var
    c : array[0..4] of integer;
    p : ^integer;
    i : array[0..2] of integer;
begin
    writeln('READY');
    c[0] := 0;
    c[1] := 1;
    c[2] := 2;
    c[3] := 3;
    c[4] := 4;

    p := @c[1];
    p^ := 10;
    writeln(c[0], ' ',c[1], ' ',c[2], ' ',c[3], ' ',c[4], ',', ' ', p^);

    i[0] := 2;
    increment(i);
    writeln(i[0]);

end.
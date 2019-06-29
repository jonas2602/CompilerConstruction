program pointer;

var
    c : array[0..4] of integer;
    p : ^integer;
begin
    c[0] := 0;
    c[1] := 1;
    c[2] := 2;
    c[3] := 3;
    c[4] := 4;

    p := @c[1];
    p^ := 10;
    writeln(c[0], ' ',c[1], ' ',c[2], ' ',c[3], ' ',c[4], ',', ' ', p^);

end.
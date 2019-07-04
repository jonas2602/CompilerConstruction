program arrays;

var
    cc: array[0..2] of array[0..2] of char;
    x: char;
    str: ^char;
    chararr: string[5];
    copy: string[5];
begin
    x := 'x';
    cc[0][0] := 'a';
    cc[0][1] := 'b';
    cc[0][2] := 'c';

    x := cc[0][0];
    cc[0][2] := x;
    writeln(x, ' ', cc[0][0], cc[0][1], cc[0][2], ' ', 1.0);

    chararr := 'afgh';
    str := chararr;
    copy := chararr;

    writeln(str);
end.
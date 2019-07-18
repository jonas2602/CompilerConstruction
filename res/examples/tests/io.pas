program io;

var
    text: string[100];
    list: array of integer;
    i: integer;
    b: boolean;

begin
    writeln('READY');
    setlength(list, 2);
    list[0] := 10;
    list[1] := 20;
    b := false;

    i := 1234;
    writeln(i, ' abc ', b, list);
end.

program io;

var
    text: string[100];
    list: array of integer;
    i: integer;
    b: boolean;

begin
    setlength(list, 2);
    list[0] := 10;
    list[1] := 20;
    b := false;

    i := 1234;
    writeln(i, ' abc ', b, list);

    {writeln('Please enter a line of text:');
    readln(text);
    writeln('Echo: ', 'text', list);}
end.

program test;

type
    t = (jan, feb, mar);

var
    i: integer;
    c: char;
begin
    i := 48;
    c := chr(i);
    writeln(c);

    i := ord(feb);
    writeln(i);

    i := ord(TRUE);
    writeln(i);

    i := ord(c);
    writeln(i);
end.
program switch;

var
    i: integer;

begin
    case 1 + 4 of
        1, 2:
            i := 1 + 0;
        3:
            i := 2 + 0;
        else
            i := 3 + 0;
    end;

    writeln(i);
end.
program switch;

type
    day = (mon, tue, wed, thu, fri, sat, sun);

var
    current: day;
    letter: char;

begin
    writeln('READY');
    current := mon;

    case current of
        mon..wed:   writeln('MEH');
        thu,fri:    writeln('WEEKEND INCOMING');
        sat,sun:    writeln('RELAX');
        else        writeln('WAIT WHAT?');
    end;

    letter := 'A';
    case letter of
        'a'..'z':   writeln('lowercase');
        'A'..'Z':   writeln('uppercase');
        '0'..'9':   writeln('digit');
        else        writeln('special');
    end;
end.
program switch;

type
    day = (mon, tue, wed, thu, fri, sat, sun);

var
    current: day;

begin
    current := mon;

    case current of
        mon..wed:   writeln('MEH');
        thu,fri:   writeln('WEEKEND INCOMING');
        sat,sun:   writeln('RELAX');
        else        writeln('WAIT WHAT?');
    end;
end.
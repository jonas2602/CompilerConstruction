program enum;

type
    day = (mon, tue, wed, thur, fri, sat, sun);

var
    current: day;

begin
    writeln('READY');
    current := sun;
    writeln(current);

    current := mon;
    writeln(current);

    current := wed;
    writeln(wed);

    if current > mon then
    begin
        writeln('Not start');
    end;

    if current = wed then
    begin
        writeln('middle');
    end;
end.
program enum;

type
    day = (mon, tue, wed, thur, fri, sat, sun);

var
    current: day;

begin
    current := sun;
    writeln(current);
end.
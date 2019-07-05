program test;

var
    i: integer;
    k: integer;
    j: ^integer;
    t: ^integer;

begin
    j := @i;
    t := nil;
    if(j = t) then
    begin
        writeln('TEST');
    end;
end.
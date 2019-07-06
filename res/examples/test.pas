program test;

var
    i: integer;
    k: integer;
    j: ^integer;
    t: ^integer;

begin
    j := nil;
    if(nil <> j) then
    begin
        writeln('test');
    end;
end.
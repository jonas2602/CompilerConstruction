program branches;

function classify(i: integer): integer;
begin
    if (i > 0) then
    begin
        if i > 10 then
        begin
            classify := 999;
        end
        else
        begin
            classify := 1;
        end;
        classify := classify + 1;
    end
    else
    begin
        if(i = 0) then
        begin
            classify := 0;
        end
        else
        begin
            classify := -1;
        end;
    end;
end;

begin
    writeln('READY');
    writeln(classify(100));
    writeln(classify(2));
    writeln(classify(0));
    writeln(classify(-100));
end.
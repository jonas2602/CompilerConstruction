program innerfunctions2;

var
    i: integer;

procedure test();
    var j: integer;
    procedure innertest(i: integer);
    begin
        i := i + 1;
        j := j + i;
    end;
begin
    j := 0;
    innertest(1);
    i := i + j;
end;

begin
    i := 2;
    test();
    writeln(i);
end.
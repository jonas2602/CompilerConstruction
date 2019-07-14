program innerfunctions3;

procedure test();
begin
    writeln('test');
end;

procedure innertest();
    procedure test();
        procedure test();
        begin
            writeln('test3');
        end;
    begin
        writeln('test2');
        test();
    end;
begin
    test();
end;

begin
    writeln('READY');
    test();
    innertest();
end.
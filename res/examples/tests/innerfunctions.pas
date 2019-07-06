program innerfunctions;

var
   i: integer;

function test(): integer;
    var
        i: integer;
    function innerTest(): integer;
    begin
        innerTest := i + 1;
    end;
begin
    test := innerTest();
end;

begin
    i := test();
    writeln(i);
    i := test();
    writeln(i);
    i := test();
    writeln(i);
end.
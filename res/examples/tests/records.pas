program records;

type
    person = record
        age : integer;
        name : ^char;
        other: integer;
    end;

function returntest(): person;
begin
    returntest.age := 12;
    returntest.other := 42;
end;


var
    myperson: person;

begin
    writeln('READY');
    myperson := returntest();
    writeln(myperson.age, ' ', myperson.other);
end.
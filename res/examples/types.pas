program types;
type
    mystr = string[5];
    person = record
        firstname, surname : ^char;
        age : integer;
        p: ^person;
    end;
    group = record
        ps : person;
    end;
var
    str: mystr;
    str2: mystr;
begin
    str := 'test';
    str2 := str;
    writeln (str2);
end.
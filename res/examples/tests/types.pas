program types;
type
    mystr = string[5];
    person = record
        firstname, surname : ^char;
        age : integer;
        child: ^person;
    end;
    group = record
        p : person;
    end;
var
    str: mystr;
    str2: mystr;
    p: person;
    c: person;
    gr: group;
begin
    writeln('READY');
    str := 'test';
    str2 := str;

    { set by reference } 
    p.child := @c;
    c.age := 3;
    writeln (p.child^.age);

    { set by copy }
    gr.p := p;
    {p.age := 5;}       { won't affect copied version in gr }
    gr.p.age := 10;
    writeln (gr.p.age);
end.
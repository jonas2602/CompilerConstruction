program dynamics;


var
    myarr: array of real;

begin
    setlength(myarr, 5);
    writeln(length(myarr));
end.

















{type
    parrType = ^arrType;
    arrType = record
        length : integer;
        typesize: integer;
        start: pointer;
    end;

procedure mysetlength(arr : parrType; size : integer);
begin
    arr^.start := reallocmem(arr^.start, size);
    arr^.length := size;
end;



var
    myarr: arrType;
    a: integer = 10;

begin
    mysetlength(@myarr, 5);
    writeln ('Hello World ', myarr.start[0]);
end.}
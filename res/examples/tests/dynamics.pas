program dynamics;


{var
    mystr: ^real;

begin
    getmem(mystr, 4 * 4);
    mystr := reallocmem(mystr, 10);
end.}

var
    myarr: array of real;
    myarr2: array of integer;

begin
    setlength(myarr, 5);
    setlength(myarr2, 5);
    writeln(length(myarr));
    myarr[0] := 2.5;
    myarr2[0] := 1;
    writeln(myarr[0], ' ', myarr2[0]);
end.



{var
    myarr: array of real;

begin
    setlength(myarr, 5);
    myarr[2] := 2.5;
    writeln(myarr[2], ' ', length(myarr));
end.}

















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
program dynamics;


{var
    myarr: array of array of real;
    myarr2: array of array of integer;

begin
    setlength(myarr, 5);
    setlength(myarr[0], 2);
    myarr[0][0] := 5.0;
    writeln(length(myarr[0]), ' ', myarr[0][0]);


    setlength(myarr2, 5);
    setlength(myarr2[0], 2);
    myarr2[0][0] := 5;
    writeln(length(myarr2[0]), ' ', myarr2[0][0]);
end.}



var
    x: array of integer;
    y: array of integer;

begin
    setlength(y, 2);
    writeln(length(x), ' ', length(y));
    y[1] := 12;
    x := y;
    writeln (x[1], ' ', y[1]);
    x[1] := 10;
    writeln (x[1], ' ', y[1]);
    x[1] := 14;
    writeln (x[1], ' ', y[1]);
    setlength(x, 3);
    x[1] := 3;
    writeln (x[1], ' ', y[1]);
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
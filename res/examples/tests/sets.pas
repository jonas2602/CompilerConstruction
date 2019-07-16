program sets;

{var
    lower: char;
    upper: char;
    myset: set of char;

begin
    lower := 'z';
    upper := 'x';
    myset := [lower .. upper];

    writeln('z' in myset)

end.}


var
    upper: set of integer;
begin
    writeln('READY');
    upper := [1, 2, 3, 4, 5];
    writeln(0 in upper,' ',1 in upper,' ',2 in upper);
    writeln(3 in upper,' ',4 in upper,' ',5 in upper);
    writeln(6 in upper,' ',7 in upper,' ',8 in upper);
    writeln(' ');

    upper := upper - [0, 3 .. 6];
    writeln(0 in upper,' ',1 in upper,' ',2 in upper);
    writeln(3 in upper,' ',4 in upper,' ',5 in upper);
    writeln(6 in upper,' ',7 in upper,' ',8 in upper);
    writeln(1 in upper);
    writeln(' ');

    upper := upper + [5, 6 .. 10];
    writeln(0 in upper,' ',1 in upper,' ',2 in upper);
    writeln(3 in upper,' ',4 in upper,' ',5 in upper);
    writeln(6 in upper,' ',7 in upper,' ',8 in upper);
    writeln(8 in upper);
    writeln(' ');
end.


{var
    valid: boolean;

begin
    valid := inrange(0,100,20);
end.}


{var
    myset: set of char;
    myset2: set of char;
    
begin
    myset := ['1','2','3'] + ['4','5'];
    writeln('4' in myset);
    myset2 := myset - ['4'];
    writeln('4' in myset);
end.}
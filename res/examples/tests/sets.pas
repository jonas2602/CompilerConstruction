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
    upper := [1, 2, 3];
    writeln(upper[0],' ', upper[1]);
    writeln(upper[2],' ', upper[3]);
    writeln(upper[4],' ', upper[5]);
    {writeln(1 in upper);}
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
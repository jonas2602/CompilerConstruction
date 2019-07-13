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
    upper := [1, 2, 3, 4, 5];
    writeln(ord(upper[0]),' ', ord(upper[1]), ' ', ord(upper[2]));
    writeln(ord(upper[3]),' ', ord(upper[4]), ' ', ord(upper[5]));
    writeln(ord(upper[6]),' ', ord(upper[7]), ' ', ord(upper[8]));
    writeln(' ');

    upper := upper - [0, 3 .. 6];
    writeln(ord(upper[0]),' ', ord(upper[1]), ' ', ord(upper[2]));
    writeln(ord(upper[3]),' ', ord(upper[4]), ' ', ord(upper[5]));
    writeln(ord(upper[6]),' ', ord(upper[7]), ' ', ord(upper[8]));
    writeln(1 in upper);
    writeln(' ');

    upper := upper + [5, 6 .. 10];
    writeln(ord(upper[0]),' ', ord(upper[1]), ' ', ord(upper[2]));
    writeln(ord(upper[3]),' ', ord(upper[4]), ' ', ord(upper[5]));
    writeln(ord(upper[6]),' ', ord(upper[7]), ' ', ord(upper[8]));
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
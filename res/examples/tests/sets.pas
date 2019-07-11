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
    upper: set of char;
begin
    upper := ['A', 'B', 'Z'];
end.

{var
    myset: set of char;
    myset2: set of char;
    
begin
    myset := ['1','2','3'] + ['4','5'];
    writeln('4' in myset);
    myset2 := myset - ['4'];
    writeln('4' in myset);
end.}
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